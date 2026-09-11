# Data Model Documentation

## Overview

The system manages the following core domains:
- Employee: Responsible for storing employee details and their leave requests.
- LeaveRequest: Responsible for tracking the details of each leave request submitted by employees.

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
**Purpose**: Represents an employee within the organization, including their personal details and leave balance.

**Table/Collection Name**: `Employee`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the employee |
| `name` | VARCHAR | Yes | Max 255 chars | Employee's full name |
| `email` | VARCHAR | Yes | Max 255 chars, unique | Employee's email address |
| `leave_balance` | INTEGER | Yes | Min 0 | Employee's remaining leave balance |

**Relationships:**
- Has many `LeaveRequest` (1:N): Inverse via `LeaveRequest.employee_id`

**Validation Rules:**
- `email` must be non-empty and unique across all records
- `leave_balance` must be a non-negative integer

### LeaveRequest
**Purpose**: Represents a leave application submitted by an employee, including details such as the leave type, start and end dates, and status.

**Table/Collection Name**: `LeaveRequest`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the leave request |
| `employee_id` | UUID | Yes | Foreign key to `Employee.id` | Employee who submitted the request |
| `leave_type` | VARCHAR | Yes | Must be one of "SICK", "VACATION", "PERSONAL" | Type of leave requested |
| `start_date` | DATE | Yes | Must be a valid date | Start date of the leave |
| `end_date` | DATE | Yes | Must be a valid date and after `start_date` | End date of the leave |
| `status` | VARCHAR | Yes | Must be one of "PENDING", "APPROVED", "REJECTED" | Current status of the leave request |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Timestamp when the request was created |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `Employee` (N:1): Foreign key `employee_id` references `Employee.id`

**Validation Rules:**
- `leave_type` must be one of "SICK", "VACATION", "PERSONAL"
- `start_date` and `end_date` must be valid dates with `end_date` after `start_date`

## Relationships and Constraints

### Foreign Keys
| From Entity | Field | To Entity | Field | Cardinality | Delete Behavior |
|-------------|-------|-----------|-------|-------------|-----------------|
| `LeaveRequest` | `employee_id` | `Employee` | `id` | N:1 | CASCADE |

### Unique Constraints
- `Employee(email)`: Ensures no duplicate email addresses

## Data Validation Rules

**Business Rules:**
- `leave_balance`: Must be a non-negative integer
- `start_date` and `end_date`: `end_date` must be after `start_date`

**Format Rules:**
- `email`: Must match the regex `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$`
- `leave_type`: Must be one of "SICK", "VACATION", "PERSONAL"

## Data Lifecycle

### Creation
- A new `Employee` is created when a new user is onboarded
- Default `leave_balance` is set to 12 (assuming 12 days of leave per year)
- `created_by` is automatically set to the HR manager

### Updates
- `Employee.leave_balance` can only be updated by the HR department
- `updated_at` is automatically updated on any modification

### Archival / Soft Delete
- Records are not archived but can be marked as inactive by setting `status` to "INACTIVE"

## Indexes
- Primary key: `id`
- Foreign keys: `employee_id`
- Search fields: `name`, `email`

## Example Records

### Employee

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "leave_balance": 12
}
```

### LeaveRequest

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "employee_id": "550e8400-e29b-41d4-a716-446655440000",
  "leave_type": "SICK",
  "start_date": "2023-10-01",
  "end_date": "2023-10-05",
  "status": "PENDING",
  "created_at": "2023-09-30T10:30:00Z",
  "updated_at": "2023-09-30T10:30:00Z"
}
```
