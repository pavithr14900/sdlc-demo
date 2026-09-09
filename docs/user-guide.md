# Employee Leave Application Documentation

## Requirement
Build an employee leave application for our organization.

## Architecture
### Architecture and Diagram Summary

- **Frontend Layer**: User interface for employees to submit leave requests and for managers to approve them.
- **API Layer**: RESTful API endpoints for handling leave requests and approvals.
- **Application Services Layer**: Business logic for processing leave requests, approvals, and notifications.
- **Persistence Layer**: Data storage for leave requests, employee details, and leave balances.
- **Security Layer**: Authentication and authorization mechanisms to secure the application.
- **External Systems**: Integration with notification services for sending alerts.

### Code Design

- `com.example.leave.api`: REST controllers for handling HTTP requests.
- `com.example.leave.service`: Service classes for business logic.
- `com.example.leave.repository`: Data access objects for interacting with the database.
- `com.example.leave.model`: Entity classes representing leave requests and employees.
- `com.example.leave.security`: Security configurations and user details service.

### Implementation Plan

#### Phase 1: Setup
**Tasks:**
- Set up the development environment.
- Initialize the Spring Boot project.
- Configure PostgreSQL database.
**Dependencies:** None
**Expected Outcome:** Development environment ready with database configured.

#### Phase 2: Database
**Tasks:**
- Design the database schema.
- Create entity classes.
- Set up Spring Data JPA repositories.
**Dependencies:** None
**Expected Outcome:** Database schema designed and entity classes created.

#### Phase 3: Domain
**Tasks:**
- Implement leave request entity.
- Implement employee and manager entities.
- Define leave types and statuses.
**Dependencies:** Database schema
**Expected Outcome:** Domain model implemented.

#### Phase 4: Business Logic
**Tasks:**
- Implement leave request service.
- Implement leave approval service.
- Implement leave balance service.
**Dependencies:** Domain model
**Expected Outcome:** Business logic implemented.

#### Phase 5: APIs
**Tasks:**
- Create REST controllers for leave requests.
- Create REST controllers for leave approvals.
- Implement request and response DTOs.
**Dependencies:** Business logic
**Expected Outcome:** API endpoints implemented.

#### Phase 6: Security
**Tasks:**
- Implement user authentication.
- Define roles and permissions.
- Secure API endpoints.
**Dependencies:** APIs
**Expected Outcome:** Application secured with authentication and authorization.

## API Design
### Endpoints

| Method | Path | Purpose | Request Body | Response Body | Success Status | Error Statuses |
|--------|------|---------|--------------|---------------|----------------|----------------|
| POST   | /leave-requests | Submit a new leave request | `{"employeeId":1,"leaveType":"SICK","startDate":"2023-12-01","endDate":"2023-12-05"}` | `{"id":1,"status":"PENDING"}` | 201 | 400, 404, 500 |
| GET    | /leave-requests/{id} | Retrieve a leave request by ID | N/A | `{"id":1,"employeeId":1,"leaveType":"SICK","startDate":"2023-12-01","endDate":"2023-12-05","status":"PENDING"}` | 200 | 404, 500 |
| PATCH  | /leave-requests/{id}/approve | Approve a leave request | `{"comment":"Approved by manager"}` | `{"id":1,"status":"APPROVED"}` | 200 | 400, 404, 500 |
| PATCH  | /leave-requests/{id}/reject | Reject a leave request | `{"comment":"Rejected due to insufficient balance"}` | `{"id":1,"status":"REJECTED"}` | 200 | 400, 404, 500 |
| GET    | /employees/{employeeId}/leave-balance | Get leave balance for an employee | N/A | `{"employeeId":1,"leaveType":"SICK","availableLeave":10}` | 200 | 404, 500 |

### Validation and Error Handling

#### Field Level Validation Rules

- `employeeId`: Must be a valid employee ID.
- `leaveType`: Must be one of the predefined leave types (SICK, VACATION, etc.).
- `startDate`: Must be a valid date and not in the past.
- `endDate`: Must be a valid date and after the startDate.
- `comment`: Must be a non-empty string when approving or rejecting.

#### Standard Error Response Envelope

```json
{
  "timestamp": "2023-10-05T12:34:56.789Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid leave request",
  "details": "The leave request is invalid due to..."
}
```

#### Business Failures to HTTP Status Codes

| Business Failure | HTTP Status Code |
|------------------|------------------|
| Invalid request data | 400 |
| Leave request not found | 404 |
| Internal server error | 500 |

## Data Model
### Model

| Table         | Column     | Data Type | Nullable | Key | Default | Description                  |
|---------------|------------|-----------|----------|-----|---------|------------------------------|
| Employee      | id         | UUID      | No       | PK  | -       | Unique identifier for employee |
|               | name       | VARCHAR   | No       | -   | -       | Employee's name              |
|               | email      | VARCHAR   | No       | -   | -       | Employee's email             |
|               | created_at | TIMESTAMP | No       | -   | CURRENT_TIMESTAMP | Record creation timestamp    |
|               | updated_at | TIMESTAMP | No       | -   | CURRENT_TIMESTAMP | Record last update timestamp |
| LeaveRequest  | id         | UUID      | No       | PK  | -       | Unique identifier for leave request |
|               | employee_id | UUID      | No       | FK  | -       | Employee who requested the leave |
|               | start_date  | DATE      | No       | -   | -       | Start date of the leave      |
|               | end_date    | DATE      | No       | -   | -       | End date of the leave       |
|               | status      | VARCHAR   | No       | -   | 'PENDING' | Status of the leave request |
|               | created_at  | TIMESTAMP | No       | -   | CURRENT_TIMESTAMP | Record creation timestamp    |
|               | updated_at  | TIMESTAMP | No       | -   | CURRENT_TIMESTAMP | Record last update timestamp |

