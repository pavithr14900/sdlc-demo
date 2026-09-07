provider "aws" {
  version = "~> 3.0"
  region  = var.region
}

resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "public" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.1.0/24"
  availability_zone = "us-west-2a"
  map_public_ip_on_launch = true
}

resource "aws_ecs_cluster" "main" {
  name = "${var.environment}-leave-app-cluster"
}

resource "aws_ecs_task_definition" "backend" {
  family                   = "${var.environment}-leave-app-backend"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "512"
  memory                   = "1024"

  container_definitions = jsonencode([{
    name      = "leave-app-backend"
    image     = var.container_image
    essential = true
    portMappings = [{
      containerPort = 8080
      hostPort      = 8080
    }]
    environment = [
      { name = "SPRING_DATASOURCE_URL", value = "jdbc:postgresql://${aws_db_instance.leave_app_db.address}/${var.database_credentials.db_name}" },
      { name = "SPRING_DATASOURCE_USERNAME", value = var.database_credentials.db_username },
      { name = "SPRING_DATASOURCE_PASSWORD", value = var.database_credentials.db_password }
    ]
  }])
}

resource "aws_ecs_service" "backend" {
  name            = "${var.environment}-leave-app-backend-service"
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.backend.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = [aws_subnet.public.id]
    security_groups = [aws_security_group.backend_sg.id]
  }
}

resource "aws_security_group" "backend_sg" {
  vpc_id = aws_vpc.main.id

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
}

resource "aws_lb" "main" {
  name               = "${var.environment}-leave-app-lb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.backend_sg.id]
  subnets            = [aws_subnet.public.id]
}

resource "aws_lb_listener" "main" {
  load_balancer_arn = aws_lb.main.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.main.arn
  }
}

resource "aws_lb_target_group" "main" {
  name_prefix      = "${var.environment}-leave-app-tg"
  port             = 8080
  protocol         = "HTTP"
  vpc_id           = aws_vpc.main.id
  target_type      = "ip"
  health_check {
    path            = "/"
    port            = "traffic-port"
    protocol        = "HTTP"
    matcher         = "200"
    interval        = 30
    timeout         = 5
    healthy_threshold = 2
    unhealthy_threshold = 2
  }
}

resource "aws_db_instance" "leave_app_db" {
  allocated_storage    = 20
  engine               = "postgres"
  instance_class       = "db.t3.medium"
  name                 = "leave_app_db"
  username             = var.database_credentials.db_username
  password             = var.database_credentials.db_password
  parameter_group_name = "default.postgres12"
  db_subnet_group_name = aws_db_subnet_group.main.name
  db_name              = var.database_credentials.db_name
  publicly_accessible  = false
  skip_final_snapshot = true
}

resource "aws_db_subnet_group" "main" {
  name       = "${var.environment}-leave-app-db-subnet-group"
  subnet_ids = [aws_subnet.public.id]
}

resource "aws_s3_bucket" "frontend" {
  bucket = "${var.environment}-leave-app-frontend"
  acl    = "private"
}

resource "aws_cloudfront_distribution" "main" {
  origin {
    domain_name = aws_s3_bucket.frontend.bucket_regional_domain_name
    origin_id   = "s3-origin"
  }

  enabled             = true
  is_ipv6_enabled     = true
  default_root_object = "index.html"

  default_cache_behavior {
    target_origin_id = "s3-origin"
    viewer_protocol_policy = "redirect-to-https"
    allowed_methods  = ["GET", "HEAD", "OPTIONS"]
    cached_methods   = ["GET", "HEAD"]
    forwarded_values {
      query_string = false
      cookies {
        forward = "none"
      }
    }
    min_ttl         = 3600
    default_ttl     = 86400
    max_ttl         = 31536000
  }

  price_class = "PriceClass_100"
}

output "load_balancer_dns_name" {
  value = aws_lb.main.dns_name
}

output "cloudfront_domain_name" {
  value = aws_cloudfront_distribution.main.domain_name
}

output "rds_endpoint" {
  value = aws_db_instance.leave_app_db.endpoint
}
