provider "aws" {
  version = "~> 3.42"
  region  = var.region
}

resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "public" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.1.0/24"
  availability_zone = "us-west-2a"
}

resource "aws_subnet" "private" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.2.0/24"
  availability_zone = "us-west-2a"
}

resource "aws_security_group" "backend_sg" {
  vpc_id = aws_vpc.main.id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_ecs_cluster" "leave_app_cluster" {
  name = "${var.env_name}-leave-app-cluster"
}

resource "aws_ecs_task_definition" "leave_app_task" {
  family                   = "leave-app-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode([{
    name      = "leave-app-container"
    image     = var.container_image
    essential = true
    portMappings = [
      {
        containerPort = 80
        hostPort     = 80
      }
    ]
  }])
}

resource "aws_ecs_service" "leave_app_service" {
  name            = "${var.env_name}-leave-app-service"
  cluster         = aws_ecs_cluster.leave_app_cluster.id
  task_definition = aws_ecs_task_definition.leave_app_task.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = [aws_subnet.private.id]
    security_groups = [aws_security_group.backend_sg.id]
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.leave_app_tg.arn
    container_name   = "leave-app-container"
    container_port   = 80
  }
}

resource "aws_lb" "leave_app_lb" {
  name               = "${var.env_name}-leave-app-lb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.backend_sg.id]
  subnets            = [aws_subnet.public.id]
}

resource "aws_lb_target_group" "leave_app_tg" {
  name     = "${var.env_name}-leave-app-tg"
  port     = 80
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
}

resource "aws_lb_listener" "leave_app_listener" {
  load_balancer_arn = aws_lb.leave_app_lb.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.leave_app_tg.arn
  }
}

resource "aws_s3_bucket" "leave_app_frontend" {
  bucket = "${var.env_name}-leave-app-frontend"
  acl    = "private"
}

resource "aws_cloudfront_distribution" "leave_app_distribution" {
  origin {
    domain_name = aws_s3_bucket.leave_app_frontend.bucket_regional_domain_name
    origin_id   = aws_s3_bucket.leave_app_frontend.arn
  }

  enabled             = true
  is_ipv6_enabled     = true
  default_root_object = "index.html"

  default_cache_behavior {
    target_origin_id = aws_s3_bucket.leave_app_frontend.arn
    viewer_protocol_policy = "redirect-to-https"
    allowed_methods  = ["GET", "HEAD", "OPTIONS"]
    cached_methods   = ["GET", "HEAD"]
    forwarded_values {
      query_string = false
      cookies {
        forward = "none"
      }
    }
  }

  price_class = "PriceClass_100"
}

resource "aws_db_instance" "leave_app_db" {
  allocated_storage    = 20
  engine               = "postgres"
  instance_class       = "db.t3.micro"
  name                 = "${var.env_name}-leave-app-db"
  username             = var.db_username
  password             = var.db_password
  parameter_group_name = "default.postgres13"
  db_subnet_group_name = aws_db_subnet_group.leave_app_db_subnet_group.name
  vpc_security_group_ids = [aws_security_group.backend_sg.id]
}

resource "aws_db_subnet_group" "leave_app_db_subnet_group" {
  name       = "${var.env_name}-leave-app-db-subnet-group"
  subnet_ids = [aws_subnet.private.id]
}
