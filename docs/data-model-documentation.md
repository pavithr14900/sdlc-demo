# Data Model Documentation

## Overview
The expense management application manages the core domains of employees and expenses. The application allows employees to submit expenses for reimbursement and administrators to approve or reject these expenses. This system ensures that all expenses are tracked, approved, and reimbursed efficiently.

The system manages the following core domains:
- **Employee**: Responsible for submitting expenses, with a one-to-many relationship with expenses.
- **Expense**: Represents the expenses submitted by employees, with a one-to-one relationship with approval records.

## Entity Relationship Diagram
```
[Employee]
    |
    | 1:N
    |
[Expense] ---- 1:1 ---- [Expense_Approval]
```

## Entities

### Employee
**Purpose**: Represents an employee who can submit expenses for reimbursement.
**Table/Collection Name**: `Employee`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the employee |
| `name` | VARCHAR | Yes | Max 255 chars | Employee's name |
| `email` | VARCHAR | Yes | Unique | Employee's email address |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Has many `Expense` (1:N): Inverse via `Expense.employee_id`

**Validation Rules:**
- `email` must be non-empty and unique across all records

### Expense
**Purpose**: Represents an expense submitted by an employee for reimbursement.
**Table/Collection Name**: `Expense`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the expense |
| `employee_id` | UUID | Yes | Foreign key | Employee who submitted the expense |
| `amount` | DECIMAL | Yes | Min 0 | Expense amount |
| `description` | VARCHAR | No | Max 255 chars | Expense description |
| `submitted_at` | TIMESTAMP | Yes | Auto-set on create | Expense submission time |
| `status` | VARCHAR | Yes | Default 'PENDING' | Expense status |

**Relationships:**
- Belongs to `Employee` (N:1): Foreign key `employee_id` references `Employee.id`
- Has one `Expense_Approval` (1:1): Inverse via `Expense_Approval.expense_id`

**Validation Rules:**
- `employee_id` must reference an existing employee
- `amount` must be a positive number
- `description` must be at most 255 characters if provided

### Expense_Approval
**Purpose**: Represents the approval status of an expense.
**Table/Collection Name**: `Expense_Approval`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier for the approval record |
| `expense_id` | UUID | Yes | Foreign key | Expense being approved |
| `approver_id` | UUID | Yes | Foreign key | Employee who approved the expense |
| `approved_at` | TIMESTAMP | No |  | Approval timestamp |
| `approval_status` | VARCHAR | Yes | Default 'PENDING' | Approval status |

**Relationships:**
- Belongs to `Expense` (N:1): Foreign key `expense_id` references `Expense.id`
- Belongs to `Employee` (N:1): Foreign key `approver_id` references `Employee.id`

**Validation Rules:**
- `expense_id` must reference an existing expense
- `approver_id` must reference an existing employee
- `approval_status` must be either 'APPROVED' or 'REJECTED'

## Relationships and Constraints

### Foreign Keys
| From Entity | Field | To Entity | Field | Cardinality | Delete Behavior |
|-------------|-------|-----------|-------|-------------|-----------------|
| `Employee` | `id` | `Expense` | `employee_id` | 1:N | CASCADE |
| `Expense` | `id` | `Expense_Approval` | `expense_id` | 1:1 | CASCADE |
| `Employee` | `id` | `Expense_Approval` | `approver_id` | N:1 | CASCADE |

### Unique Constraints
- `Employee(email)`: Ensures no duplicate email addresses

## Data Validation Rules

**Business Rules:**
- **Employee email uniqueness**: The `email` field in the `Employee` entity must be unique across all records.
- **Positive expense amount**: The `amount` field in the `Expense` entity must be a positive number.
- **Description length limit**: The `description` field in the `Expense` entity must be at most 255 characters if provided.

**Format Rules:**
- **Email format**: The `email` field in the `Employee` entity must match the standard email format regex.
- **Date format**: The `submitted_at` and `approved_at` fields in the `Expense` and `Expense_Approval` entities must be in the YYYY-MM-DD format.

## Data Lifecycle

### Creation
- A new `Employee` is created when a new user registers.
- Default `status` for `Expense` is `PENDING`.
- `created_at` and `updated_at` are automatically set to the current timestamp on creation.

### Updates
- `Employee.email` can only be updated by the employee themselves or an administrator.
- `updated_at` is automatically updated on any modification to an `Employee` or `Expense`.

### Archival / Soft Delete
- Records are not archived but marked with a `deleted_at` timestamp if soft delete is required.

## Indexes
- Primary key: `id`
- Foreign keys: `employee_id`, `expense_id`, `approver_id`
- Search fields: `name`, `email` (for common queries)

## Example Records

### Employee
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "created_at": "2023-10-01T10:00:00Z",
  "updated_at": "2023-10-01T10:00:00Z"
}
```

### Expense
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "employee_id": "550e8400-e29b-41d4-a716-446655440000",
  "amount": 100.00,
  "description": "Business lunch",
  "submitted_at": "2023-10-01T12:00:00Z",
  "status": "PENDING"
}
```

### Expense_Approval
```json
{
  "id": "789e1234-5678-9abc-def0-123456789abc",
  "expense_id": "123e4567-e89b-12d3-a456-426614174000",
  "approver_id": "550e8400-e29b-41d4-a716-446655440000",
  "approved_at": "2023-10-02T09:00:00Z",
  "approval_status": "APPROVED"
}
```
