import React, { useState } from'react';
import { Table, Button, Form, FormGroup, Label, Input } from 'reactstrap';

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
    status: 'PENDING',
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setNewExpense({...newExpense, [name]: value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Submit expense logic here
    setExpenses([...expenses, newExpense]);
    setNewExpense({
      id: '',
      employeeId: '',
      amount: 0,
      description: '',
      date: '',
      status: 'PENDING',
    });
  };

  return (
    <div>
      <h2>Submit Expense</h2>
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for="date">Date</Label>
          <Input type="date" name="date" id="date" onChange={handleInputChange} />
        </FormGroup>
        <FormGroup>
          <Label for="amount">Amount</Label>
          <Input type="number" name="amount" id="amount" onChange={handleInputChange} />
        </FormGroup>
        <FormGroup>
          <Label for="description">Description</Label>
          <Input type="text" name="description" id="description" onChange={handleInputChange} />
        </FormGroup>
        <FormGroup>
          <Label for="category">Category</Label>
          <Input type="select" name="category" id="category">
            <option value="Travel">Travel</option>
            <option value="Meals">Meals</option>
            <option value="Office Supplies">Office Supplies</option>
          </Input>
        </FormGroup>
        <Button color="secondary" onClick={() => { /* Upload receipt logic here */ }}>Upload Receipt</Button>
        <Button type="submit" color="primary">Submit Expense</Button>
      </Form>
      <Table>
        <thead>
          <tr>
            <th>Date</th>
            <th>Amount</th>
            <th>Description</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map(expense => (
            <tr key={expense.id}>
              <td>{expense.date}</td>
              <td>{expense.amount}</td>
              <td>{expense.description}</td>
              <td>{expense.status}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default ExpensePanel;
