import React, { useState } from'react';

interface Expense {
  id: string;
  employeeId: string;
  amount: number;
  description: string;
  date: string;
  status: string;
}

const ExpensePanel: React.FC = () => {
  const [expenses, setExpenses] = useState<Expense[]>([]);
  const [newExpense, setNewExpense] = useState<Expense>({
    id: '',
    employeeId: '',
    amount: 0,
    description: '',
    date: '',
    status: 'SUBMITTED'
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setNewExpense(prevState => ({...prevState, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // API call to submit expense
    setExpenses([...expenses, newExpense]);
    setNewExpense({
      id: '',
      employeeId: '',
      amount: 0,
      description: '',
      date: '',
      status: 'SUBMITTED'
    });
  };

  return (
    <div>
      <h1>Submit Expense</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Expense Date</label>
          <input type="date" name="date" value={newExpense.date} onChange={handleInputChange} required />
        </div>
        <div>
          <label>Expense Description</label>
          <input type="text" name="description" value={newExpense.description} onChange={handleInputChange} required />
        </div>
        <div>
          <label>Amount</label>
          <input type="number" name="amount" value={newExpense.amount} onChange={handleInputChange} required />
        </div>
        <div>
          <label>Receipt</label>
          <input type="file" name="receipt" onChange={handleInputChange} />
        </div>
        <button type="submit">Submit Expense</button>
      </form>
      <h2>Submitted Expenses</h2>
      <ul>
        {expenses.map(expense => (
          <li key={expense.id}>
            {expense.description} - ${expense.amount} - {expense.date} - {expense.status}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ExpensePanel;
