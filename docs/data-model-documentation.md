# Data Model Documentation

## Overview

The system manages the following core domains:
- Employee: Responsible for storing employee details and their leave requests.
- LeaveRequest: Responsible for managing leave requests submitted by employees.

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
**Purpose**: Represents an employee within the organization who can submit leave requests.

**Table/Collection Name**: `Employee`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the employee |
| `name` | VARCHAR | Yes | Max 255 chars | Employee name |
| `email` | VARCHAR | Yes | Max 255 chars, unique | Employee email |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Has many `LeaveRequest` (1:N): Inverse via `LeaveRequest.employee_id`

**Validation Rules:**
- `name` must be non-empty
- `email` must be non-empty and unique across all records

### LeaveRequest
**Purpose**: Represents a leave request submitted by an employee.

**Table/Collection Name**: `LeaveRequest`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the leave request |
| `employee_id` | UUID | Yes | Foreign key | Employee who requested leave |
| `leave_type` | VARCHAR | Yes | Max 255 chars | Type of leave requested |
| `start_date` | DATE | Yes | Must not be in the past | Start date of leave |
| `end_date` | DATE | Yes | Must be after start_date | End date of leave |
| `status` | VARCHAR | Yes | 'PENDING', 'APPROVED', 'REJECTED', 'CANCELED' | Leave request status |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `Employee` (N:1): Foreign key `employee_id` references `Employee.id`

**Validation Rules:**
- `employee_id` must be a valid employee ID
- `leave_type` must be one of the predefined leave types
- `start_date` must be a valid date, must not be in the past
- `end_date` must be a valid date, must be after `start_date`
- `status` must be one of the predefined statuses

## Relationships and Constraints

### Foreign Keys
| From Entity | Field | To Entity | Field | Cardinality | Delete Behavior |
|-------------|-------|-----------|-------|-------------|-----------------|
| `LeaveRequest` | `employee_id` | `Employee` | `id` | N:1 | CASCADE |

### Unique Constraints
- `Employee(email)`: Ensures no duplicate email addresses

## Data Validation Rules

**Business Rules:**
- `leave_type` must be one of the predefined leave types: 'SICK', 'VACATION', 'MATERNITY', 'PATERNITY'.
- `start_date` must be a valid date and must not be in the past.
- `end_date` must be a valid date and must be after `start_date`.

**Format Rules:**
- `email`: Must match the regex `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$`

## Data Lifecycle

### Creation
- A new `LeaveRequest` is created when an employee submits a leave request.
- Default `status` is `PENDING`.
- `created_by` is automatically set to the current user.

### Updates
- `LeaveRequest.status` can only be updated by an authorized user.
- `updated_at` is automatically updated on any modification.

### Archival / Soft Delete
- Records are not archived but can be marked as `CANCELED`.

## Indexes
- Primary key: `id`
- Foreign keys: `employee_id`
- Search fields: `email`

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
  "leave_type": "SICK",
  "start_date": "2026-01-15",
  "end_date": "2026-01-20",
  "status": "PENDING",
  "created_at": "2026-01-15T10:30:00Z",
  "updated_at": "2026-01-15T10:30:00Z"
}
```
