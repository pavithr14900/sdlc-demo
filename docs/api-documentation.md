# API Documentation

## Overview
**Base URL**: `http://localhost:5000` (development) | `https://api.example.com` (production)
**Response Format**: JSON
**Authentication**: Bearer token
**Rate Limits**: 100 requests per minute

## Authentication

### Bearer Token
1. Obtain a token by logging in through the `/api/auth/login` endpoint.
2. Include in request headers: `Authorization: Bearer <token>`
3. Token expires in 1 hour; refresh using the `/api/auth/refresh` endpoint.

### Example Request with Auth
```bash
curl -X GET http://localhost:5000/api/leave-requests \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json"
```

## Endpoints

### POST /api/leave-requests
Submit a new leave request.

**Request:**
- **Method**: POST
- **Path**: `/api/leave-requests`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "employeeId": "1",
    "leaveType": "SICK",
    "startDate": "2023-12-01",
    "endDate": "2023-12-05"
  }
  ```

**Response:**
- **Status 201 (Created)**:
  ```json
  {
    "id": "1",
    "status": "PENDING"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 500 (Internal Server Error)**: Unexpected error

**Example:**
```bash
curl -X POST http://localhost:5000/api/leave-requests \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"1","leaveType":"SICK","startDate":"2023-12-01","endDate":"2023-12-05"}'
```

### GET /api/leave-requests/{id}
Retrieve a leave request by ID.

**Request:**
- **Method**: GET
- **Path**: `/api/leave-requests/{id}`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "1",
    "employeeId": "1",
    "leaveType": "SICK",
    "startDate": "2023-12-01",
    "endDate": "2023-12-05",
    "status": "PENDING"
  }
  ```
- **Status 404 (Not Found)**: Leave request not found
- **Status 500 (Internal Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/leave-requests/1 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### PATCH /api/leave-requests/{id}/approve
Approve a leave request.

**Request:**
- **Method**: PATCH
- **Path**: `/api/leave-requests/{id}/approve`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "comment": "Approved by manager"
  }
  ```

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "1",
    "status": "APPROVED"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 404 (Not Found)**: Leave request not found
- **Status 500 (Internal Server Error)**: Unexpected error

**Example:**
```bash
curl -X PATCH http://localhost:5000/api/leave-requests/1/approve \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"comment":"Approved by manager"}'
```

### PATCH /api/leave-requests/{id}/reject
Reject a leave request.

**Request:**
- **Method**: PATCH
- **Path**: `/api/leave-requests/{id}/reject`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "comment": "Rejected due to insufficient balance"
  }
  ```

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "1",
    "status": "REJECTED"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 404 (Not Found)**: Leave request not found
- **Status 500 (Internal Server Error)**: Unexpected error

**Example:**
```bash
curl -X PATCH http://localhost:5000/api/leave-requests/1/reject \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"comment":"Rejected due to insufficient balance"}'
```

### GET /api/employees/{employeeId}/leave-balance
Get leave balance for an employee.

**Request:**
- **Method**: GET
- **Path**: `/api/employees/{employeeId}/leave-balance`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "employeeId": "1",
    "leaveType": "SICK",
    "availableLeave": 10
  }
  ```
- **Status 404 (Not Found)**: Employee not found
- **Status 500 (Internal Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/employees/1/leave-balance \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

## Error Codes and Meanings

| Status | Code | Meaning | When It Occurs |
|--------|------|---------|----------------|
| 400 | BAD_REQUEST | Invalid input | Submitting leave request with missing `employeeId` |
| 401 | UNAUTHORIZED | Invalid credentials | Token missing or expired |
| 403 | FORBIDDEN | Access denied | User lacks required role |
| 404 | NOT_FOUND | Resource not found | Leave request or employee not found |
| 409 | CONFLICT | Duplicate or state conflict | Leave request already approved or rejected |
| 429 | RATE_LIMIT | Too many requests | Exceeded rate limit |
| 500 | INTERNAL_ERROR | Server error | Unexpected error, check logs |

## Rate Limits and Quotas
- **100 requests per minute**: Exceeding this limit will result in a 429 RATE_LIMIT response.

## Data Types and Formats
- **Timestamps**: ISO 8601 format (e.g., `2026-01-15T10:30:00Z`)
- **Booleans**: `true` or `false`
- **IDs**: UUID v4 or numeric, as documented per entity
- **Enums**: Specific values listed in request/response examples

## Pagination (if applicable)
This API does not support pagination.

## SDK Examples
No SDKs are provided for this API.
