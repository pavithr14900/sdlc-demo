import React, { useState } from'react';

interface LeaveRequest {
  id: number;
  employeeId: number;
  startDate: string;
  endDate: string;
  leaveType: string;
  status: string;
}

const LeaveRequestPanel: React.FC = () => {
  const [leaveRequest, setLeaveRequest] = useState<LeaveRequest>({
    id: 0,
    employeeId: 0,
    startDate: '',
    endDate: '',
    leaveType: '',
    status: 'PENDING',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setLeaveRequest({...leaveRequest, [name]: value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Handle form submission
  };

  return (
    <div>
      <h2>Apply for Leave</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Start Date</label>
          <input
            type="date"
            name="startDate"
            value={leaveRequest.startDate}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>End Date</label>
          <input
            type="date"
            name="endDate"
            value={leaveRequest.endDate}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Reason</label>
          <input
            type="text"
            name="leaveType"
            value={leaveRequest.leaveType}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default LeaveRequestPanel;
