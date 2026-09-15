import React from 'react';
import ReactDOM from'react-dom/client';
import LeaveRequestPanel from './components/LeaveRequestPanel';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
  <React.StrictMode>
    <LeaveRequestPanel />
  </React.StrictMode>
);
