# User Guide

## Overview
The Employee Leave Application is designed for employees and HR personnel within our organization to manage leave requests efficiently. Employees can submit leave requests, check their leave balance, and view the status of their requests. HR personnel can approve or reject leave requests, ensuring that the leave management process is streamlined and transparent.

## Getting Started
1. **Login**: Employees and HR personnel can log in using their credentials.
2. **Submit a Leave Request**: Navigate to the leave request section and fill out the form with the required details.
3. **Verify Success**: After submission, you should see a confirmation message and the leave request should appear in your list with a status of "PENDING".

## User Roles and Permissions

### Employee
- **Capabilities**: Submit leave requests, view their leave balance, and check the status of their leave requests.
- **Typical workflows**: 
  - Submit a leave request for vacation.
  - Check the leave balance before submitting a request.
  - View the status of a submitted leave request.
- **Restrictions**: Employees cannot approve or reject leave requests.

### HR Personnel
- **Capabilities**: Approve or reject leave requests, view all leave requests, and view employee leave balances.
- **Typical workflows**: 
  - Review and approve a leave request.
  - Reject a leave request with a reason.
  - View the leave balance of an employee to ensure they have sufficient leave.
- **Restrictions**: HR personnel cannot submit leave requests on behalf of employees.

## Core Workflows

### Submit a Leave Request
This workflow allows employees to submit a leave request for approval.

**Steps:**
1. Navigate to the leave request section by clicking on the "Leave Requests" tab.
2. Click the "Submit Leave Request" button to open the form.
3. Enter the required fields with values like:
   - `employeeId`: 12345
   - `leaveType`: VACATION
   - `startDate`: 2023-10-01
   - `endDate`: 2023-10-05
4. Click the "Submit" button to submit the request.
5. **Expected outcome**: A confirmation message and the leave request should appear in your list with a status of "PENDING".

**Common mistakes:**
- **Invalid employee ID**: Ensure the employee ID is valid and exists in the system.
- **Invalid leave type**: Ensure the leave type is one of the predefined types (SICK, VACATION, PERSONAL).

### Approve a Leave Request
This workflow allows HR personnel to approve a leave request.

**Steps:**
1. Navigate to the leave request section by clicking on the "Leave Requests" tab.
2. Click on the leave request ID to view details.
3. Click the "Approve" button to approve the request.
4. **Expected outcome**: The leave request status should change to "APPROVED".

**Tips:**
- Ensure the leave request is valid and meets the company's leave policy.
- Provide a reason for rejection if the leave request is not approved.

### Reject a Leave Request
This workflow allows HR personnel to reject a leave request.

**Steps:**
1. Navigate to the leave request section by clicking on the "Leave Requests" tab.
2. Click on the leave request ID to view details.
3. Click the "Reject" button to reject the request.
4. **Expected outcome**: The leave request status should change to "REJECTED".

**Tips:**
- Provide a reason for rejection to the employee.
- Ensure the rejection is in line with the company's leave policy.

## Data Management

### Creating Records
To create a new leave request, employees must fill out the form with the required fields: `employeeId`, `leaveType`, `startDate`, and `endDate`. All fields are mandatory, and the `leaveType` must be one of the predefined types (SICK, VACATION, PERSONAL). The `startDate` must be before the `endDate`.

### Editing Records
Only HR personnel can edit leave requests. To edit a leave request, navigate to the leave request section, click on the leave request ID to view details, and then click the "Edit" button. Make the necessary changes and click "Save" to update the request.

### Deleting Records
Leave requests cannot be deleted. However, they can be rejected, which effectively removes them from the pending list. Deleting records is not supported to maintain an audit trail of leave requests.

### Filtering and Search
Users can filter and search leave requests by employee ID, leave type, and status. The search functionality allows users to quickly find specific leave requests.

## Reports and Exports
HR personnel can generate reports on leave requests, including pending, approved, and rejected requests. Reports can be exported in CSV and PDF formats for further analysis.

## Frequently Asked Questions

**Q: How can I check my leave balance?**
A: Navigate to the leave balance section by clicking on the "Leave Balance" tab. Your leave balance will be displayed based on your leave type.

**Q: Can I change the dates of my leave request?**
A: No, once a leave request is submitted, the dates cannot be changed. You will need to submit a new leave request if changes are required.

**Q: Can I submit a leave request for a future date?**
A: Yes, you can submit a leave request for a future date. Ensure the dates are valid and within the company's leave policy.

## Troubleshooting

### Common Issues
- **Leave request not found**: Ensure the leave request ID is correct and exists in the system.
- **Database connection failure**: Check the database connection settings and ensure the database server is running.
- **Invalid input data**: Ensure all input data meets the validation rules and is in the correct format.

### Performance Tips
- Use indexing on frequently queried fields to improve search performance.
- Regularly monitor and optimize database queries to ensure efficient data retrieval.

## Best Practices
- Always validate input data to prevent invalid requests.
- Use parameterized queries or Spring Data JPA to prevent SQL injection.
- Implement rate limiting to prevent abuse of API endpoints.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
