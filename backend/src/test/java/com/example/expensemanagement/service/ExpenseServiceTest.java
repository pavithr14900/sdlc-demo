package com.example.expensemanagement.service;

import com.example.expensemanagement.dto.ExpenseDto;
import com.example.expensemanagement.exception.ValidationException;
import com.example.expensemanagement.model.Expense;
import com.example.expensemanagement.repository.ExpenseRepository;
import com.example.expensemanagement.service.impl.ExpenseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitExpense_happyPath() {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setEmployeeId("1");
        expenseDto.setAmount(100.0);
        expenseDto.setDescription("Lunch");
        expenseDto.setDate("2023-10-01");

        Expense expense = new Expense();
        expense.setId("1");
        expense.setStatus("SUBMITTED");

        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);

        ExpenseDto result = expenseService.submitExpense(expenseDto);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("SUBMITTED", result.getStatus());
    }

    @Test
    void submitExpense_invalidAmount() {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setEmployeeId("1");
        expenseDto.setAmount(-100.0);
        expenseDto.setDescription("Lunch");
        expenseDto.setDate("2023-10-01");

        assertThrows(ValidationException.class, () -> expenseService.submitExpense(expenseDto));
    }

    @Test
    void submitExpense_invalidDate() {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setEmployeeId("1");
        expenseDto.setAmount(100.0);
        expenseDto.setDescription("Lunch");
        expenseDto.setDate("2023-13-01");

        assertThrows(ValidationException.class, () -> expenseService.submitExpense(expenseDto));
    }
}
