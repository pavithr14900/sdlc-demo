# User Guide

## Overview
Employees use this application to submit leave requests, view their leave balances, and check the status of their leave requests. It solves the problem of manual leave request processing and helps employees manage their leave more efficiently. With this application, employees can submit leave requests, managers can approve or reject them, and employees can view their leave balances and the status of their requests.

## Getting Started
1. Log in to the application using your employee credentials.
2. Navigate to the "Leave Requests" section.
3. Verify success: You should see a list of your leave requests and their statuses.

## User Roles and Permissions

### Employee
- **Capabilities**: Submit leave requests, view leave balances, view leave request statuses.
- **Typical workflows**: Submit a leave request, view leave balance, check leave request status.
- **Restrictions**: Cannot approve or reject leave requests.

### Manager
- **Capabilities**: Approve or reject leave requests, view employee leave balances.
- **Typical workflows**: Approve a leave request, reject a leave request, view employee leave balances.
- **Restrictions**: Cannot submit leave requests on behalf of employees.

## Core Workflows

### Submit Leave Request
This workflow allows employees to submit a leave request.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the "Leave Requests" tab.
2. Click the "Submit Leave Request" button to open the leave request form.
3. Enter the required fields with values like:
   - `employeeId`: Your employee ID
   - `leaveType`: Type of leave (e.g., SICK, VACATION)
   - `startDate`: Start date of the leave
   - `endDate`: End date of the leave
4. Click the "Submit" button to submit the leave request.
5. **Expected outcome**: You should see a success message and the leave request status should be "PENDING".

**Common mistakes:**
- **Invalid employee ID**: Ensure you enter a valid employee ID.
- **Invalid leave type**: Ensure you select a valid leave type from the predefined options.

### Approve Leave Request
This workflow allows managers to approve a leave request.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the "Leave Requests" tab.
2. Find the leave request you want to approve.
3. Click the "Approve" button next to the leave request.
4. **Expected outcome**: The leave request status should change to "APPROVED".

**Tips:**
- Use the search and filter options to quickly find the leave request you want to approve.

### Reject Leave Request
This workflow allows managers to reject a leave request.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the "Leave Requests" tab.
2. Find the leave request you want to reject.
3. Click the "Reject" button next to the leave request.
4. **Expected outcome**: The leave request status should change to "REJECTED".

**Tips:**
- Use the search and filter options to quickly find the leave request you want to reject.

## Data Management

### Creating Records
To create a new leave request, navigate to the "Leave Requests" section, click the "Submit Leave Request" button, and fill in the required fields. The required fields are `employeeId`, `leaveType`, `startDate`, and `endDate`. Ensure the `startDate` is not in the past and the `endDate` is after the `startDate`.

### Editing Records
Leave requests cannot be edited directly. If a leave request needs to be modified, it must be canceled and a new one submitted with the correct details.

### Deleting Records
Leave requests can be canceled by the employee or manager. Canceling a leave request changes its status to "CANCELED". There are no cascading effects or recovery options for canceled leave requests.

### Filtering and Search
Use the search bar and filters in the "Leave Requests" section to find specific leave requests. You can filter by employee ID, leave type, status, and date range.

## Reports and Exports
Currently, no reports or exports are available. Future versions may include reports on leave usage and approvals.

## Frequently Asked Questions

**Q: How do I submit a leave request?**
A: Navigate to the "Leave Requests" section, click the "Submit Leave Request" button, fill in the required fields, and click "Submit".

**Q: Can I edit a leave request after submitting it?**
A: No, leave requests cannot be edited directly. If changes are needed, the leave request must be canceled and a new one submitted.

**Q: Can I view my leave balance?**
A: Yes, you can view your leave balance in the "Leave Requests" section.

## Troubleshooting

### Common Issues
- **Leave request submission fails**: Ensure all required fields are filled in correctly and the dates are valid.
- **Leave request not found**: Ensure you are searching for the correct leave request ID.
- **Unable to approve/reject leave request**: Ensure you have the necessary permissions and the leave request exists.

### Performance Tips
- Use the search and filter options to quickly find leave requests.
- Avoid submitting leave requests with invalid data to prevent errors.

## Best Practices
- Submit leave requests well in advance to allow time for approval.
- Check the status of your leave requests regularly.
- Ensure your leave type and dates are correct before submitting.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
