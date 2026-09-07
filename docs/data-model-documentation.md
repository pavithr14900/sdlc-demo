# Data Model Documentation

## Overview

The employee leave application manages data related to employees and their leave requests. It ensures that leave requests are submitted, processed, and approved in an organized manner. The system manages the following core domains:

- **Employee**: Responsible for storing employee details and their leave requests. The cardinality is 1:N, meaning one employee can have multiple leave requests.
- **LeaveRequest**: Responsible for storing details of each leave request, including the employee who requested it, the dates, and the status. The cardinality is 1:N, meaning one leave request is associated with one employee.

## Entity Relationship Diagram

```
[Employee]
    |
    | 1:N
    |
[LeaveRequest]
```

## Entities

### Employee
**Purpose**: Represents an employee in the organization.

**Table/Collection Name**: `Employee`

**Fields:**

| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the employee |
| `name` | VARCHAR | Yes | Max 255 chars | Employee's full name |
| `email` | VARCHAR | Yes | Max 255 chars, unique | Employee's email address |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**

- Has many `LeaveRequest` (1:N): Inverse via `LeaveRequest.employee_id`

**Validation Rules:**

- `name` must be non-empty
- `email` must be non-empty and unique across all records

### LeaveRequest
**Purpose**: Represents a leave request made by an employee.

**Table/Collection Name**: `LeaveRequest`

**Fields:**

| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the leave request |
| `employee_id` | UUID | Yes | Foreign key referencing `Employee.id` | Employee who requested the leave |
| `start_date` | DATE | Yes | | Start date of the leave |
| `end_date` | DATE | Yes | | End date of the leave |
| `status` | VARCHAR | Yes | Default 'Pending' | Status of the leave request |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**

- Belongs to `Employee` (N:1): Foreign key `employee_id` references `Employee.id`

**Validation Rules:**

- `start_date` must be a valid date
- `end_date` must be a valid date and must be after `start_date`
- `status` must be one of 'Pending', 'Approved', 'Rejected'

## Relationships and Constraints

### Foreign Keys

| From Entity | Field | To Entity | Field | Cardinality | Delete Behavior |
|-------------|-------|-----------|-------|-------------|-----------------|
| `LeaveRequest` | `employee_id` | `Employee` | `id` | N:1 | CASCADE |

### Unique Constraints

- `Employee(email)`: Ensures no duplicate email addresses

## Data Validation Rules

**Business Rules:**

- `start_date` must be before `end_date`: If `start_date` is after `end_date`, return validation error.
- `status` must be one of 'Pending', 'Approved', 'Rejected': If `status` is not one of these values, return validation error.

**Format Rules:**

- `email`: Must match the regex `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$`

## Data Lifecycle

### Creation

- A new `LeaveRequest` is created when an employee submits a leave request.
- Default `status` is `Pending`
- `created_at` is automatically set to the current timestamp

### Updates

- `LeaveRequest.status` can only be updated by HR personnel
- `updated_at` is automatically updated on any modification

### Archival / Soft Delete

- Records are not archived but can be marked as 'Rejected' or 'Approved' to indicate their status

## Indexes

- Primary key: `id`
- Foreign keys: `employee_id`
- Search fields: `name`, `email` (for common queries)

## Example Records

### Employee

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "created_at": "2026-01-15T10:30:00Z",
  "updated_at": "2026-01-15T10:30:00Z"
}
```

### LeaveRequest

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "employee_id": "550e8400-e29b-41d4-a716-446655440000",
  "start_date": "2026-01-15",
  "end_date": "2026-01-20",
  "status": "Pending",
  "created_at": "2026-01-15T10:30:00Z",
  "updated_at": "2026-01-15T10:30:00Z"
}
```
