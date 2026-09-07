provider "aws" {
  version = "~> 3.0"
  region  = var.region
}

resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"

  tags = {
    Name = "leave-app-vpc-${var.environment}"
  }
}

resource "aws_subnet" "public" {
  count             = 2
  vpc_id            = aws_vpc.main.id
  cidr_block        = cidrsubnet(aws_vpc.main.cidr_block, 8, count.index)
  map_public_ip_on_launch = true

  tags = {
    Name = "leave-app-public-subnet-${var.environment}-${count.index}"
  }
}

resource "aws_ecs_cluster" "main" {
  name = "leave-app-ecs-cluster-${var.environment}"

  tags = {
    Name = "leave-app-ecs-cluster-${var.environment}"
  }
}

resource "aws_ecs_task_definition" "backend" {
  family                   = "leave-app-backend"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode([{
    name      = "leave-app-backend"
    image     = var.container_image
    essential = true
    portMappings = [{
      containerPort = 8080
      hostPort      = 8080
    }]
  }])

  tags = {
    Name = "leave-app-task-def-${var.environment}"
  }
}

resource "aws_ecs_service" "backend" {
  name            = "leave-app-backend-service-${var.environment}"
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.backend.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = aws_subnet.public.*.id
    security_groups = [aws_security_group.backend_sg.id]
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.backend.arn
    container_name   = "leave-app-backend"
    container_port   = 8080
  }

  tags = {
    Name = "leave-app-backend-service-${var.environment}"
  }
}

resource "aws_lb" "backend" {
  name               = "leave-app-backend-alb-${var.environment}"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.backend_sg.id]
  subnets            = aws_subnet.public.*.id

  tags = {
    Name = "leave-app-backend-alb-${var.environment}"
  }
}

resource "aws_lb_target_group" "backend" {
  name     = "leave-app-backend-tg-${var.environment}"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id

  health_check {
    path = "/"
  }

  tags = {
    Name = "leave-app-backend-tg-${var.environment}"
  }
}

resource "aws_security_group" "backend_sg" {
  name        = "leave-app-backend-sg-${var.environment}"
  description = "Security group for backend service"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "leave-app-backend-sg-${var.environment}"
  }
}

resource "aws_s3_bucket" "frontend" {
  bucket = "leave-app-frontend-bucket-${var.environment}"

  versioning {
    enabled = true
  }

  tags = {
    Name = "leave-app-frontend-bucket-${var.environment}"
  }
}

resource "aws_cloudfront_distribution" "frontend" {
  origin {
    domain_name = aws_s3_bucket.frontend.bucket_regional_domain_name
    origin_id   = aws_s3_bucket.frontend.arn

    custom_origin_config {
      origin_protocol_policy = "http-only"
    }
  }

  enabled             = true
  is_ipv6_enabled     = true
  default_root_object = "index.html"

  default_cache_behavior {
    target_origin_id = aws_s3_bucket.frontend.arn
    viewer_protocol_policy = "redirect-to-https"

    allowed_methods  = ["GET", "HEAD", "OPTIONS"]
    cached_methods   = ["GET", "HEAD"]

    forwarded_values {
      query_string = false
      cookies {
        forward = "none"
      }
    }

    min_ttl = 3600
  }

  price_class = "PriceClass_100"

  tags = {
    Name = "leave-app-cloudfront-distribution-${var.environment}"
  }
}

resource "aws_db_instance" "postgresql" {
  allocated_storage    = 20
  engine               = "postgres"
  engine_version       = "13.3"
  instance_class       = "db.t3.micro"
  name                 = "leave-app-db-${var.environment}"
  username             = var.db_username
  password             = var.db_password
  parameter_group_name = "default.postgres13"
  db_subnet_group_name = aws_db_subnet_group.main.name
  vpc_security_group_ids = [aws_security_group.db_sg.id]

  tags = {
    Name = "leave-app-db-${var.environment}"
  }
}

resource "aws_db_subnet_group" "main" {
  name       = "leave-app-db-subnet-group-${var.environment}"
  subnet_ids = aws_subnet.public.*.id

  tags = {
    Name = "leave-app-db-subnet-group-${var.environment}"
  }
}

resource "aws_security_group" "db_sg" {
  name        = "leave-app-db-sg-${var.environment}"
  description = "Security group for database"
  vpc_id       = aws_vpc.main.id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "leave-app-db-sg-${var.environment}"
  }
}
