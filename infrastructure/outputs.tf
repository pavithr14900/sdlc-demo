output "load_balancer_dns_name" {
  description = "The DNS name of the Application Load Balancer"
  value       = aws_lb.leave_app_lb.dns_name
}

output "cloudfront_domain_name" {
  description = "The domain name of the CloudFront distribution"
  value       = aws_cloudfront_distribution.leave_app_distribution.domain_name
}

output "rds_endpoint" {
  description = "The endpoint of the RDS PostgreSQL database"
  value       = aws_db_instance.leave_app_db.endpoint
}
