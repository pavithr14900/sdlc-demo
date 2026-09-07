variable "region" {
  description = "The AWS region to deploy the infrastructure"
  type        = string
  default     = "us-west-2"
}

variable "environment" {
  description = "The environment name for the infrastructure"
  type        = string
  default     = "dev"
}

variable "instance_size" {
  description = "The instance size for the ECS Fargate tasks"
  type        = string
  default     = "large"
}

variable "database_credentials" {
  description = "Database credentials for the RDS PostgreSQL instance"
  type        = map(string)
  sensitive   = true
}

variable "container_image" {
  description = "The container image for the ECS Fargate service"
  type        = string
}
