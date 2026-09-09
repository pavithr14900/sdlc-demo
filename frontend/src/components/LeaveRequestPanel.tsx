import React, { useState } from 'react';

interface LeaveRequest {
  id: string;
  employeeId: string;
  startDate: string;
  endDate: string;
  status: string;
}

const LeaveRequestPanel: React.FC = () => {
  const [leaveRequest, setLeaveRequest] = useState<LeaveRequest>({
    id: '',
    employeeId: '',
    startDate: '',
    endDate: '',
    status: '',
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
      <label>
        Leave Type:
        <select name="leaveType" onChange={handleChange}>
          <option value="SICK">Sick Leave</option>
          <option value="VACATION">Vacation Leave</option>
          <option value="MATERNITY">Maternity Leave</option>
        </select>
      </label>
      <label>
        Start Date:
        <input type="date" name="startDate" onChange={handleChange} />
      </label>
      <label>
        End Date:
        <input type="date" name="endDate" onChange={handleChange} />
      </label>
      <label>
        Reason:
        <textarea name="reason" onChange={handleChange}></textarea>
      </label>
      <button type="submit">Submit Request</button>
    </form>
  );
};

export default LeaveRequestPanel;
