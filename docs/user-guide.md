# User Guide

## Overview
The Expense Management Application is designed for employees within an organization to submit, track, and manage their expense reports. This application solves the problem of manual expense tracking and reporting by providing a streamlined, automated process. Employees can submit expenses, managers can approve or reject them, and all parties can view reports and track the status of expenses.

## Getting Started
1. **Login**: Open the application and log in using your credentials.
2. **Submit an Expense**: Navigate to the 'Submit Expense' section and fill out the required fields.
3. **Verify Submission**: Ensure you see a confirmation message indicating your expense has been submitted successfully.

## User Roles and Permissions

### Employee
- **Capabilities**: Submit expenses, view submitted expenses, view approval status.
- **Typical workflows**: Submit a new expense, view pending expenses, check approval status.
- **Restrictions**: Cannot approve or reject expenses.

### Manager
- **Capabilities**: Approve or reject submitted expenses, view all expenses.
- **Typical workflows**: Review and approve submitted expenses, reject invalid expenses.
- **Restrictions**: Cannot modify submitted expense details.

## Core Workflows

### Submit Expense
This workflow allows employees to submit their expense reports for approval.

**Steps:**
1. Navigate to the 'Submit Expense' page.
2. Click the 'Add Expense' button to open the expense form.
3. Enter fields with values like:
   - `employeeId: 1`
   - `amount: 100`
   - `description: Lunch`
   - `date: 2023-10-01`
4. Click the 'Submit' button to send the expense for approval.
5. **Expected outcome**: A confirmation message stating the expense has been submitted.

**Common mistakes:**
- **Missing `employeeId`**: Ensure you enter a valid employee ID.
- **Invalid `amount`**: Ensure the amount is a positive number.

### Approve Expense
This workflow allows managers to approve submitted expenses.

**Steps:**
1. Navigate to the 'Manage Expenses' page.
2. Click on the expense you want to approve.
3. Click the 'Approve' button.
4. **Expected outcome**: The expense status changes to 'APPROVED'.

**Tips:**
- Use the filter option to quickly find expenses that need approval.
- Review the expense details before approving.

### Reject Expense
This workflow allows managers to reject submitted expenses.

**Steps:**
1. Navigate to the 'Manage Expenses' page.
2. Click on the expense you want to reject.
3. Click the 'Reject' button.
4. **Expected outcome**: The expense status changes to 'REJECTED'.

**Tips:**
- Provide a reason for rejection to help the employee understand the issue.
- Ensure the rejection reason is clear and actionable.

## Data Management

### Creating Records
To create a new expense record, navigate to the 'Submit Expense' page and fill out the required fields. Required fields include `employeeId`, `amount`, `description`, and `date`. Ensure the `amount` is a positive number and the `date` is in the correct format (YYYY-MM-DD).

### Editing Records
Employees can only edit their submitted expenses before approval. To edit an expense, navigate to the 'My Expenses' page, find the expense, and click the 'Edit' button. Make the necessary changes and click 'Save'.

### Deleting Records
Expenses can only be deleted by employees before they are approved. To delete an expense, navigate to the 'My Expenses' page, find the expense, and click the 'Delete' button. Confirm the deletion.

### Filtering and Search
Use the filter options on the 'Manage Expenses' page to find specific expenses. You can filter by employee, date range, and status.

## Reports and Exports
You can generate reports on expenses by navigating to the 'Reports' page. Available reports include:
- **Monthly Expense Report**: View expenses submitted in a specific month.
- **Employee Expense Report**: View all expenses submitted by a specific employee.

Export formats include CSV and PDF.

## Frequently Asked Questions

**Q: How do I submit an expense?**
A: Navigate to the 'Submit Expense' page, fill out the required fields, and click 'Submit'.

**Q: Can I edit an expense after it has been approved?**
A: No, expenses cannot be edited after approval.

**Q: Can I delete an expense after it has been approved?**
A: No, expenses cannot be deleted after approval.

## Troubleshooting

### Common Issues
- **Expense not submitting**: Ensure all required fields are filled out correctly.
- **Unable to approve/reject expense**: Ensure you have the correct permissions.
- **Expense status not updating**: Refresh the page and ensure the action was successful.

### Performance Tips
- Use filters to narrow down the list of expenses for quicker access.
- Regularly clear your browser cache for optimal performance.

## Best Practices
- Always double-check the expense details before submitting.
- Provide clear and concise descriptions for expenses.
- Regularly review and approve submitted expenses to avoid delays.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
