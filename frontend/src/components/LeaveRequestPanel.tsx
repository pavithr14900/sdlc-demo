import React, { useState } from 'react';

interface LeaveRequestFormProps {
  onSubmit: (leaveRequest: LeaveRequest) => void;
}

interface LeaveRequest {
  leaveType: string;
  startDate: string;
  endDate: string;
  reason: string;
}

const LeaveRequestPanel: React.FC<LeaveRequestFormProps> = ({ onSubmit }) => {
  const [leaveType, setLeaveType] = useState<string>('');
  const [startDate, setStartDate] = useState<string>('');
  const [endDate, setEndDate] = useState<string>('');
  const [reason, setReason] = useState<string>('');

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    const leaveRequest: LeaveRequest = {
      leaveType,
      startDate,
      endDate,
      reason,
    };
    onSubmit(leaveRequest);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Leave Type:</label>
        <select value={leaveType} onChange={(e) => setLeaveType(e.target.value)}>
          <option value="">Select Leave Type</option>
          <option value="SICK">Sick Leave</option>
          <option value="VACATION">Vacation Leave</option>
          <option value="PERSONAL">Personal Leave</option>
        </select>
      </div>
      <div>
        <label>Start Date:</label>
        <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
      </div>
      <div>
        <label>End Date:</label>
        <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
      </div>
      <div>
        <label>Reason:</label>
        <textarea value={reason} onChange={(e) => setReason(e.target.value)}></textarea>
      </div>
      <button type="submit">Submit Request</button>
    </form>
  );
};

export default LeaveRequestPanel;
