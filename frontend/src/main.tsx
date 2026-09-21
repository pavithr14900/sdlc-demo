import React from 'react';
import ReactDOM from'react-dom/client';
import ExpensePanel from './components/ExpensePanel';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
  <React.StrictMode>
    <ExpensePanel />
  </React.StrictMode>
);
