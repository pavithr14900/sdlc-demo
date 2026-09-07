output "backend_load_balancer_dns" {
  description = "The DNS name of the backend load balancer"
  value       = aws_lb.backend.dns_name
}

output "frontend_cloudfront_domain" {
  description = "The CloudFront domain name for the frontend"
  value       = aws_cloudfront_distribution.frontend.domain_name
}

output "rds_endpoint" {
  description = "The RDS PostgreSQL endpoint"
  value       = aws_db_instance.postgresql.endpoint
}
