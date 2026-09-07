import React from 'react';
import LeaveRequestPanel from './LeaveRequestPanel';

const App: React.FC = () => {
  const handleSubmit = (leaveRequest: any) => {
    console.log('Leave request submitted:', leaveRequest);
  };

  return (
    <div>
      <h1>Apply for Leave</h1>
      <LeaveRequestPanel onSubmit={handleSubmit} />
    </div>
  );
};

export default App;
