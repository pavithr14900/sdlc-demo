import React, { useState } from 'react';

interface LeaveRequest {
    leaveType: string;
    startDate: string;
    endDate: string;
    reason: string;
}

const LeavePanel: React.FC = () => {
    const [leaveRequest, setLeaveRequest] = useState<LeaveRequest>({
        leaveType: '',
        startDate: '',
        endDate: '',
        reason: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setLeaveRequest(prevState => ({...prevState, [name]: value }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        // Handle form submission logic here
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Leave Type:</label>
                <select name="leaveType" value={leaveRequest.leaveType} onChange={handleChange}>
                    <option value="">Select Leave Type</option>
                    <option value="Vacation">Vacation</option>
                    <option value="Sick Leave">Sick Leave</option>
                    <option value="Personal">Personal</option>
                </select>
            </div>
            <div>
                <label>Start Date:</label>
                <input type="date" name="startDate" value={leaveRequest.startDate} onChange={handleChange} />
            </div>
            <div>
                <label>End Date:</label>
                <input type="date" name="endDate" value={leaveRequest.endDate} onChange={handleChange} />
            </div>
            <div>
                <label>Reason:</label>
                <textarea name="reason" value={leaveRequest.reason} onChange={handleChange}></textarea>
            </div>
            <button type="submit">Submit</button>
        </form>
    );
};

export default LeavePanel;
