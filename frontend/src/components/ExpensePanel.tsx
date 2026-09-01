import React, { useState } from 'react';
import axios from 'axios';

interface Expense {
    id: string;
    employeeId: string;
    amount: number;
    description: string;
    date: string;
    status: string;
}

interface ExpensePanelProps {
    onExpenseCreated: (expense: Expense) => void;
}

const ExpensePanel: React.FC<ExpensePanelProps> = ({ onExpenseCreated }) => {
    const [employeeId, setEmployeeId] = useState<string>('');
    const [amount, setAmount] = useState<number>(0);
    const [description, setDescription] = useState<string>('');
    const [date, setDate] = useState<string>(new Date().toISOString().split('T')[0]);

    const handleSubmit = async () => {
        const newExpense: Expense = {
            id: '',
            employeeId,
            amount,
            description,
            date,
            status: 'SUBMITTED'
        };
        try {
            const response = await axios.post<Expense>('/api/expenses', newExpense);
            onExpenseCreated(response.data);
        } catch (error) {
            console.error('Error creating expense:', error);
        }
    };

    return (
        <div>
            <h2>Submit Expense</h2>
            <input
                type="text"
                placeholder="Employee ID"
                value={employeeId}
                onChange={(e) => setEmployeeId(e.target.value)}
            />
            <input
                type="number"
                placeholder="Amount"
                value={amount}
                onChange={(e) => setAmount(Number(e.target.value))}
            />
            <input
                type="text"
                placeholder="Description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
            />
            <input
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
            />
            <button onClick={handleSubmit}>Submit</button>
        </div>
    );
};

export default ExpensePanel;
