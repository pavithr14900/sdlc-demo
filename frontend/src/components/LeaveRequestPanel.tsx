import React, { useState } from 'react';

interface LeaveRequest {
    id: string;
    employeeId: string;
    leaveType: string;
    startDate: string;
    endDate: string;
    status: string;
}

interface LeaveRequestPanelProps {
    leaveRequests: LeaveRequest[];
    onApprove: (id: string) => void;
    onReject: (id: string) => void;
}

const LeaveRequestPanel: React.FC<LeaveRequestPanelProps> = ({ leaveRequests, onApprove, onReject }) => {
    return (
        <div>
            <h2>Leave Requests</h2>
            <ul>
                {leaveRequests.map(request => (
                    <li key={request.id}>
                        <p>Employee ID: {request.employeeId}</p>
                        <p>Leave Type: {request.leaveType}</p>
                        <p>Start Date: {request.startDate}</p>
                        <p>End Date: {request.endDate}</p>
                        <p>Status: {request.status}</p>
                        <button onClick={() => onApprove(request.id)}>Approve</button>
                        <button onClick={() => onReject(request.id)}>Reject</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default LeaveRequestPanel;
