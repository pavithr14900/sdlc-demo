variable "region" {
  description = "The AWS region to deploy the infrastructure"
  type        = string
}

variable "env_name" {
  description = "The environment name for the infrastructure"
  type        = string
}

variable "container_image" {
  description = "The Docker image for the leave application"
  type        = string
}

variable "db_username" {
  description = "The username for the RDS PostgreSQL database"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "The password for the RDS PostgreSQL database"
  type        = string
  sensitive   = true
}
