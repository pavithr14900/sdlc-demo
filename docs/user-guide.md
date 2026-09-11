# User Guide

## Overview
Employees use this application to submit leave requests, view their leave balances, and track the status of their leave applications. It solves the problem of manual leave management by providing a streamlined, automated process. With this application, employees can easily submit leave requests, managers can approve or reject them, and employees can view their leave balances and the status of their applications.

## Getting Started
1. **Login**: Employees should log in using their credentials.
2. **Submit Leave Application**: Navigate to the leave application section and fill out the required fields.
3. **Verify Success**: After submission, employees should see a confirmation message with the leave application ID and status.

## User Roles and Permissions

### Employee
- **Capabilities**: Submit leave applications, view leave balances, view leave application status.
- **Typical workflows**: Submit a leave application, view leave balance, check leave application status.
- **Restrictions**: Cannot approve or reject leave applications.

### Manager
- **Capabilities**: Approve or reject leave applications.
- **Typical workflows**: Review leave applications, approve or reject them.
- **Restrictions**: Cannot submit leave applications on behalf of employees.

## Core Workflows

### Submit Leave Application
This workflow allows employees to submit a leave application.

**Steps:**
1. Navigate to the "Leave Application" section by clicking on the menu.
2. Click the "Submit Leave" button to open the leave application form.
3. Enter fields with values like:
   - `employeeId`: `1`
   - `leaveType`: `SICK`
   - `startDate`: `2023-10-01`
   - `endDate`: `2023-10-05`
4. Click the "Submit" button to submit the leave application.
5. **Expected outcome**: A confirmation message with the leave application ID and status.

**Common mistakes:**
- **Invalid employee ID**: Ensure the employee ID is valid and exists in the system.
- **Invalid leave type**: Ensure the leave type is one of "SICK", "VACATION", "PERSONAL".

### Retrieve Leave Application Details
This workflow allows employees to view the details of their leave applications.

**Steps:**
1. Navigate to the "Leave Application" section by clicking on the menu.
2. Click on the leave application ID to view details.
3. **Expected outcome**: The leave application details, including ID, employee ID, leave type, start date, end date, and status.

**Tips:**
- Use the search bar to quickly find a specific leave application.
- Click the refresh button to update the leave application status.

### Get Employee Leave Balance
This workflow allows employees to view their leave balances.

**Steps:**
1. Navigate to the "Leave Balance" section by clicking on the menu.
2. Enter the employee ID.
3. Click the "Get Balance" button.
4. **Expected outcome**: The leave balance for the specified employee ID.

**Tips:**
- Use the dropdown menu to select the leave type.
- Click the refresh button to update the leave balance.

## Data Management

### Creating Records
To create a new leave application record, employees should navigate to the "Leave Application" section and fill out the required fields. Validation rules include ensuring the employee ID is valid, the leave type is one of "SICK", "VACATION", "PERSONAL", and the end date is after the start date.

### Editing Records
Editing records is not supported in this application. Employees can only submit new leave applications or view existing ones.

### Deleting Records
Deleting records is not supported in this application. Leave applications are managed through approval or rejection workflows.

### Filtering and Search
Employees can filter and search leave applications by employee ID, leave type, and status. The search bar allows for quick lookup of specific leave applications.

## Reports and Exports
This application does not provide reports or exports. Employees can view their leave balances and application status directly in the application.

## Frequently Asked Questions

**Q: Can I submit a leave application for a future date?**
A: Yes, you can submit a leave application for a future date. Ensure the end date is after the start date.

**Q: What happens if my leave application is rejected?**
A: If your leave application is rejected, you will receive a notification with the reason for rejection. You can resubmit the leave application with corrected information.

**Q: Can I view my leave balance in different leave types?**
A: Yes, you can view your leave balance for different leave types by selecting the leave type in the "Leave Balance" section.

## Troubleshooting

### Common Issues
- **Issue 1**: Leave application not submitted.
  - **How to diagnose**: Check the input fields for errors.
  - **How to fix**: Ensure all required fields are filled out correctly.
- **Issue 2**: Leave balance not updating.
  - **How to diagnose**: Check the leave application status.
  - **How to fix**: Ensure the leave application has been approved.

### Performance Tips
- Ensure the database is properly indexed to speed up queries.
- Use caching for frequently accessed data to improve performance.

## Best Practices
- Always validate input data to prevent errors.
- Use parameterized queries to prevent SQL injection.
- Implement rate limiting to prevent API abuse.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
