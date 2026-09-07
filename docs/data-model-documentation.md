# Data Model Documentation

## Overview

The application manages the following core domains to facilitate the submission, approval, and tracking of employee leave requests within our organization.

- **Employee**: Responsible for managing employee data, including leave balances and requests. Cardinality: 1:N with LeaveRequest.
- **LeaveRequest**: Responsible for managing leave requests, including their status and associated dates. Cardinality: N:1 with Employee.

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

**Purpose**: Represents an employee within the organization, including their leave balance and requests.

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

- `email` must be non-empty and unique across all records
- `name` must be non-empty

### LeaveRequest

**Purpose**: Represents a leave request submitted by an employee, including its status and associated dates.

**Table/Collection Name**: `LeaveRequest`

**Fields:**

| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the leave request |
| `employee_id` | UUID | Yes | Foreign key to Employee.id | Employee who requested the leave |
| `start_date` | DATE | Yes | Must not be in the past | Start date of the leave |
| `end_date` | DATE | Yes | Must be after start_date | End date of the leave |
| `status` | VARCHAR | Yes | Must be one of 'Pending', 'Approved', 'Rejected' | Status of the leave request |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**

- Belongs to `Employee` (N:1): Foreign key `employee_id` references `Employee.id`

**Validation Rules:**

- `start_date` must be a valid date and not in the past
- `end_date` must be a valid date and after `start_date`
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

- `LeaveRequest.start_date` must be a valid date and not in the past: If violated, return error "Start date must not be in the past."
- `LeaveRequest.end_date` must be a valid date and after `start_date`: If violated, return error "End date must be after start date."
- `LeaveRequest.status` must be one of 'Pending', 'Approved', 'Rejected': If violated, return error "Invalid leave status."

**Format Rules:**

- `Employee.email`: Must match regex `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$`

## Data Lifecycle

### Creation

- A new `LeaveRequest` is created when an employee submits a leave request.
- Default `status` is `Pending`
- `created_at` is automatically set to the current timestamp

### Updates

- `LeaveRequest.status` can only be updated by an authorized user (e.g., manager)
- `updated_at` is automatically updated on any modification

### Archival / Soft Delete

- Records are not archived but can be marked as deleted by setting the `status` to 'Rejected'

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
