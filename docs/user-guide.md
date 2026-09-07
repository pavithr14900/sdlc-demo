# User Guide

## Overview
This application is designed for employees within our organization to manage their leave requests. It allows employees to submit leave requests, view their leave history, and track their leave balances. Managers can approve or reject leave requests, ensuring that leave management is efficient and transparent.

## Getting Started
1. **Login**: Employees and managers should log in using their credentials.
2. **Navigate to Leave Requests**: After logging in, navigate to the "Leave Requests" section.
3. **Verify Success**: You should see a list of available actions such as "Submit Leave Request" and "View Leave History".

## User Roles and Permissions

### Employee
- **Capabilities**: Submit leave requests, view leave history, and check leave balance.
- **Typical workflows**: Submit a new leave request, view pending leave requests, check leave balance.
- **Restrictions**: Cannot approve or reject leave requests.

### Manager
- **Capabilities**: Approve or reject leave requests, view all leave requests within their team.
- **Typical workflows**: Review pending leave requests, approve or reject leave requests, view team leave history.
- **Restrictions**: Cannot modify leave balance or employee information.

## Core Workflows

### Submit a Leave Request
This workflow allows employees to submit a new leave request.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu item.
2. Click the "Submit Leave Request" button to open the form.
3. Enter the following fields with values like:
   - `employeeId`: `1`
   - `leaveType`: `SICK`
   - `startDate`: `2023-10-01`
   - `endDate`: `2023-10-05`
4. Click the "Submit" button to submit the request.
5. **Expected outcome**: A success message indicating that the leave request has been submitted.

**Common mistakes:**
- **Invalid employee ID**: Ensure the employee ID is valid and exists in the system.
- **Invalid leave type**: Ensure the leave type is one of "SICK", "VACATION", "PERSONAL".

### Approve a Leave Request
This workflow allows managers to approve a leave request.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu item.
2. Click on the leave request you want to approve.
3. Click the "Approve" button to change the status of the leave request.
4. **Expected outcome**: The leave request status should change to "APPROVED".

**Tips:**
- Ensure you have the necessary permissions to approve leave requests.
- Double-check the leave dates to ensure they are valid.

### Reject a Leave Request
This workflow allows managers to reject a leave request.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu item.
2. Click on the leave request you want to reject.
3. Click the "Reject" button to change the status of the leave request.
4. **Expected outcome**: The leave request status should change to "REJECTED".

**Tips:**
- Ensure you have the necessary permissions to reject leave requests.
- Provide a reason for rejection if required.

## Data Management

### Creating Records
To create a new leave request, navigate to the "Leave Requests" section and click the "Submit Leave Request" button. Fill in the required fields: `employeeId`, `leaveType`, `startDate`, and `endDate`. Click "Submit" to create the record.

### Editing Records
Editing records is not supported in this application. Once a leave request is submitted, it cannot be modified. However, managers can approve or reject the request, effectively changing its status.

### Deleting Records
Deleting records is not supported in this application. Leave requests are tracked for historical purposes and cannot be deleted.

### Filtering and Search
Use the search bar at the top of the "Leave Requests" section to find specific leave requests by employee ID, leave type, or status.

## Reports and Exports
Currently, the application does not support generating reports or exporting data. However, managers can view detailed leave history and pending requests within the application.

## Frequently Asked Questions

**Q: Can I submit a leave request for a future date?**
A: Yes, you can submit a leave request for a future date. Ensure the start date is after the current date.

**Q: What happens if my leave request is rejected?**
A: If your leave request is rejected, you will receive a notification with the reason for rejection. You can resubmit the request with the necessary changes.

**Q: Can I view my leave balance?**
A: Yes, you can view your leave balance in the "Leave Requests" section. Your current leave balance is displayed at the top of the page.

## Troubleshooting

### Common Issues
- **Leave request not submitted**: Ensure all required fields are filled in correctly. Check for any validation errors.
- **Unable to approve/reject leave request**: Ensure you have the necessary permissions and the leave request is in a pending state.
- **Error in leave dates**: Ensure the start date is before the end date and both dates are valid.

### Performance Tips
- Refresh the page if you experience slow loading times.
- Clear your browser cache if the application is not responding.

## Best Practices
- Submit leave requests well in advance to allow time for approval.
- Check your leave balance regularly to plan future leave.
- Communicate with your manager if you need to make changes to a submitted leave request.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
