import React, { useState } from'react';

interface Expense {
  id: string;
  amount: number;
  description: string;
  date: string;
}

const ExpensePanel: React.FC = () => {
  const [expenses, setExpenses] = useState<Expense[]>([]);
  const [newExpense, setNewExpense] = useState<Expense>({
    id: '',
    amount: 0,
    description: '',
    date: ''
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setNewExpense(prevState => ({...prevState, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setExpenses([...expenses, newExpense]);
    setNewExpense({ id: '', amount: 0, description: '', date: '' });
  };

  return (
    <div>
      <h1>Submit Expense</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Date</label>
          <input type="date" name="date" value={newExpense.date} onChange={handleInputChange} required />
        </div>
        <div>
          <label>Amount</label>
          <input type="number" name="amount" value={newExpense.amount} onChange={handleInputChange} required />
        </div>
        <div>
          <label>Description</label>
          <textarea name="description" value={newExpense.description} onChange={handleInputChange} required />
        </div>
        <button type="submit">Submit</button>
      </form>
      <h2>Submitted Expenses</h2>
      <ul>
        {expenses.map(expense => (
          <li key={expense.id}>
            {expense.date} - {expense.amount} - {expense.description}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ExpensePanel;
