package com.example.expense.service;

import com.example.expense.model.Expense;
import com.example.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(UUID id) {
        return expenseRepository.findById(id);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(UUID id) {
        expenseRepository.deleteById(id);
    }

    public Expense approveExpense(UUID id) {
        Expense expense = expenseRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setStatus(Expense.ExpenseStatus.APPROVED);
        return expenseRepository.save(expense);
    }

    public Expense rejectExpense(UUID id) {
        Expense expense = expenseRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setStatus(Expense.ExpenseStatus.REJECTED);
        return expenseRepository.save(expense);
    }
}
