# API Documentation

## Overview
**Base URL**: `http://localhost:5000` (development) | `https://api.example.com` (production)
**Response Format**: JSON
**Authentication**: Bearer token using Spring Security JWT
**Rate Limits**: 100 requests per minute per user

## Authentication

### Bearer Token
1. Obtain a token by logging in via the `/api/auth/login` endpoint with `username` and `password`.
2. Include in request headers: `Authorization: Bearer <token>`.
3. Token expires in 1 hour; refresh using the `/api/auth/refresh` endpoint.

### Example Request with Auth
```bash
curl -X GET http://localhost:5000/api/leave-requests \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json"
```

## Endpoints

### POST /leave-requests
Submit a new leave request.

**Request:**
- **Method**: POST
- **Path**: `/api/leave-requests`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "employeeId": "UUID",
    "leaveType": "SICK|VACATION|PERSONAL",
    "startDate": "YYYY-MM-DD",
    "endDate": "YYYY-MM-DD"
  }
  ```

**Response:**
- **Status 201 (Created)**:
  ```json
  {
    "id": "UUID",
    "status": "PENDING"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X POST http://localhost:5000/api/leave-requests \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"123e4567-e89b-12d3-a456-426614174000","leaveType":"SICK","startDate":"2023-10-01","endDate":"2023-10-05"}'
```

### GET /leave-requests/{id}
Retrieve leave request details.

**Request:**
- **Method**: GET
- **Path**: `/api/leave-requests/{id}`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "UUID",
    "employeeId": "UUID",
    "leaveType": "SICK|VACATION|PERSONAL",
    "startDate": "YYYY-MM-DD",
    "endDate": "YYYY-MM-DD",
    "status": "PENDING|APPROVED|REJECTED"
  }
  ```
- **Status 404 (Not Found)**: Record doesn't exist
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/leave-requests/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### PATCH /leave-requests/{id}/approve
Approve a leave request.

**Request:**
- **Method**: PATCH
- **Path**: `/api/leave-requests/{id}/approve`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "UUID",
    "status": "APPROVED"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Record doesn't exist
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X PATCH http://localhost:5000/api/leave-requests/123e4567-e89b-12d3-a456-426614174000/approve \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### PATCH /leave-requests/{id}/reject
Reject a leave request.

**Request:**
- **Method**: PATCH
- **Path**: `/api/leave-requests/{id}/reject`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "UUID",
    "status": "REJECTED"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Record doesn't exist
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X PATCH http://localhost:5000/api/leave-requests/123e4567-e89b-12d3-a456-426614174000/reject \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### GET /leave-balance/{employeeId}
Get employee leave balance.

**Request:**
- **Method**: GET
- **Path**: `/api/leave-balance/{employeeId}`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "employeeId": "UUID",
    "leaveType": "SICK|VACATION|PERSONAL",
    "balance": "integer"
  }
  ```
- **Status 404 (Not Found)**: Record doesn't exist
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/leave-balance/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

## Error Codes and Meanings

| Status | Code | Meaning | When It Occurs |
|--------|------|---------|----------------|
| 400 | BAD_REQUEST | Invalid input | Missing or invalid required field |
| 401 | UNAUTHORIZED | Invalid credentials | Token missing or expired |
| 403 | FORBIDDEN | Access denied | User lacks required role |
| 404 | NOT_FOUND | Resource not found | Record doesn't exist |
| 409 | CONFLICT | Duplicate or state conflict | Record already exists |
| 429 | RATE_LIMIT | Too many requests | Exceeded rate limit |
| 500 | INTERNAL_ERROR | Server error | Unexpected error, check logs |

## Rate Limits and Quotas
- **100 requests per minute per user**: Exceeding this limit will result in a 429 RATE_LIMIT response.

## Data Types and Formats
- **Timestamps**: ISO 8601 format (e.g., `2026-01-15T10:30:00Z`)
- **Booleans**: `true` or `false`
- **IDs**: UUID v4 or numeric, as documented per entity
- **Enums**: Specific values listed in request/response examples

## Pagination (if applicable)
This API does not support pagination.

## SDK Examples
No SDKs are provided for this API.
