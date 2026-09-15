# Data Model Documentation

## Overview
The application manages the procurement and inventory lifecycle for a retail organization operating across India, with 150 stores, 12 warehouses, 2,000 suppliers, and 10,000 employees. The system handles the complete process from identifying a purchasing need through supplier selection, purchase approval, delivery, inventory updates, invoice verification, and payment reconciliation.

The system manages the following core domains:
- Procurement: Manages purchase requisitions, approvals, and purchase orders.
- Inventory: Tracks inventory levels, stock transfers, and goods receipts.
- Budgets: Monitors budget allocations, reservations, and spending.
- Suppliers: Manages supplier onboarding, quotations, and invoices.
- Payments: Handles payment processing and reconciliation.

## Entity Relationship Diagram
```plaintext
[Users] ---- N:1 ---- [PurchaseRequisitions]
    |
    | 1:N
    |
[Stores] ---- N:1 ---- [PurchaseRequisitions]
    |
    | 1:N
    |
[CostCenters] ---- N:1 ---- [PurchaseRequisitions]
    |
    | 1:N
    |
[FinancialYears] ---- N:1 ---- [PurchaseRequisitions]
    |
    | 1:N
    |
[Products] ---- N:1 ---- [PurchaseRequisitionLines]
    |
    | 1:N
    |
[PurchaseRequisitions] ---- 1:N ---- [PurchaseOrders]
    |
    | 1:N
    |
[PurchaseOrders] ---- 1:N ---- [GoodsReceipts]
    |
    | 1:N
    |
[GoodsReceipts] ---- 1:N ---- [Inventory]
    |
    | 1:N
    |
[PurchaseOrders] ---- 1:N ---- [Invoices]
    |
    | 1:N
    |
[Invoices] ---- 1:N ---- [Payments]
```

## Entities

### Users
**Purpose**: Represents users with different roles in the procurement and inventory management process.
**Table/Collection Name**: `Users`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `username` | String | Yes | Max 255 chars, unique | Username of the user |
| `password` | String | Yes | Min 8 chars | Password of the user |
| `role` | String | Yes | Must be one of predefined roles | Role of the user |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Has many `PurchaseRequisitions` (1:N): Inverse via `PurchaseRequisitions.user_id`

**Validation Rules:**
- `username` must be non-empty and unique across all records
- `password` must be at least 8 characters long

### PurchaseRequisitions
**Purpose**: Represents a request for purchasing goods or services.
**Table/Collection Name**: `PurchaseRequisitions`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `user_id` | UUID | Yes | Foreign key to Users | User who created the requisition |
| `store_id` | UUID | Yes | Foreign key to Stores | Store requesting the goods |
| `cost_center_id` | UUID | Yes | Foreign key to CostCenters | Cost center for the requisition |
| `financial_year_id` | UUID | Yes | Foreign key to FinancialYears | Financial year for the requisition |
| `status` | String | Yes | Must be one of predefined statuses | Current status of the requisition |
| `total_value` | DECIMAL | Yes | Must be a positive number | Total value of the requisition |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `Users` (1:N): Foreign key `user_id` references `Users.id`
- Belongs to `Stores` (1:N): Foreign key `store_id` references `Stores.id`
- Belongs to `CostCenters` (1:N): Foreign key `cost_center_id` references `CostCenters.id`
- Belongs to `FinancialYears` (1:N): Foreign key `financial_year_id` references `FinancialYears.id`
- Has many `PurchaseRequisitionLines` (1:N): Inverse via `PurchaseRequisitionLines.purchase_requisition_id`

**Validation Rules:**
- `total_value` must be a positive number

### PurchaseRequisitionLines
**Purpose**: Represents a line item within a purchase requisition.
**Table/Collection Name**: `PurchaseRequisitionLines`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `purchase_requisition_id` | UUID | Yes | Foreign key to PurchaseRequisitions | Requisition this line belongs to |
| `product_id` | UUID | Yes | Foreign key to Products | Product being requested |
| `quantity` | DECIMAL | Yes | Must be a positive number | Quantity of the product |
| `estimated_price` | DECIMAL | Yes | Must be a positive number | Estimated price of the product |
| `required_delivery_date` | DATE | Yes | Must be a valid date | Required delivery date |
| `delivery_location_id` | UUID | Yes | Foreign key to Stores or Warehouses | Location for delivery |
| `cost_center_id` | UUID | Yes | Foreign key to CostCenters | Cost center for the line |
| `business_justification` | String | Yes | Min 5 chars, Max 200 chars | Justification for the request |
| `attachment` | String | No | Max 255 chars | Attachment for the line |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `PurchaseRequisitions` (1:N): Foreign key `purchase_requisition_id` references `PurchaseRequisitions.id`
- Belongs to `Products` (1:N): Foreign key `product_id` references `Products.id`
- Belongs to `Stores` or `Warehouses` (1:N): Foreign key `delivery_location_id` references `Stores.id` or `Warehouses.id`
- Belongs to `CostCenters` (1:N): Foreign key `cost_center_id` references `CostCenters.id`

