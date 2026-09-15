# User Guide

## Overview
The Enterprise Procurement and Inventory Management Platform is designed for a retail organization operating across India, with 150 stores, 12 warehouses, 2,000 suppliers, and 10,000 employees. This platform aims to streamline the procurement process, manage inventory, and ensure efficient financial operations. It solves problems such as duplicate purchases, budget overruns, stock shortages, delayed payments, and limited audit visibility by providing a centralized system for managing the complete procurement lifecycle.

## Getting Started
1. **Login**: Access the platform using your credentials.
2. **Navigate to Dashboard**: View an overview of pending tasks, budget status, and recent activities.
3. **Verify Success**: Ensure you see your role-specific dashboard with relevant information.

## User Roles and Permissions

### Store Requester
- **Capabilities**: Create and track purchase requests.
- **Typical workflows**: Submit a new purchase requisition, track the status of submitted requisitions.
- **Restrictions**: Cannot approve their own requests.

### Store Manager
- **Capabilities**: Approve purchase requests and monitor store budgets.
- **Typical workflows**: Review and approve requisitions, monitor budget usage.
- **Restrictions**: Cannot approve requisitions they created.

### Procurement Officer
- **Capabilities**: Manage quotations, suppliers, and purchase orders.
- **Typical workflows**: Request quotations from suppliers, compare and select quotations, issue purchase orders.
- **Restrictions**: Cannot approve requisitions they created.

### Warehouse Operator
- **Capabilities**: Receive goods and process stock transfers.
- **Typical workflows**: Record received goods, update inventory levels, process stock transfers.
- **Restrictions**: Cannot approve requisitions they created.

### Finance Officer
- **Capabilities**: Verify invoices and schedule payments.
- **Typical workflows**: Review and approve invoices, schedule payments to suppliers.
- **Restrictions**: Cannot approve requisitions they created.

### Finance Director
- **Capabilities**: Approve high-value purchases and budget exceptions.
- **Typical workflows**: Review and approve high-value requisitions, approve budget exceptions.
- **Restrictions**: Cannot approve requisitions they created.

## Core Workflows

### Submit a Purchase Requisition
**What this workflow accomplishes and when you'd use it**: Create and submit a purchase requisition for approval.

**Steps:**
1. Navigate to the **Requisitions** section.
2. Click **Create Requisition** to open the requisition form.
3. Enter **Product ID, Quantity, Estimated Price, Delivery Date, Delivery Location, Cost Centre, Business Justification**.
4. Click **Submit** to send the requisition for approval.
5. **Expected outcome**: Requisition is submitted successfully and appears in the submitted requisitions list.

**Common mistakes:**
- **Incorrect Product ID**: Ensure the product ID is valid and exists in the system.
- **Invalid Quantity**: Ensure the quantity is a positive integer.

### Approve a Purchase Requisition
**What this workflow accomplishes and when you'd use it**: Approve or reject a submitted purchase requisition.

**Steps:**
1. Navigate to the **Requisitions** section.
2. Click on the **Pending Approvals** tab.
3. Select the requisition to review.
4. Click **Approve** or **Reject** and provide comments if necessary.
5. **Expected outcome**: The requisition status is updated to Approved or Rejected.

**Tips:**
- **Use the calendar**: Select delivery dates using the calendar widget.
- **Attach documents**: Use the attachment field to upload supporting documents.

### Generate a Purchase Order
**What this workflow accomplishes and when you'd use it**: Create a purchase order from an approved requisition.

**Steps:**
1. Navigate to the **Requisitions** section.
2. Select an approved requisition.
3. Click **Generate Purchase Order**.
4. Review and confirm the purchase order details.
5. Click **Create Purchase Order**.
6. **Expected outcome**: Purchase order is created and sent to the supplier.

**Tips:**
- **Check stock levels**: Ensure sufficient stock levels before generating a purchase order.
- **Verify supplier details**: Confirm supplier details are correct before creating the purchase order.

## Data Management

### Creating Records
**How to create a new record, validation rules, required fields**:
- Navigate to the relevant section (e.g., **Users**, **Requisitions**).
- Click **Create** to open the form.
- Enter required fields (e.g., **Name**, **Role**, **Product ID**).
- Click **Submit** to create the record.
- **Validation rules**: Ensure all required fields are filled and valid.

### Editing Records
**How to modify existing records, who can edit what**:
- Navigate to the relevant section.
- Select the record to edit.
- Click **Edit** to open the form.
- Modify the required fields.
- Click **Save** to update the record.
- **Permissions**: Only users with appropriate roles can edit records.

### Deleting Records
**Deletion process, any cascading effects, recovery options**:
- Navigate to the relevant section.
- Select the record to delete.
- Click **Delete** and confirm the action.
- **Cascading effects**: Deleting a record may affect related records (e.g., deleting a user may remove their requisitions).
- **Recovery options**: Use the **Recycle Bin** to restore deleted records.

### Filtering and Search
**How to find records using filters and search**:
- Use the **Filter** option to narrow down records by criteria (e.g., **Status**, **Date**).
- Use the **Search** bar to find records by keywords (e.g., **Product Name**, **User Name**).

## Reports and Exports
**What reports are available, how to generate them, export formats**:
- Navigate to the **Reports** section.
- Select the desired report (e.g., **Purchase Requisitions**, **Inventory Levels**).
- Configure the report parameters (e.g., **Date Range**, **Location**).
- Click **Generate** to create the report.
- **Export formats**: Available formats include CSV and Excel.

## Frequently Asked Questions

**Q: Can I create multiple requisitions at once?**
A: Yes, you can create multiple requisitions by adding multiple products to a single requisition form.

**Q: How do I track the status of my requisitions?**
A: Navigate to the **Requisitions** section and view the status of your requisitions in the list.

**Q: Can I edit a requisition after it has been submitted?**
A: Yes, you can revise a requisition by clicking the **Revise** button and making the necessary changes.

## Troubleshooting

### Common Issues
- **Requisition not submitting**: Ensure all required fields are filled and valid.
- **Approval not received**: Check the approval workflow and ensure the approver has access to the requisition.
- **Invoice not matching**: Verify the quantities, prices, and taxes match the purchase order and goods receipt.

### Performance Tips
- **Use filters**: Narrow down the list of records to improve performance.
- **Limit search terms**: Use specific keywords to find records quickly.

## Best Practices
- **Regularly review budgets**: Ensure budget allocations are accurate and up-to-date.
- **Monitor stock levels**: Keep track of inventory to prevent stock shortages.
- **Use approval workflows**: Ensure requisitions are reviewed and approved by the appropriate personnel.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
