# Data Model Documentation

## Overview

The application manages the following core domains:
- Employee: Manages employee details and their submitted expenses and travel requests.
- Expense: Tracks submitted expenses, their approval status, and reimbursement details.
- Approval: Manages the approval process for expenses and travel requests by managers.
- Manager: Manages manager details and their approval actions.
- Finance: Manages finance details and policy compliance checks.
- Reimbursement: Tracks reimbursement details for approved expenses.
- Policy: Defines corporate policies and their thresholds.
- Audit: Maintains audit history for all actions on expenses and travel requests.
- Notification: Manages notifications sent to employees regarding their expenses and travel requests.
- Role: Manages roles and their associated permissions.
- UserRole: Associates users with their roles.
- ApprovalThreshold: Defines approval thresholds for different roles.

## Entity Relationship Diagram

```
[Employee] ---- 1:N ---- [Expense]
[Employee] ---- 1:N ---- [Approval]
[Employee] ---- 1:N ---- [Notification]
[Employee] ---- 1:N ---- [UserRole]
[Expense] ---- 1:1 ---- [Approval]
[Expense] ---- 1:1 ---- [Reimbursement]
[Approval] ---- 1:1 ---- [Manager]
[Manager] ---- N:1 ---- [Approval]
[Policy] ---- 1:1 ---- [ApprovalThreshold]
[Role] ---- 1:N ---- [UserRole]
[Role] ---- 1:1 ---- [ApprovalThreshold]
```

## Entities

### Employee
**Purpose**: Represents an employee who submits expenses and travel requests.
**Table/Collection Name**: `Employee`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `name` | VARCHAR | Yes | Max 255 chars | Employee name |
| `email` | VARCHAR | Yes | Max 255 chars, unique | Employee email |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Has many `Expense` (1:N): Inverse via `Expense.employee_id`
- Has many `Approval` (1:N): Inverse via `Approval.employee_id`
- Has many `Notification` (1:N): Inverse via `Notification.employee_id`
- Has many `UserRole` (1:N): Inverse via `UserRole.user_id`

**Validation Rules:**
- `email` must be non-empty and unique across all records

### Expense
**Purpose**: Represents an expense submitted by an employee.
**Table/Collection Name**: `Expense`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `employee_id` | UUID | Yes | Foreign key to `Employee.id` | Employee who submitted the expense |
| `amount` | DECIMAL | Yes | > 0 | Expense amount |
| `description` | VARCHAR | No | Max 255 chars | Expense description |
| `receipt_url` | VARCHAR | No | Max 500 chars | URL to uploaded receipt |
| `status` | VARCHAR | Yes | 'PENDING', 'APPROVED', 'REJECTED' | Expense status |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `Employee` (1:N): Foreign key `employee_id` references `Employee.id`
- Has one `Approval` (1:1): Inverse via `Approval.expense_id`
- Has one `Reimbursement` (1:1): Inverse via `Reimbursement.expense_id`

**Validation Rules:**
- `amount` must be greater than 0
- `receipt_url` must be a valid URL if provided

### Approval
**Purpose**: Represents the approval process for an expense or travel request.
**Table/Collection Name**: `Approval`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `expense_id` | UUID | No | Foreign key to `Expense.id` | Expense being approved |
| `manager_id` | UUID | Yes | Foreign key to `Manager.id` | Manager approving the expense |
| `status` | VARCHAR | Yes | 'APPROVED', 'REJECTED' | Approval status |
| `comment` | VARCHAR | No | Max 500 chars | Approval comment |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |

**Relationships:**
- Belongs to `Expense` (1:1): Foreign key `expense_id` references `Expense.id`
- Belongs to `Manager` (1:1): Foreign key `manager_id` references `Manager.id`

**Validation Rules:**
- `comment` must be non-empty if provided

