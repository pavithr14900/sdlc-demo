import React, { useState } from'react';

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
    status: ''
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setLeaveRequest({...leaveRequest, [name]: value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Handle form submission here
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Leave Type</label>
        <select name="leaveType" value={leaveRequest.leaveType} onChange={handleChange}>
          <option value="">Select Leave Type</option>
          <option value="SICK">Sick Leave</option>
          <option value="VACATION">Vacation Leave</option>
          <option value="PERSONAL">Personal Leave</option>
        </select>
      </div>
      <div>
        <label>Start Date</label>
        <input type="date" name="startDate" value={leaveRequest.startDate} onChange={handleChange} />
      </div>
      <div>
        <label>End Date</label>
        <input type="date" name="endDate" value={leaveRequest.endDate} onChange={handleChange} />
      </div>
      <div>
        <label>Reason</label>
        <textarea name="reason" />
      </div>
      <button type="submit">Submit Leave Request</button>
    </form>
  );
};

export default LeaveRequestPanel;