### ER Diagram

```json
{
  "entities": [
    { "name": "Employee", "fields": ["id", "name", "email"] },
    { "name": "LeaveRequest", "fields": ["id", "employee_id", "start_date", "end_date", "status"] }
  ],
  "relationships": [
    { "from": "Employee", "to": "LeaveRequest", "label": "1:N" }
  ]
}
```

### Schema Script

```sql
CREATE TABLE Employee (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE LeaveRequest (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    employee_id UUID NOT NULL REFERENCES Employee(id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

### Constraints and Indexes

- **Primary Keys**: `Employee(id)`, `LeaveRequest(id)`.
- **Foreign Keys**: `LeaveRequest(employee_id)` references `Employee(id)`.
- **Unique Constraints**: `Employee(email)`.
- **Check Constraints**: `LeaveRequest(status)` should be one of 'PENDING', 'APPROVED', 'REJECTED'.
- **Indexes**:
  - `CREATE INDEX idx_employee_email ON Employee(email);`
  - `CREATE INDEX idx_leaverequest_employee_id ON LeaveRequest(employee_id);`
  - `CREATE INDEX idx_leaverequest_status ON LeaveRequest(status);`
  - Reason: To improve the performance of queries filtering by email, employee ID, and leave request status.

## User Guide

### Overview
This application is designed for employees and managers within our organization to manage leave requests. Employees can submit leave requests, while managers can approve or reject these requests. The application ensures that leave requests are processed efficiently and that employees have an accurate view of their leave balances.

### Getting Started
1. **Login**: Employees and managers can log in using their credentials.
2. **Submit Leave Request**: Employees navigate to the leave request section and fill out the necessary details.
3. **Verify Success**: Upon successful submission, employees should see a confirmation message with the status "PENDING".

### User Roles and Permissions

#### Employee
- **Capabilities**: Submit leave requests, view leave balance.
- **Typical workflows**: Submit a leave request, view pending leave requests.
- **Restrictions**: Cannot approve or reject leave requests.

#### Manager
- **Capabilities**: Approve or reject leave requests, view all leave requests.
- **Typical workflows**: Review a leave request, approve or reject it.
- **Restrictions**: Cannot modify leave balance.

#### Admin
- **Capabilities**: Manage user accounts, view all leave requests and balances.
- **Typical workflows**: Create new employee accounts, reset passwords.
- **Restrictions**: None.

### Core Workflows

#### Submit Leave Request
This workflow allows employees to request leave. Use this when you need time off for personal reasons.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu.
2. Click the "Submit Leave Request" button to open the form.
3. Enter fields with values like:
   - `employeeId: 1`
   - `leaveType: SICK`
   - `startDate: 2023-12-01`
   - `endDate: 2023-12-05`
4. Click the "Submit" button to submit the request.
5. **Expected outcome**: A confirmation message with the status "PENDING".

**Common mistakes:**
- **Missing `employeeId`**: Ensure you enter a valid employee ID.
- **Invalid `leaveType`**: Use one of the predefined leave types (SICK, VACATION, etc.).

#### Approve Leave Request
This workflow allows managers to approve leave requests. Use this when you need to approve a leave request submitted by an employee.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu.
2. Click on the leave request you want to approve.
3. Click the "Approve" button to open the approval dialog.
4. Enter a comment like "Approved by manager".
5. Click the "Approve" button to submit.
6. **Expected outcome**: The leave request status updates to "APPROVED".

**Tips:**
- Use clear and concise comments for better tracking.
- Ensure the leave dates do not conflict with other approved leaves.

#### Reject Leave Request
This workflow allows managers to reject leave requests. Use this when you need to reject a leave request submitted by an employee.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu.
2. Click on the leave request you want to reject.
3. Click the "Reject" button to open the rejection dialog.
4. Enter a comment like "Rejected due to insufficient balance".
5. Click the "Reject" button to submit.
6. **Expected outcome**: The leave request status updates to "REJECTED".

**Tips:**
- Provide a clear reason for rejection to help employees understand.
- Ensure the rejection is communicated promptly.

### Data Management

#### Creating Records
To create a new leave request, navigate to the "Leave Requests" section, click "Submit Leave Request", and fill out the required fields. Required fields include `employeeId`, `leaveType`, `startDate`, and `endDate`.

#### Editing Records
Editing records is not supported in this application. Once a leave request is submitted, it can only be approved or rejected.

#### Deleting Records
Deleting records is not supported. Instead, leave requests can be rejected, which effectively denies the request.

#### Filtering and Search
You can filter and search leave requests by employee ID, leave type, and status using the filters available in the "Leave Requests" section.

### Reports and Exports
Reports on leave requests and balances can be generated from the "Reports" section. Exports are available in CSV and PDF formats.

### Frequently Asked Questions

**Q: How can I check my leave balance?**
A: Navigate to the "Leave Balance" section to view your available leave balance.

**Q: Can I edit a submitted leave request?**
A: No, you cannot edit a submitted leave request. You can only approve or reject it.

**Q: Can I <feature question>?**
A: No, this feature is not available in the current version.

### Troubleshooting

#### Common Issues
