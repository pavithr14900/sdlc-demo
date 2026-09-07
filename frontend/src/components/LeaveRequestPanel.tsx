import React, { useState } from 'react';

interface LeaveRequestFormProps {
  onSubmit: (leaveRequest: LeaveRequest) => void;
}

interface LeaveRequest {
  employeeId: string;
  leaveType: string;
  startDate: string;
  endDate: string;
  reason: string;
}

const LeaveRequestPanel: React.FC<LeaveRequestFormProps> = ({ onSubmit }) => {
  const [form, setForm] = useState<LeaveRequest>({
    employeeId: '',
    leaveType: '',
    startDate: '',
    endDate: '',
    reason: ''
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    setForm({...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Employee ID:</label>
        <input type="text" name="employeeId" value={form.employeeId} onChange={handleChange} required />
      </div>
      <div>
        <label>Leave Type:</label>
        <select name="leaveType" value={form.leaveType} onChange={handleChange} required>
          <option value="">Select Leave Type</option>
          <option value="SICK">Sick Leave</option>
          <option value="VACATION">Vacation</option>
          <option value="PERSONAL">Personal</option>
        </select>
      </div>
      <div>
        <label>Start Date:</label>
        <input type="date" name="startDate" value={form.startDate} onChange={handleChange} required />
      </div>
      <div>
        <label>End Date:</label>
        <input type="date" name="endDate" value={form.endDate} onChange={handleChange} required />
      </div>
      <div>
        <label>Reason:</label>
        <textarea name="reason" value={form.reason} onChange={handleChange} required />
      </div>
      <button type="submit">Submit</button>
    </form>
  );
};

export default LeaveRequestPanel;
