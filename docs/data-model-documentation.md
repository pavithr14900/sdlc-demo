# Data Model Documentation

## Overview

The employee leave application manages data related to employees, their leave requests, and the approval process within the organization. This data model ensures that all necessary information is captured and maintained to facilitate the leave management process efficiently. The system manages the following core domains:

- **Employees**: Responsible for storing employee details, their roles, and their leave balances.
- **Leave Requests**: Responsible for capturing leave requests submitted by employees, including the type of leave, duration, and status.
- **Approvals**: Responsible for tracking the approval status of leave requests, including who approved or rejected the request and when.

## Entity Relationship Diagram

Below is an ASCII diagram representing the entities and their relationships:

```
[Employee] ---- 1:N ---- [LeaveRequest]
    |
    | 1:1
    |
[LeaveBalance]
    |
    | 1:N
    |
[Approval]
```

## Entities

### Employee
**Purpose**: Represents an employee within the organization, including their personal details and leave balance.

**Table/Collection Name**: `employees`

**Fields:**

| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `name` | String | Yes | Max 255 chars | Employee's full name |
| `email` | String | Yes | Max 255 chars, unique | Employee's email address |
| `role` | String | Yes | Max 100 chars | Employee's role within the organization |
| `leave_balance` | Integer | Yes | Min 0 | Total leave days available |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**

- Has many `LeaveRequest` (1:N): Inverse via `LeaveRequest.employee_id`
- Has one `LeaveBalance` (1:1): Inverse via `LeaveBalance.employee_id`

**Validation Rules:**

- `name` must be non-empty and within 255 characters
- `email` must be non-empty, unique, and a valid email format
- `role` must be non-empty and within 100 characters
- `leave_balance` must be a non-negative integer

### LeaveRequest
**Purpose**: Captures details of a leave request submitted by an employee, including the type of leave, duration, and status.

**Table/Collection Name**: `leave_requests`

**Fields:**

| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `employee_id` | UUID | Yes | Foreign key to `employees.id` | Employee who submitted the request |
| `leave_type` | String | Yes | Max 100 chars | Type of leave (e.g., sick, vacation) |
| `start_date` | Date | Yes | Must be in the future | Start date of the leave |
| `end_date` | Date | Yes | Must be after `start_date` | End date of the leave |
| `status` | String | Yes | Must be one of (Pending, Approved, Rejected) | Current status of the request |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**

- Belongs to `Employee` (N:1): Foreign key `employee_id` references `employees.id`
- Has one `Approval` (1:1): Inverse via `Approval.leave_request_id`

**Validation Rules:**

- `leave_type` must be non-empty and within 100 characters
- `start_date` and `end_date` must be valid dates and `end_date` must be after `start_date`
- `status` must be one of (Pending, Approved, Rejected)

### LeaveBalance
**Purpose**: Tracks the leave balance for each employee, including the types of leave available.

**Table/Collection Name**: `leave_balances`

**Fields:**

| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `employee_id` | UUID | Yes | Foreign key to `employees.id` | Employee associated with the balance |
| `sick_leave` | Integer | Yes | Min 0 | Number of sick leave days remaining |
| `vacation_leave` | Integer | Yes | Min 0 | Number of vacation leave days remaining |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**

- Belongs to `Employee` (N:1): Foreign key `employee_id` references `employees.id`

**Validation Rules:**

- `sick_leave` and `vacation_leave` must be non-negative integers

### Approval
**Purpose**: Captures the approval status of a leave request, including the approver and the timestamp of the approval.

**Table/Collection Name**: `approvals`

**Fields:**

| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `leave_request_id` | UUID | Yes | Foreign key to `leave_requests.id` | Leave request being approved |
| `approver_id` | UUID | Yes | Foreign key to `employees.id` | Employee who approved the request |
| `status` | String | Yes | Must be one of (Approved, Rejected) | Approval status |
| `comment` | String | No | Max 500 chars | Comment from the approver |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**

- Belongs to `LeaveRequest` (N:1): Foreign key `leave_request_id` references `leave_requests.id`
- Belongs to `Employee` (N:1): Foreign key `approver_id` references `employees.id`

**Validation Rules:**

- `status` must be one of (Approved, Rejected)
- `comment` must be within 500 characters if provided

## Relationships and Constraints

### Foreign Keys

| From Entity | Field | To Entity | Field | Cardinality | Delete Behavior |
|-------------|-------|-----------|-------|-------------|-----------------|
| `leave_requests` | `employee_id` | `employees` | `id` | N:1 | CASCADE |
| `leave_balances` | `employee_id` | `employees` | `id` | N:1 | CASCADE |
| `approvals` | `leave_request_id` | `leave_requests` | `id` | N:1 | CASCADE |
| `approvals` | `approver_id` | `employees` | `id` | N:1 | CASCADE |

### Unique Constraints

- `employees(email)`: Ensures no duplicate email addresses
- `leave_requests(employee_id, start_date, end_date)`: Ensures no overlapping leave requests for the same employee

## Data Validation Rules

**Business Rules:**

- **Leave Balance Check**: Before approving a leave request, ensure the employee has sufficient leave balance for the requested leave type.
- **Date Range Validity**: Ensure the leave request's start and end dates do not overlap with existing leave requests for the same employee.
- **Approval Chain**: Ensure that only authorized employees can approve leave requests.

**Format Rules:**

- **Email Format**: `email` must match the regex `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$`
- **Leave Type**: `leave_type` must be one of (sick, vacation, personal)

## Data Lifecycle

### Creation

- A new `Employee` is created when a new employee is hired.
- Default `leave_balance` is set to 0 for both sick and vacation leave.
- `created_by` is automatically set to the HR manager creating the record.

### Updates

- `Employee.leave_balance` can only be updated by the HR department.
- `updated_at` is automatically updated on any modification to an employee's details.

### Archival / Soft Delete

- Records are not archived but marked with a `deleted_at` timestamp when an employee leaves the organization.
- Soft-deleted records are excluded from normal queries.

## Indexes

- Primary key: `id`
- Foreign keys: `employee_id`, `leave_request_id`, `approver_id`
- Search fields: `name`, `email`, `leave_type` (for common queries)

## Example Records

### Employee

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "role": "Software Engineer",
  "leave_balance": {
    "sick_leave": 10,
    "vacation_leave": 20
  },
  "created_at": "2026-01-15T10:30:00Z",
  "updated_at": "2026-01-15T10:30:00Z"
}
```

### LeaveRequest

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "employee_id": "550e8400-e29b-41d4-a716-446655440000",
  "leave_type": "vacation",
  "start_date": "2026-02-01",
  "end_date": "2026-02-05",
  "status": "Pending",
  "created_at": "2026-01-15T10:30:00Z",
  "updated_at": "2026-01-15T10:30:00Z"
}
```

### Approval

```json
{
  "id": "789e0123-4567-89ab-cdef-0123456789ab",
  "leave_request_id": "123e4567-e89b-12d3-a456-426614174000",
  "approver_id": "550e8400-e29b-41d4-a716-446655440000",
  "status": "Approved",
  "comment": "Leave approved for vacation",
  "created_at": "2026-01-16T09:00:00Z",
  "updated_at": "2026-01-16T09:00:00Z"
}
```