### Manager
**Purpose**: Represents a manager who approves expenses and travel requests.
**Table/Collection Name**: `Manager`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `name` | VARCHAR | Yes | Max 255 chars | Manager name |
| `email` | VARCHAR | Yes | Max 255 chars, unique | Manager email |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Has many `Approval` (1:N): Inverse via `Approval.manager_id`

**Validation Rules:**
- `email` must be non-empty and unique across all records

### Finance
**Purpose**: Represents a finance team member who validates policy compliance.
**Table/Collection Name**: `Finance`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `name` | VARCHAR | Yes | Max 255 chars | Finance name |
| `email` | VARCHAR | Yes | Max 255 chars, unique | Finance email |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Validation Rules:**
- `email` must be non-empty and unique across all records

### Reimbursement
**Purpose**: Represents the reimbursement details for an approved expense.
**Table/Collection Name**: `Reimbursement`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `expense_id` | UUID | Yes | Foreign key to `Expense.id` | Expense being reimbursed |
| `amount` | DECIMAL | Yes | > 0 | Reimbursement amount |
| `status` | VARCHAR | Yes | 'PENDING', 'COMPLETED' | Reimbursement status |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `Expense` (1:1): Foreign key `expense_id` references `Expense.id`

**Validation Rules:**
- `amount` must be greater than 0

### Policy
**Purpose**: Defines corporate policies and their thresholds.
**Table/Collection Name**: `Policy`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `name` | VARCHAR | Yes | Max 255 chars | Policy name |
| `description` | VARCHAR | No | Max 500 chars | Policy description |
| `threshold` | DECIMAL | Yes | > 0 | Policy threshold |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Validation Rules:**
- `threshold` must be greater than 0

### Audit
**Purpose**: Maintains audit history for all actions on expenses and travel requests.
**Table/Collection Name**: `Audit`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `entity_id` | UUID | Yes | Foreign key to `Expense.id` or `TravelRequest.id` | Entity being audited |
| `entity_type` | VARCHAR | Yes | 'EXPENSE' or 'TRAVEL_REQUEST' | Type of entity |
| `action` | VARCHAR | Yes | 'SUBMITTED', 'APPROVED', 'REJECTED', etc. | Action performed |
| `details` | JSONB | No | JSON object | Audit details |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |

**Validation Rules:**
- `entity_type` must be one of 'EXPENSE' or 'TRAVEL_REQUEST'

### Notification
**Purpose**: Manages notifications sent to employees regarding their expenses and travel requests.
**Table/Collection Name**: `Notification`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `employee_id` | UUID | Yes | Foreign key to `Employee.id` | Employee receiving notification |
| `message` | VARCHAR | Yes | Max 500 chars | Notification message |
| `status` | VARCHAR | Yes | 'PENDING', 'SENT' | Notification status |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `Employee` (1:N): Foreign key `employee_id` references `Employee.id`

**Validation Rules:**
- `message` must be non-empty

### Role
**Purpose**: Manages roles and their associated permissions.
**Table/Collection Name**: `Role`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `name` | VARCHAR | Yes | Max 255 chars | Role name |
| `description` | VARCHAR | No | Max 500 chars | Role description |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Validation Rules:**
- `name` must be non-empty

### UserRole
**Purpose**: Associates users with their roles.
**Table/Collection Name**: `UserRole`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `user_id` | UUID | Yes | Foreign key to `Employee.id` | User associated with the role |
| `role_id` | UUID | Yes | Foreign key to `Role.id` | Role associated with the user |
| `created_at` | TIMESTAMP | Yes | Auto-set on create | Record creation time |
| `updated_at` | TIMESTAMP | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `Employee` (1:N): Foreign key `user_id` references `Employee.id`
- Belongs to `Role` (1:N): Foreign key `role_id` references `Role.id`

**Validation Rules:**
- Both `user_id` and `role_id` must be non-null and valid

### ApprovalThreshold
**Purpose**: Defines approval thresholds for different roles.
**Table/Collection Name**: `ApprovalThreshold`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier
