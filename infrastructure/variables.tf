variable "region" {
  description = "The AWS region to deploy to"
  type        = string
}

variable "environment" {
  description = "The environment name"
  type        = string
}

variable "container_image" {
  description = "The Docker image for the backend service"
  type        = string
}

variable "db_username" {
  description = "The username for the PostgreSQL database"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "The password for the PostgreSQL database"
  type        = string
  sensitive   = true
}
