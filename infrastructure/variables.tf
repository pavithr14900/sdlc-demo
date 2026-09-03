variable "region" {
  description = "The AWS region to deploy to"
  type        = string
}

variable "environment_name" {
  description = "The environment name for the deployment"
  type        = string
}

variable "availability_zone" {
  description = "The availability zone to deploy to"
  type        = string
}

variable "container_image" {
  description = "The container image to deploy"
  type        = string
}

variable "db_username" {
  description = "The database username"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "The database password"
  type        = string
  sensitive   = true
}
