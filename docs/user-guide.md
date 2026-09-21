# User Guide

## Overview
The Employee Expense & Corporate Travel Management Platform is designed for employees, managers, and finance teams to efficiently manage and track employee expenses and corporate travel requests. Employees can submit expense claims and travel requests, upload receipts, and track reimbursement status. Managers can approve or reject these claims, while the finance team validates policy compliance. The system generates department-wise spend reports, policy violations, outstanding reimbursements, and monthly cost reports.

## Getting Started
1. **Login**: Access the platform using your credentials.
2. **Navigate to Dashboard**: View an overview of your pending tasks and recent activities.
3. **Verify Success**: Ensure you see your name and role displayed on the top right corner.

## User Roles and Permissions

### Employee
- **Capabilities**: Submit expense claims and travel requests, upload receipts, view reimbursement status.
- **Typical workflows**:
  - Submit a new expense claim.
  - Submit a new travel request.
  - Track the status of submitted claims and requests.
- **Restrictions**: Cannot approve or reject expense claims or travel requests.

### Manager
- **Capabilities**: Approve or reject expense claims and travel requests, view audit history.
- **Typical workflows**:
  - Review and approve an employee's expense claim.
  - Review and approve an employee's travel request.
  - View the audit history of a claim or request.
- **Restrictions**: Cannot modify expense claims or travel requests submitted by employees.

### Finance
- **Capabilities**: Validate policy compliance, generate reports, view department-wise spend and policy violations.
- **Typical workflows**:
  - Validate the policy compliance of approved expense claims.
  - Generate monthly cost reports.
  - Review department-wise spend and policy violations.
- **Restrictions**: Cannot submit expense claims or travel requests.

## Core Workflows

### Submit Expense Claim
This workflow allows employees to submit a new expense claim.

**Steps:**
1. Navigate to the 'Expenses' section by clicking on the 'Expenses' tab.
2. Click 'Submit Expense' to open the expense form.
3. Enter 'employeeId', 'amount', 'description', and 'receiptUrl' with values like:
   - `employeeId: 1`
   - `amount: 100`
   - `description: Lunch`
   - `receiptUrl: http://example.com/receipt.jpg`
4. Click 'Submit' to submit the expense.
5. **Expected outcome**: A success message indicating the expense has been submitted.

**Common mistakes:**
- **Invalid amount**: Ensure the amount is greater than 0.
- **Empty description**: Provide a non-empty description.

### Approve Expense Claim
This workflow allows managers to approve an expense claim.

**Steps:**
1. Navigate to the 'Expenses' section by clicking on the 'Expenses' tab.
2. Click on the expense ID to view the details.
3. Click 'Approve' to open the approval form.
4. Enter 'managerId' and 'approvalComment' with values like:
   - `managerId: 2`
   - `approvalComment: Approved`
5. Click 'Approve' to submit the approval.
6. **Expected outcome**: A success message indicating the expense has been approved.

**Tips:**
- Provide a clear approval comment for audit purposes.

### Generate Department-wise Spend Report
This workflow allows finance to generate a department-wise spend report.

**Steps:**
1. Navigate to the 'Reports' section by clicking on the 'Reports' tab.
2. Click 'Generate Department-wise Spend Report'.
3. **Expected outcome**: A report displaying department-wise spend, policy violations, and outstanding reimbursements.

**Tips:**
- Use filters to narrow down the report to specific departments or time periods.

## Data Management

### Creating Records
To create a new record, navigate to the relevant section (e.g., 'Expenses' for expense claims) and click 'Submit' or 'Add'. Fill in the required fields, such as 'employeeId', 'amount', 'description', and 'receiptUrl'. Ensure all monetary amounts are greater than 0 and descriptions are non-empty.

### Editing Records
To edit an existing record, navigate to the record details page and click 'Edit'. Modify the necessary fields and click 'Save'. Only authorized users can edit records based on their role and permissions.

### Deleting Records
To delete a record, navigate to the record details page and click 'Delete'. Confirm the deletion. Note that deleting a record may have cascading effects on related records, such as approvals or reimbursements.

### Filtering and Search
Use the search bar and filters available in each section to find records. Filters can be applied based on criteria such as date range, department, or status.

## Reports and Exports
The platform offers various reports, including department-wise spend reports, monthly cost reports, and policy violation reports. To generate a report, navigate to the 'Reports' section and click the relevant report button. Reports can be exported in formats such as CSV and PDF.

## Frequently Asked Questions

**Q: Can I submit multiple expense claims at once?**
A: Yes, you can submit multiple expense claims by clicking 'Add Expense' and filling in the details for each claim.

**Q: How do I know if my expense claim has been approved?**
A: You will receive a notification via email once your expense claim has been approved or rejected.

**Q: Can I edit my submitted expense claim?**
A: No, you cannot edit a submitted expense claim. However, you can contact your manager to request changes.

## Troubleshooting

### Common Issues
- **Issue 1: Unable to login**: Ensure you are using the correct credentials. If the issue persists, reset your password.
- **Issue 2: Expense claim not appearing**: Check the status of your claim. If it is still pending, wait for your manager to review it.
- **Issue 3: Report generation error**: Ensure all required fields are filled in and try generating the report again.

### Performance Tips
- Use the latest version of your web browser for optimal performance.
- Clear your browser cache if you experience slow loading times.

## Best Practices
- Always upload receipts for your expense claims to expedite the approval process.
- Regularly review and update your contact information to ensure you receive notifications.
- Use the audit history feature to track changes and approvals on your claims.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