**Validation Rules:**
- `quantity` and `estimated_price` must be positive numbers
- `required_delivery_date` must be a valid date

### Products
**Purpose**: Represents a product that can be purchased.
**Table/Collection Name**: `Products`

**Fields:**
| Field Name | Type | Required | Constraints | Description |
|------------|------|----------|-------------|-------------|
| `id` | UUID | Yes | Primary key, auto-generated | Unique identifier |
| `sku` | String | Yes | Max 255 chars, unique | SKU of the product |
| `description` | String | No | Max 255 chars | Description of the product |
| `category_id` | UUID | Yes | Foreign key to ProductCategories | Category of the product |
| `unit` | String | Yes | Max 255 chars | Unit of measure for the product |
| `reorder_level` | DECIMAL | Yes | Must be a positive number | Reorder level for the product |
| `preferred_supplier_id` | UUID | No | Foreign key to Suppliers | Preferred supplier for the product |
| `batch_tracking_required` | BOOLEAN | No | Default false | Indicates if batch tracking is required |
| `expiry_tracking_required` | BOOLEAN | No | Default false | Indicates if expiry tracking is required |
| `created_at` | Timestamp | Yes | Auto-set on create | Record creation time |
| `updated_at` | Timestamp | Yes | Auto-set on create/update | Last modification time |

**Relationships:**
- Belongs to `ProductCategories` (1:N): Foreign key `category_id` references `ProductCategories.id`
- Optionally belongs to `Suppliers` (1:N): Foreign key `preferred_supplier_id` references `Suppliers.id`

**Validation Rules:**
- `sku` must be non-empty and unique across all records
- `reorder_level` must be a positive number

## Relationships and Constraints

### Foreign Keys
| From Entity | Field | To Entity | Field | Cardinality | Delete Behavior |
|-------------|-------|-----------|-------|-------------|-----------------|
| `Users` | `id` | `PurchaseRequisitions` | `user_id` | 1:N | CASCADE |
| `Stores` | `id` | `PurchaseRequisitions` | `store_id` | 1:N | CASCADE |
| `CostCenters` | `id` | `PurchaseRequisitions` | `cost_center_id` | 1:N | CASCADE |
| `FinancialYears` | `id` | `PurchaseRequisitions` | `financial_year_id` | 1:N | CASCADE |
| `PurchaseRequisitions` | `id` | `PurchaseRequisitionLines` | `purchase_requisition_id` | 1:N | CASCADE |
| `Products` | `id` | `PurchaseRequisitionLines` | `product_id` | 1:N | CASCADE |
| `Stores` | `id` | `PurchaseRequisitionLines` | `delivery_location_id` | 1:N | CASCADE |
| `CostCenters` | `id` | `PurchaseRequisitionLines` | `cost_center_id` | 1:N | CASCADE |
| `PurchaseRequisitions` | `id` | `PurchaseOrders` | `purchase_requisition_id` | 1:N | CASCADE |
| `PurchaseOrders` | `id` | `GoodsReceipts` | `purchase_order_id` | 1:N | CASCADE |
| `PurchaseOrders` | `id` | `Invoices` | `purchase_order_id` | 1:N | CASCADE |
| `Invoices` | `id` | `Payments` | `invoice_id` | 1:N | CASCADE |

### Unique Constraints
- `Users(username)`: Ensures no duplicate usernames
- `Products(sku)`: Ensures no duplicate SKUs

## Data Validation Rules

**Business Rules:**
- `PurchaseRequisitions.total_value`: Must be a positive number, otherwise raise validation error "Total value must be positive".
- `PurchaseRequisitionLines.quantity`: Must be a positive number, otherwise raise validation error "Quantity must be positive".
- `PurchaseRequisitionLines.estimated_price`: Must be a positive number, otherwise raise validation error "Estimated price must be positive".

**Format Rules:**
- `Users.username`: Must be alphanumeric and between 2 and 50 characters, otherwise raise validation error "Invalid username format".
- `Products.sku`: Must be alphanumeric and between 1 and 255 characters, otherwise raise validation error "Invalid SKU format".

## Data Lifecycle

### Creation
- A new `User` is created when a new account is registered.
- Default `status` for `PurchaseRequisitions` is "DRAFT".
- `created_by` is automatically set to the current user for all entities.

### Updates
- `User.password` can only be updated by the user themselves.
- `updated_at` is automatically updated on any modification to a record.

### Archival / Soft Delete
- Records are not archived but can be deactivated by setting a `deleted_at` timestamp.

## Indexes
- Primary key: `id`
- Foreign keys: `user_id`, `store_id`, `cost_center_id`, `financial_year_id`, `purchase_requisition_id`, `product_id`, `delivery_location_id`, `purchase_order_id`, `invoice_id`
- Search fields: `username`, `sku`

## Example Records

### Users
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "username": "johndoe",
  "password": "encrypted_password",
  "role": "STORE_REQUESTER",
  "created_at": "2026-01-15T10:30:00Z",
  "updated_at": "2026-01-15T10:30:00Z"
}
```

### PurchaseRequisitions
```json
{
  "id": "123e4
