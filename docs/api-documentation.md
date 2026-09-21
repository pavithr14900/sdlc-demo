# API Documentation

## Overview
**Base URL**: `http://localhost:5000` (development) | `https://api.example.com` (production)
**Response Format**: JSON
**Authentication**: Bearer token (JWT)
**Rate Limits**: 100 requests per minute

## Authentication

### Bearer Token
1. Obtain a token by logging in via the `/api/auth/login` endpoint.
2. Include the token in request headers: `Authorization: Bearer <token>`.
3. Token expires in 1 hour; refresh using the `/api/auth/refresh` endpoint.

### Example Request with Auth
```bash
curl -X GET http://localhost:5000/api/expenses \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json"
```

## Endpoints

### POST /api/expenses
Submit an expense for reimbursement.

**Request:**
- **Method**: POST
- **Path**: `/api/expenses`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "employeeId": "UUID",
    "amount": "Decimal",
    "description": "String",
    "receiptUrl": "String"
  }
  ```

**Response:**
- **Status 201 (Created)**:
  ```json
  {
    "success": true,
    "data": {
      "id": "UUID",
      "status": "SUBMITTED"
    },
    "message": "Expense submitted successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X POST http://localhost:5000/api/expenses \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"123e4567-e89b-12d3-a456-426614174000","amount":100,"description":"Lunch","receiptUrl":"http://example.com/receipt.jpg"}'
```

### POST /api/travel-requests
Submit a travel request for approval.

**Request:**
- **Method**: POST
- **Path**: `/api/travel-requests`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "employeeId": "UUID",
    "destination": "String",
    "startDate": "YYYY-MM-DD",
    "endDate": "YYYY-MM-DD",
    "purpose": "String"
  }
  ```

**Response:**
- **Status 201 (Created)**:
  ```json
  {
    "success": true,
    "data": {
      "id": "UUID",
      "status": "SUBMITTED"
    },
    "message": "Travel request submitted successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X POST http://localhost:5000/api/travel-requests \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"123e4567-e89b-12d3-a456-426614174000","destination":"Paris","startDate":"2023-10-01","endDate":"2023-10-05","purpose":"Conference"}'
```

### POST /api/expenses/<id>/approve
Approve an expense submission.

**Request:**
- **Method**: POST
- **Path**: `/api/expenses/<id>/approve`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "managerId": "UUID",
    "approvalComment": "String"
  }
  ```

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "id": "UUID",
      "status": "APPROVED"
    },
    "message": "Expense approved successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Expense not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X POST http://localhost:5000/api/expenses/123e4567-e89b-12d3-a456-426614174000/approve \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"managerId":"123e4567-e89b-12d3-a456-426614174001","approvalComment":"Approved"}'
```

### POST /api/expenses/<id>/reject
Reject an expense submission.

**Request:**
- **Method**: POST
- **Path**: `/api/expenses/<id>/reject`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "managerId": "UUID",
    "rejectionComment": "String"
  }
  ```

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "id": "UUID",
      "status": "REJECTED"
    },
    "message": "Expense rejected successfully"
  }
  ```
- **Status 400 (Bad Request)**: Missing or invalid required field
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Expense not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X POST http://localhost:5000/api/expenses/123e4567-e89b-12d3-a456-426614174000/reject \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"managerId":"123e4567-e89b-12d3-a456-426614174001","rejectionComment":"Not eligible"}'
```

### GET /api/expenses
Get all expenses submitted by employees.

**Request:**
- **Method**: GET
- **Path**: `/api/expenses`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": [
      {
        "id": "UUID",
        "employeeId": "UUID",
        "amount": "Decimal",
        "description": "String",
        "status": "SUBMITTED"
      }
    ],
    "message": "Expenses retrieved successfully"
  }
  ```
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/expenses \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### GET /api/expenses/<id>
Get an expense by its ID.

**Request:**
- **Method**: GET
- **Path**: `/api/expenses/<id>`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "id": "UUID",
      "employeeId": "UUID",
      "amount": "Decimal",
      "description": "String",
      "status": "SUBMITTED"
    },
    "message": "Expense retrieved successfully"
  }
  ```
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Expense not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/expenses/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### GET /api/travel-requests
Get all travel requests submitted by employees.

**Request:**
- **Method**: GET
- **Path**: `/api/travel-requests`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": [
      {
        "id": "UUID",
        "employeeId": "UUID",
        "destination": "String",
        "startDate": "YYYY-MM-DD",
        "endDate": "YYYY-MM-DD",
        "status": "SUBMITTED"
      }
    ],
    "message": "Travel requests retrieved successfully"
  }
  ```
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/travel-requests \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### GET /api/travel-requests/<id>
Get a travel request by its ID.

**Request:**
- **Method**: GET
- **Path**: `/api/travel-requests/<id>`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "id": "UUID",
      "employeeId": "UUID",
      "destination": "String",
      "startDate": "YYYY-MM-DD",
      "endDate": "YYYY-MM-DD",
      "status": "SUBMITTED"
    },
    "message": "Travel request retrieved successfully"
  }
  ```
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 404 (Not Found)**: Travel request not found
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://localhost:5000/api/travel-requests/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json"
```

### GET /api/reports/expenses
Get department-wise spend report.

**Request:**
- **Method**: GET
- **Path**: `/api/reports/expenses`
- **Headers**: `Authorization: Bearer <token>`, `Content-Type: application/json`

**Response:**
- **Status 200 (OK)**:
  ```json
  {
    "success": true,
    "data": {
      "department": "String",
      "totalSpend": "Decimal",
      "policyViolations": "Integer",
      "outstandingReimbursements": "Decimal"
    },
    "message": "Department-wise spend report retrieved successfully"
  }
  ```
- **Status 401 (Unauthorized)**: Invalid or expired token
- **Status 403 (Forbidden)**: Insufficient permissions
- **Status 500 (Server Error)**: Unexpected error

**Example:**
```bash
curl -X GET http://
