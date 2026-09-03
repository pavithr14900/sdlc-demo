output "load_balancer_dns_name" {
  description = "The DNS name of the load balancer"
  value       = aws_lb.main.dns_name
}

output "cloudfront_domain_name" {
  description = "The CloudFront domain name"
  value       = aws_cloudfront_distribution.frontend.domain_name
}

output "rds_endpoint" {
  description = "The RDS endpoint"
  value       = aws_db_instance.main.endpoint
}
