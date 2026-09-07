REQUIREMENT
===========
Build an employee leave application for our organization.

ARCHITECTURE
============
## 4. ARCHITECTURE DESIGN

### Architecture and Diagram Summary

- **Frontend Layer**: A web-based interface for employees to submit leave requests and view their leave status.
- **API Layer**: RESTful APIs to handle requests from the frontend and communicate with the application services.
- **Application Services Layer**: Core business logic for processing leave requests, approvals, and balance tracking.
- **Persistence Layer**: Database schema and data access objects to store and retrieve leave data.
- **Security Layer**: Authentication and authorization mechanisms to ensure only authorized users can access the system.
- **External Systems**: Integration with existing HR systems for user data and leave policy enforcement.

### Code Design

- `com.example.leave.web`: Controllers and request mappings for the API.
- `com.example.leave.service`: Service classes implementing business logic.
- `com.example.leave.repository`: JPA repositories for database access.
- `com.example.leave.model`: Domain objects representing leave requests and employees.
- `com.example.leave.security`: Security configurations and user details service.
- `com.example.leave.validation`: Custom validation annotations and validators.

### Implementation Plan

#### Phase 1: Setup
**Tasks:**
- Initialize the project with Spring Boot
- Configure PostgreSQL database
- Set up Spring Security
**Dependencies:** None
**Expected Outcome:** A running Spring Boot application with a configured database and basic security setup.

#### Phase 2: Database
**Tasks:**
- Design and create database schema
- Implement JPA entities and repositories
**Dependencies:** Phase 1
**Expected Outcome:** A database schema ready for leave data storage and retrieval.

#### Phase 3: Domain
**Tasks:**
- Define domain models for leave requests and employees
- Implement domain logic for leave balance calculations
**Dependencies:** Phase 2
**Expected Outcome:** Domain models and logic for managing leave requests and employee data.

#### Phase 4: Business Logic
**Tasks:**
- Implement service layer for leave request processing
- Define leave approval workflow
**Dependencies:** Phase 3
**Expected Outcome:** Complete business logic for leave request submission and approval.

#### Phase 5: APIs
**Tasks:**
- Develop RESTful APIs for leave requests
- Implement API validation and error handling
**Dependencies:** Phase 4
**Expected Outcome:** Functional APIs for leave request submission and status checking.

#### Phase 6: Security and Deployment
**Tasks:**
- Configure role-based access control
- Prepare application for deployment
**Dependencies:** Phase 5
**Expected Outcome:** A secure application ready for production deployment.

API DESIGN
==========
## 5. API DESIGN

### Endpoints

| Method | Path | Purpose | Request Body | Response Body | Success Status | Error Statuses |
|--------|------|---------|--------------|---------------|----------------|----------------|
| POST   | /leave-requests | Submit a new leave request | `{"employeeId":1,"leaveType":"SICK","startDate":"2023-10-01","endDate":"2023-10-05"}` | `{"id":1,"status":"PENDING"}` | 201 | 400, 404, 500 |
| GET    | /leave-requests/{id} | Retrieve a leave request by ID | N/A | `{"id":1,"employeeId":1,"leaveType":"SICK","startDate":"2023-10-01","endDate":"2023-10-05","status":"PENDING"}` | 200 | 404 |
| PATCH  | /leave-requests/{id}/approve | Approve a leave request | N/A | `{"id":1,"status":"APPROVED"}` | 200 | 400, 404, 403, 500 |
| PATCH  | /leave-requests/{id}/reject | Reject a leave request | N/A | `{"id":1,"status":"REJECTED"}` | 200 | 400, 404, 403, 500 |
| GET    | /leave-balance/{employeeId} | Get leave balance for an employee | N/A | `{"employeeId":1,"leaveType":"SICK","balance":10}` | 200 | 404 |

### Validation and Error Handling

#### Field Level Validation Rules

- `employeeId`: Required, must be a valid employee ID.
- `leaveType`: Required, must be one of ["SICK", "VACATION", "PERSONAL"].
- `startDate`: Required, must be a valid date, must not be in the past.
- `endDate`: Required, must be a valid date, must be after startDate.
- `status`: Must be one of ["PENDING", "APPROVED", "REJECTED"].

#### Standard Error Response Envelope

```json
{
  "timestamp": "2023-10-01T12:34:56.789Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid leave request.",
  "details": "The leave request contains invalid data."
}
```

#### Business Failures to HTTP Status Codes

| Business Failure | HTTP Status Code |
|------------------|------------------|
| Invalid request data | 400 |
| Leave request not found | 404 |
| Unauthorized access | 403 |
| Internal server error | 500 |

DATA MODEL
==========
## 6. DATA MODEL

### Model

| Table         | Column   | Data Type | Nullable | Key | Default | Description                           |
|---------------|-----------|-----------|----------|-----|---------|---------------------------------------|
| Employee      | id        | UUID      | No       | PK  | -       | Unique identifier for the employee    |
| Employee      | name      | VARCHAR   | No       | -   | -       | Employee's full name                  |
| Employee      | email     | VARCHAR   | No       | -   | -       | Employee's email address              |
| LeaveRequest  | id        | UUID      | No       | PK  | -       | Unique identifier for the leave request |
| LeaveRequest  | employee_id | UUID     | No       | FK  | -       | Employee who requested the leave      |
| LeaveRequest  | start_date | DATE      | No       | -   | -       | Start date of the leave               |
| LeaveRequest  | end_date   | DATE      | No       | -   | -       | End date of the leave                 |
| LeaveRequest  | status    | VARCHAR   | No       | -   | -       | Status of the leave request           |
| LeaveRequest  | created_at| TIMESTAMP | No       | -   | CURRENT_TIMESTAMP | Creation timestamp                   |
| LeaveRequest  | updated_at| TIMESTAMP | No       | -   | CURRENT_TIMESTAMP | Last update timestamp                |

