import React, { useState } from 'react';

interface LeaveRequest {
    id: string;
    employeeId: string;
    leaveType: string;
    startDate: string;
    endDate: string;
    status: string;
}

const LeaveRequestPanel: React.FC = () => {
    const [leaveRequest, setLeaveRequest] = useState<LeaveRequest>({
        id: '',
        employeeId: '',
        leaveType: '',
        startDate: '',
        endDate: '',
        status: '',
    });

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setLeaveRequest({...leaveRequest, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        // API call to submit leave request
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="employeeId" value={leaveRequest.employeeId} onChange={handleInputChange} placeholder="Employee ID" />
            <input type="text" name="leaveType" value={leaveRequest.leaveType} onChange={handleInputChange} placeholder="Leave Type" />
            <input type="date" name="startDate" value={leaveRequest.startDate} onChange={handleInputChange} />
            <input type="date" name="endDate" value={leaveRequest.endDate} onChange={handleInputChange} />
            <button type="submit">Submit Leave Request</button>
        </form>
    );
};

export default LeaveRequestPanel;
