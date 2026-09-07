variable "region" {
  description = "The AWS region to deploy to"
  type        = string
  default     = "us-west-2"
}

variable "environment" {
  description = "The environment name"
  type        = string
  default     = "dev"
}

variable "instance_size" {
  description = "The instance size for the ECS Fargate tasks"
  type        = string
  default     = "large"
}

variable "db_credentials" {
  description = "Database credentials"
  type        = map(string)
  sensitive   = true
  default     = {
    username = "admin"
    password = "password"
  }
}

variable "container_image" {
  description = "The container image for the backend service"
  type        = string
  default     = "my-backend-image:latest"
}
