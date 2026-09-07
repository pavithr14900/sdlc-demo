# API Documentation

## Overview
**Base URL**: `http://localhost:5000` (development) | `https://api.example.com` (production)
**Response Format**: JSON
**Authentication**: Bearer token
**Rate Limits**: 100 requests per minute

## Authentication

### Bearer Token
1. Obtain a token by logging in through the `/auth/login` endpoint with `username` and `password`.
2. Include in request headers: `Authorization: Bearer <token>`.
3. Token expires in 1 hour; refresh using the `/auth/refresh` endpoint.

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
    "success": true,
    "data": {
      "id": 1,
      "status": "PENDING"
    },
    "message": "Leave request submitted successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 409 (Conflict)**: Duplicate leave request

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
    "success": true,
    "data": {
      "id": 1,
      "employeeId": 1,
      "leaveType": "SICK",
      "startDate": "2023-12-01",
      "endDate": "2023-12-05",
      "status": "PENDING"
    },
    "message": "Leave request retrieved successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Leave request not found

**Example:**
```bash
curl -X GET http://localhost:5000/api/leave-requests/1 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### GET /api/leave-requests
List all leave requests.

**Request:**
- **Method**: GET
- **Path**: `/api/leave-requests`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": [
      {
        "id": 1,
        "employeeId": 1,
        "leaveType": "SICK",
        "startDate": "2023-12-01",
        "endDate": "2023-12-05",
        "status": "PENDING"
      }
    ],
    "message": "Leave requests retrieved successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions

**Example:**
```bash
curl -X GET http://localhost:5000/api/leave-requests \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### PUT /api/leave-requests/{id}/approve
Approve a leave request.

**Request:**
- **Method**: PUT
- **Path**: `/api/leave-requests/{id}/approve`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "id": 1,
      "status": "APPROVED"
    },
    "message": "Leave request approved successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Leave request not found

**Example:**
```bash
curl -X PUT http://localhost:5000/api/leave-requests/1/approve \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### PUT /api/leave-requests/{id}/reject
Reject a leave request.

**Request:**
- **Method**: PUT
- **Path**: `/api/leave-requests/{id}/reject`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "id": 1,
      "status": "REJECTED"
    },
    "message": "Leave request rejected successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Leave request not found

**Example:**
```bash
curl -X PUT http://localhost:5000/api/leave-requests/1/reject \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### DELETE /api/leave-requests/{id}
Cancel a leave request.

**Request:**
- **Method**: DELETE
- **Path**: `/api/leave-requests/{id}`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "id": 1,
      "status": "CANCELED"
    },
    "message": "Leave request canceled successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Leave request not found

**Example:**
```bash
curl -X DELETE http://localhost:5000/api/leave-requests/1 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

## Error Codes and Meanings

| Status | Code | Meaning | When It Occurs |
|--------|------|---------|----------------|
| 400 | BAD_REQUEST | Invalid input | Submitting a leave request with missing or invalid fields |
| 401 | UNAUTHORIZED | Invalid credentials | Token missing or expired |
| 403 | FORBIDDEN | Access denied | User lacks required role |
| 404 | NOT_FOUND | Resource not found | Leave request not found |
| 409 | CONFLICT | Duplicate or state conflict | Leave request already exists |
| 429 | RATE_LIMIT | Too many requests | Exceeded rate limit |
| 500 | INTERNAL_ERROR | Server error | Unexpected error, check logs |

## Rate Limits and Quotas
- **100 requests per minute**: Exceeding this limit will result in a 429 Too Many Requests response.

## Data Types and Formats
- **Timestamps**: ISO 8601 format (e.g., `2026-01-15T10:30:00Z`)
- **Booleans**: `true` or `false`
- **IDs**: UUID v4 or numeric, as documented per entity
- **Enums**: Specific values listed in request/response examples

## Pagination (if applicable)
This API does not support pagination.

## SDK Examples
No SDKs are provided for this API.