### ER Diagram

```json
{
  "entities": [
    { "name": "Employee", "fields": ["id", "name", "email"] },
    { "name": "LeaveRequest", "fields": ["id", "employee_id", "start_date", "end_date", "status", "created_at", "updated_at"] }
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
    email VARCHAR NOT NULL UNIQUE
);

CREATE TABLE LeaveRequest (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    employee_id UUID NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR NOT NULL CHECK (status IN ('Pending', 'Approved', 'Rejected')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE
);

CREATE INDEX idx_leaverequest_employee_id ON LeaveRequest(employee_id);
CREATE INDEX idx_leaverequest_status ON LeaveRequest(status);
```

### Constraints and Indexes

- **Primary Keys**: `id` in `Employee` and `LeaveRequest`.
- **Foreign Keys**: `employee_id` in `LeaveRequest` referencing `id` in `Employee`.
- **Unique Constraints**: `email` in `Employee`.
- **Check Constraints**: `status` in `LeaveRequest` to ensure it is one of 'Pending', 'Approved', or 'Rejected'.
- **Indexes**:
  - `idx_leaverequest_employee_id`: To speed up queries that filter or join on `employee_id`.
  - `idx_leaverequest_status`: To speed up queries that filter on the `status` of leave requests.

DOCUMENTATION WITH REMAINING PLACEHOLDERS
==========================================
# User Guide

## Overview
The Employee Leave Application is designed for employees within our organization to submit leave requests and view their leave status. This application streamlines the leave management process, ensuring that employees can easily request time off and managers can efficiently approve or reject these requests. It also helps in tracking leave balances and maintaining accurate records of leave activities.

## Getting Started
To start using the Employee Leave Application, follow these steps:

1. **Login**: Access the application via the web interface and log in using your employee credentials.
2. **Submit a Leave Request**: Navigate to the 'Leave Requests' section and click on 'Submit Leave Request'. Fill in the required details such as leave type, start date, and end date.
3. **Verify Submission**: After submitting the request, you should see a confirmation message with the leave request ID and status as 'PENDING'.

## User Roles and Permissions

### Employee
- **Capabilities**: Submit leave requests, view their own leave status, and check their leave balance.
- **Typical workflows**: Submit a leave request, check the status of a leave request, view leave balance.
- **Restrictions**: Cannot approve or reject leave requests, cannot view other employees' leave status.

### Manager
- **Capabilities**: Approve or reject leave requests submitted by employees under their supervision.
- **Typical workflows**: Review a leave request, approve or reject the request, view the leave balance of employees.
- **Restrictions**: Cannot modify leave policies, cannot view leave requests outside their team.

### Admin
- **Capabilities**: Manage user roles, view all leave requests, and generate leave reports.
- **Typical workflows**: Assign roles to users, approve or reject leave requests, generate leave reports.
- **Restrictions**: Cannot modify the core application settings, cannot delete user accounts.

## Core Workflows

### Submit a Leave Request
This workflow allows employees to request time off.

**Steps:**
1. Navigate to the 'Leave Requests' section by clicking on the 'Leave' tab in the main menu.
2. Click on 'Submit Leave Request' to open the request form.
3. Enter the required fields with values like:
   - `employeeId`: 12345
   - `leaveType`: SICK
   - `startDate`: 2023-10-01
   - `endDate`: 2023-10-05
4. Click 'Submit' to send the request.
5. **Expected outcome**: A confirmation message with the leave request ID and status as 'PENDING'.

**Common mistakes:**
- **Invalid employee ID**: Ensure the employee ID is correct and exists in the system.
- **Incorrect date format**: Use the format YYYY-MM-DD for dates.

### Approve or Reject a Leave Request
This workflow allows managers to review and act on leave requests.

**Steps:**
1. Navigate to the 'Leave Requests' section by clicking on the 'Leave' tab in the main menu.
2. Click on the leave request ID to open the details.
3. Click 'Approve' or 'Reject' to change the status.
4. **Expected outcome**: The leave request status should update to 'APPROVED' or 'REJECTED'.

**Tips:**
- **Check leave balance**: Ensure the employee has sufficient leave balance before approving.
- **Provide feedback**: Use the comment section to provide feedback on the leave request.

### View Leave Balance
This workflow allows employees to check their remaining leave balance.

**Steps:**
1. Navigate to the 'Leave Balance' section by clicking on the 'Leave' tab in the main menu.
2. Click on 'View Balance' to see the leave balance for different leave types.
3. **Expected outcome**: A list of leave types and their respective balances.

## Data Management

### Creating Records
To create a new leave request, navigate to the 'Leave Requests' section, click on 'Submit Leave Request', and fill in the required fields. Ensure all fields are correctly filled and validated before submission.

### Editing Records
Existing leave requests can be modified by the employee who submitted the request. Navigate to the 'Leave Requests' section, click on the request ID, and then click 'Edit' to make changes. Managers can only change the status of a leave request.

### Deleting Records
Leave requests cannot be deleted once submitted. However, they can be marked as 'CANCELLED' if necessary. This action is irreversible and should be used with caution.

### Filtering and Search
Use the search bar and filters in the 'Leave Requests' section to find specific leave requests. Filters can be applied based on employee ID
