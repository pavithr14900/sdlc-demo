# API Documentation

## Overview
**Base URL**: `http://localhost:5000` (development) | `https://api.example.com` (production)
**Response Format**: JSON
**Authentication**: Bearer token using Spring Security JWT
**Rate Limits**: 100 requests per minute

## Authentication

### Bearer Token
1. Obtain a token by logging in through the `/api/auth/login` endpoint with `username` and `password`.
2. Include in request headers: `Authorization: Bearer <token>`.
3. Token expires in 1 hour; refresh using the `/api/auth/refresh` endpoint.

### Example Request with Auth
```bash
curl -X GET http://localhost:5000/api/leaves \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json"
```

## Endpoints

### POST /api/leaves
Submit a new leave application.

**Request:**
- **Method**: POST
- **Path**: `/api/leaves`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "employeeId": "UUID, unique identifier for employee",
    "leaveType": "SICK, VACATION, PERSONAL",
    "startDate": "YYYY-MM-DD, start date of the leave",
    "endDate": "YYYY-MM-DD, end date of the leave"
  }
  ```

**Response:**
- **Status 201 (Created)**:
  ```json
  {
    "id": "UUID, unique identifier for leave request",
    "status": "PENDING"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X POST http://localhost:5000/api/leaves \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"123e4567-e89b-12d3-a456-426614174000","leaveType":"SICK","startDate":"2023-10-01","endDate":"2023-10-05"}'
```

### GET /api/leaves/{id}
Retrieve leave application details.

**Request:**
- **Method**: GET
- **Path**: `/api/leaves/{id}`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Query Parameters**:
  - `id` (required): `UUID, unique identifier for leave request`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "UUID, unique identifier for leave request",
    "employeeId": "UUID, unique identifier for employee",
    "leaveType": "SICK, VACATION, PERSONAL",
    "startDate": "YYYY-MM-DD, start date of the leave",
    "endDate": "YYYY-MM-DD, end date of the leave",
    "status": "PENDING, APPROVED, REJECTED"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Leave application not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/leaves/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### PUT /api/leaves/{id}/approve
Approve a leave application.

**Request:**
- **Method**: PUT
- **Path**: `/api/leaves/{id}/approve`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Query Parameters**:
  - `id` (required): `UUID, unique identifier for leave request`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "UUID, unique identifier for leave request",
    "status": "APPROVED"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Leave application not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X PUT http://localhost:5000/api/leaves/123e4567-e89b-12d3-a456-426614174000/approve \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### PUT /api/leaves/{id}/reject
Reject a leave application.

**Request:**
- **Method**: PUT
- **Path**: `/api/leaves/{id}/reject`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Query Parameters**:
  - `id` (required): `UUID, unique identifier for leave request`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "id": "UUID, unique identifier for leave request",
    "status": "REJECTED"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Leave application not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X PUT http://localhost:5000/api/leaves/123e4567-e89b-12d3-a456-426614174000/reject \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### GET /api/leaves/balance/{employeeId}
Get employee leave balance.

**Request:**
- **Method**: GET
- **Path**: `/api/leaves/balance/{employeeId}`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Query Parameters**:
  - `employeeId` (required): `UUID, unique identifier for employee`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "employeeId": "UUID, unique identifier for employee",
    "leaveType": "SICK, VACATION, PERSONAL",
    "balance": "integer, remaining leave balance"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Employee not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/leaves/balance/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

## Error Codes and Meanings

| Status | Code | Meaning | When It Occurs |
|--------|------|---------|----------------|
| 400 | BAD_REQUEST | Invalid input | Submit leave application with invalid employee ID |
| 401 | UNAUTHORIZED | Invalid credentials | Token missing or expired |
| 403 | FORBIDDEN | Access denied | User lacks required role |
| 404 | NOT_FOUND | Resource not found | Leave application or employee not found |
| 409 | CONFLICT | Duplicate or state conflict | Record already exists |
| 429 | RATE_LIMIT | Too many requests | Exceeded rate limit |
| 500 | INTERNAL_ERROR | Server error | Unexpected error, check logs |

## Rate Limits and Quotas
- 100 requests per minute: Exceeding this limit will result in a 429 RATE_LIMIT response.
- 1000 requests per hour: Exceeding this limit will result in a temporary block of further requests.

## Data Types and Formats
- **Timestamps**: ISO 8601 format (e.g., `2026-01-15T10:30:00Z`)
- **Booleans**: `true` or `false`
- **IDs**: UUID v4 or numeric, as documented per entity
- **Enums**: Specific values listed in request/response examples

## Pagination (if applicable)
This API does not support pagination.

## SDK Examples
No SDKs are provided for this API.
