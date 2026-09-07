provider "aws" {
  region = var.region
}

resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "public" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.1.0/24"
  map_public_ip_on_launch = true
}

resource "aws_security_group" "alb" {
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

resource "aws_security_group" "ecs" {
  vpc_id = aws_vpc.main.id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    security_groups = [aws_security_group.alb.id]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_ecs_cluster" "main" {
  name = "leave-app-cluster-${var.environment}"
}

resource "aws_ecs_task_definition" "backend" {
  family                   = "leave-app-backend"
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
      hostPort      = 80
    }]
    secrets = [{
      name      = "DB_USERNAME"
      valueFrom = "arn:aws:secretsmanager:${var.region}:${data.aws_caller_identity.current.account_id}:secret:leave-app-db-user-${var.environment}"
    }, {
      name      = "DB_PASSWORD"
      valueFrom = "arn:aws:secretsmanager:${var.region}:${data.aws_caller_identity.current.account_id}:secret:leave-app-db-pass-${var.environment}"
    }]
  }])
}

resource "aws_ecs_service" "backend" {
  name            = "leave-app-backend-service-${var.environment}"
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.backend.arn
  desired_count   = 1
  launch_type     = "FARGATE"
  network_configuration {
    subnets         = [aws_subnet.public.id]
    security_groups = [aws_security_group.ecs.id]
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.backend.arn
    container_name   = "leave-app-backend"
    container_port   = 80
  }
}

resource "aws_lb" "main" {
  name               = "leave-app-alb-${var.environment}"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb.id]
  subnets            = [aws_subnet.public.id]
}

resource "aws_lb_target_group" "backend" {
  name     = "leave-app-backend-tg-${var.environment}"
  port     = 80
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
}

resource "aws_lb_listener" "backend" {
  load_balancer_arn = aws_lb.main.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.backend.arn
  }
}

resource "aws_s3_bucket" "frontend" {
  bucket = "leave-app-frontend-${var.environment}"
  acl    = "private"
}

resource "aws_s3_bucket_policy" "frontend" {
  bucket = aws_s3_bucket.frontend.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action    = "s3:GetObject"
        Effect    = "Allow"
        Resource  = "${aws_s3_bucket.frontend.arn}/*",
        Principal = "*"
      }
    ]
  })
}

resource "aws_cloudfront_distribution" "frontend" {
  origin {
    domain_name = aws_s3_bucket.frontend.bucket_regional_domain_name
    origin_id   = aws_s3_bucket.frontend.bucket_region
  }

  enabled             = true
  is_ipv6_enabled     = true
  default_root_object = "index.html"

  default_cache_behavior {
    target_origin_id = aws_s3_bucket.frontend.bucket_region
    viewer_protocol_policy = "redirect-to-https"
    allowed_methods  = ["GET", "HEAD"]
    cached_methods  = ["GET", "HEAD"]
    forwarded_values {
      query_string = false
      cookies {
        forward = "none"
      }
    }
    min_ttl = 3600
  }

  price_class = "PriceClass_100"
}

resource "aws_db_instance" "main" {
  allocated_storage    = 20
  engine              = "postgres"
  instance_class      = "db.t3.micro"
  name                = "leave-app-db-${var.environment}"
  username            = var.db_credentials.username
  password            = var.db_credentials.password
  parameter_group_name = "default.postgres13"
  db_subnet_group_name = aws_db_subnet_group.main.name
  publicly_accessible = false
  vpc_security_group_ids = [aws_security_group.ecs.id]
}

resource "aws_db_subnet_group" "main" {
  name       = "leave-app-db-subnet-group-${var.environment}"
  subnet_ids = [aws_subnet.public.id]
}

resource "aws_secrets_manager_secret" "db_user" {
  name = "leave-app-db-user-${var.environment}"
}

resource "aws_secrets_manager_secret_version" "db_user" {
  secret_id     = aws_secrets_manager_secret.db_user.id
  secret_string = var.db_credentials.username
}

resource "aws_secrets_manager_secret" "db_pass" {
  name = "leave-app-db-pass-${var.environment}"
}

resource "aws_secrets_manager_secret_version" "db_pass" {
  secret_id     = aws_secrets_manager_secret.db_pass.id
  secret_string = var.db_credentials.password
}
