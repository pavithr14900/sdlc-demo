import React, { useState } from 'react';

interface LeaveRequest {
  id: number;
  employeeId: number;
  leaveTypeId: number;
  startDate: string;
  endDate: string;
  status: string;
}

const LeaveRequestPanel: React.FC = () => {
  const [leaveRequests, setLeaveRequests] = useState<LeaveRequest[]>([]);
  const [newRequest, setNewRequest] = useState<LeaveRequest>({
    id: 0,
    employeeId: 0,
    leaveTypeId: 0,
    startDate: '',
    endDate: '',
    status: 'Pending'
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setNewRequest({...newRequest, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const response = await fetch('/leave-requests', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newRequest)
    });
    const data = await response.json();
    setLeaveRequests([...leaveRequests, data]);
  };

  return (
    <div>
      <h1>Leave Request Panel</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Employee ID:</label>
          <input type="number" name="employeeId" onChange={handleInputChange} required />
        </div>
        <div>
          <label>Leave Type ID:</label>
          <input type="number" name="leaveTypeId" onChange={handleInputChange} required />
        </div>
        <div>
          <label>Start Date:</label>
          <input type="date" name="startDate" onChange={handleInputChange} required />
        </div>
        <div>
          <label>End Date:</label>
          <input type="date" name="endDate" onChange={handleInputChange} required />
        </div>
        <button type="submit">Submit</button>
      </form>
      <h2>Leave Requests</h2>
      <ul>
        {leaveRequests.map(request => (
          <li key={request.id}>
            {request.id} - {request.status}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LeaveRequestPanel;
