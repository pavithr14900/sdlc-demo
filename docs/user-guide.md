# User Guide

## Overview
The expense management application is designed for employees and finance managers within an organization to streamline the process of submitting, approving, and reporting on expenses. This application solves the problem of manual expense tracking and approval, allowing for a more efficient and transparent process. With this application, users can submit expense claims, managers can approve or reject these claims, and finance teams can generate detailed expense reports.

## Getting Started
1. **Login**: Access the application and log in using your credentials.
2. **Navigate to Expense Submission**: Go to the dashboard and click on the "Submit Expense" button.
3. **Verify Success**: Upon successful login, you should see the dashboard with options to submit, view, and manage expenses.

## User Roles and Permissions

### Employee
- **Capabilities**: Submit expense claims, view submitted expenses, generate personal expense reports.
- **Typical workflows**: Submit a new expense, view the status of submitted expenses, generate a monthly expense report.
- **Restrictions**: Cannot approve or reject other employees' expenses.

### Manager
- **Capabilities**: Approve or reject expense claims, view all submitted expenses, generate department expense reports.
- **Typical workflows**: Review and approve submitted expenses, reject invalid expenses, generate reports for team expenses.
- **Restrictions**: Cannot modify other managers' actions or access sensitive employee data.

### Finance Manager
- **Capabilities**: Oversee all expense claims, generate organization-wide expense reports, manage user roles and permissions.
- **Typical workflows**: Review all submitted expenses, approve or reject claims, generate annual expense reports, manage user access.
- **Restrictions**: Cannot change the application's core functionalities or access raw database records directly.

## Core Workflows

### Submit an Expense
This workflow is used when an employee needs to claim an expense.

**Steps:**
1. Navigate to the "Submit Expense" section by clicking the button on the dashboard.
2. Click the "New Expense" button to open the expense submission form.
3. Enter fields with values like:
   - `employeeId`: `1`
   - `amount`: `100`
   - `description`: `Lunch`
   - `date`: `2023-10-01`
4. Click the "Submit" button to submit the expense.
5. **Expected outcome**: A success message indicating the expense has been submitted.

**Common mistakes:**
- **Invalid amount**: Ensure the amount is a positive number. Fix by entering a valid amount.
- **Invalid date format**: Ensure the date is in YYYY-MM-DD format. Fix by entering a valid date.

### Approve an Expense
This workflow is used when a manager needs to approve an employee's expense claim.

**Steps:**
1. Navigate to the "Expense Approval" section by clicking the link on the dashboard.
2. Click on the expense to be approved.
3. Click the "Approve" button to approve the expense.
4. **Expected outcome**: The expense status should change to "APPROVED".

**Tips:**
- Use the search function to quickly find specific expenses.
- Check the approval history for previous actions on the expense.

### Generate an Expense Report
This workflow is used when a finance manager needs to generate a report on expenses.

**Steps:**
1. Navigate to the "Reports" section by clicking the link on the dashboard.
2. Click the "Generate Report" button.
3. Select the date range and click "Generate".
4. **Expected outcome**: A detailed report showing total expenses, approved expenses, and rejected expenses.

**Tips:**
- Use filters to narrow down the report to specific departments or employees.
- Export the report in CSV format for further analysis.

## Data Management

### Creating Records
To create a new expense record, navigate to the "Submit Expense" section, fill in the required fields (employeeId, amount, description, date), and click "Submit". Required fields must be filled out, and the amount must be a positive number.

### Editing Records
Editing records is not supported in this application to maintain audit integrity. If an error is found in a submitted expense, it should be rejected and resubmitted with corrections.

### Deleting Records
Deleting records is not supported. Instead, expenses can be rejected if they are found to be invalid. This ensures a complete audit trail.

### Filtering and Search
Use the search bar at the top of the expense list to find specific expenses by employee name, date, or amount. Filters can be applied to view expenses by status (e.g., pending, approved, rejected).

## Reports and Exports
Expense reports can be generated from the "Reports" section. Reports can be filtered by date range, employee, and status. Reports can be exported in CSV format for further analysis.

## Frequently Asked Questions

**Q: Can I submit an expense after the month-end?**
A: No, expenses can only be submitted within the current month. Submit any pending expenses before the month-end.

**Q: What should I do if my expense is rejected?**
A: Review the rejection reason, correct any errors, and resubmit the expense.

**Q: Can I view expenses submitted by other employees?**
A: Yes, managers and finance managers can view all submitted expenses. Employees can only view their own expenses.

## Troubleshooting

### Common Issues
- **Expense not submitting**: Ensure all required fields are filled out correctly. Fix by entering valid data.
- **Unable to approve expense**: Ensure you have the correct role and permissions. Fix by checking your role settings.
- **Report not generating**: Ensure the date range is correctly set. Fix by selecting a valid date range.

### Performance Tips
- Use the search and filter functions to quickly find specific data.
- Regularly clear your browser cache to ensure the application runs smoothly.

## Best Practices
- Submit expenses as soon as possible after the expense occurs.
- Regularly review and approve submitted expenses to avoid delays.
- Use the reporting feature to monitor expense trends and manage budgets effectively.
