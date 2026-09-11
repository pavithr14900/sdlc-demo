import React from 'react';
import ReactDOM from'react-dom';
import LeaveRequestPanel from './components/LeaveRequestPanel';

const App = () => {
  return (
    <div>
      <LeaveRequestPanel />
    </div>
  );
};

ReactDOM.render(<App />, document.getElementById('root'));
