# User Guide

## Overview
The employee leave application is designed for employees within our organization to submit leave requests, view their leave balances, and track the status of their leave requests. This application aims to streamline the leave management process, ensuring that employees can easily request leave and managers can efficiently approve or reject these requests. With this tool, employees can manage their leave more effectively, and HR can maintain accurate records of leave balances and approvals.

## Getting Started
To get started with the employee leave application, follow these steps:

1. **Login**: Access the application and log in using your employee credentials.
2. **Submit Leave Request**: Navigate to the leave request section and fill out the necessary details.
3. **Verify Submission**: After submitting a leave request, you should see a confirmation message indicating that your request has been received.

## User Roles and Permissions

### Employee
- **Capabilities**: Submit leave requests, view leave balance, and check the status of their leave requests.
- **Typical workflows**: Submit a leave request, check the status of a leave request, view leave balance.
- **Restrictions**: Cannot approve or reject leave requests.

### Manager
- **Capabilities**: Approve or reject leave requests submitted by employees.
- **Typical workflows**: Review a leave request, approve or reject the request, view employee leave balances.
- **Restrictions**: Cannot modify leave request details.

### HR
- **Capabilities**: Manage employee data, view and manage leave balances, and oversee the leave approval process.
- **Typical workflows**: Add or update employee information, approve or reject leave requests, view overall leave statistics.
- **Restrictions**: Cannot submit leave requests on behalf of employees.

## Core Workflows

### Submit Leave Request
This workflow allows employees to request leave by specifying the type of leave, start date, and end date.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu.
2. Click the "Submit Leave Request" button to open the form.
3. Enter the following fields with values like:
   - `employeeId`: `1`
   - `leaveType`: `SICK`
   - `startDate`: `2023-10-01`
   - `endDate`: `2023-10-05`
4. Click the "Submit" button to send the request.
5. **Expected outcome**: A confirmation message stating that the leave request has been submitted.

**Common mistakes:**
- **Incorrect leave type**: Ensure you select a valid leave type (SICK, VACATION, PERSONAL). If incorrect, correct the leave type and resubmit.
- **Invalid date range**: Ensure the start date is not in the past and the end date is after the start date. If invalid, correct the dates and resubmit.

### View Leave Balance
This workflow allows employees to check their remaining leave balance.

**Steps:**
1. Navigate to the "Leave Balance" section by clicking on the menu.
2. Click the "View Balance" button to display your leave balance.
3. **Expected outcome**: A list of leave types and their respective balances.

**Tips:**
- Regularly check your leave balance to plan your leave requests accordingly.
- Contact HR if you notice any discrepancies in your leave balance.

### Approve or Reject Leave Request
This workflow allows managers to approve or reject leave requests submitted by employees.

**Steps:**
1. Navigate to the "Leave Requests" section by clicking on the menu.
2. Click on the leave request you want to approve or reject.
3. Click the "Approve" or "Reject" button to change the status.
4. **Expected outcome**: The leave request status should update to "APPROVED" or "REJECTED".

**Tips:**
- Review leave requests promptly to avoid delays in the leave approval process.
- Provide feedback to employees if their leave request is rejected.

## Data Management

### Creating Records
To create a new leave request, employees must fill out the leave request form with the required details: `employeeId`, `leaveType`, `startDate`, and `endDate`. The form will validate these fields to ensure they meet the specified criteria.

### Editing Records
Employees cannot edit their leave requests directly. If an error is found, they must contact HR to make the necessary changes. Managers can approve or reject leave requests but cannot modify the request details.

### Deleting Records
Leave requests cannot be deleted once submitted. If a request needs to be voided, HR must manually update the status to "CANCELLED".

### Filtering and Search
Users can filter leave requests by employee ID, leave type, and status. The search functionality allows users to find specific leave requests quickly.

## Reports and Exports
HR can generate reports on leave requests, approvals, and rejections. These reports can be exported in CSV and PDF formats for further analysis.

## Frequently Asked Questions

**Q: Can I submit a leave request for a past date?**
A: No, you cannot submit a leave request for a past date. The start date must be in the future.

**Q: What should I do if my leave request is rejected?**
A: If your leave request is rejected, you can contact your manager for feedback and resubmit the request with necessary changes.

**Q: Can I request leave during a weekend or holiday?**
A: Yes, you can request leave during weekends or holidays. The system will automatically adjust the leave balance accordingly.

## Troubleshooting

### Common Issues
- **Leave request not submitted**: Ensure all required fields are filled out correctly. If the issue persists, contact support.
- **Unable to approve/reject leave request**: Ensure you have the necessary permissions. If the issue persists, contact HR.
- **Incorrect leave balance**: Ensure all leave requests are correctly processed. If the issue persists, contact HR.

### Performance Tips
- Ensure your internet connection is stable when submitting leave requests.
- Clear your browser cache if you experience any loading issues.

## Best Practices
- Submit leave requests well in advance to allow sufficient time for approval.
- Regularly check your leave balance to plan your leave effectively.
- Contact HR if you notice any discrepancies in your leave balance.

## Getting Help
- **In-app help**: Click the? icon for contextual help.
- **Documentation**: See the API Documentation and README for technical details.
- **Contact support**: Email support@example.com with:
  - What you were trying to do
  - Error message (if any)
  - Screenshots or logs
  - Your role and username
