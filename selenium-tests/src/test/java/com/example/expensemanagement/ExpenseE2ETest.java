package com.example.expensemanagement;

import com.example.expensemanagement.page.ExpensePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExpenseE2ETest {

    private WebDriver driver;
    private ExpensePage expensePage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        expensePage = new ExpensePage(driver);
    }

    @Test
    public void testSubmitExpense() {
        expensePage.open();
        expensePage.submitExpense("1", 100.0, "Lunch", "2023-10-01");

        assertEquals("1", expensePage.getExpenseId());
        assertEquals("SUBMITTED", expensePage.getExpenseStatus());
    }

    @Test
    public void testInvalidAmount() {
        expensePage.open();
        expensePage.submitExpense("1", -100.0, "Lunch", "2023-10-01");

        assertEquals("Error", expensePage.getErrorMessage());
    }

    @Test
    public void testInvalidDate() {
        expensePage.open();
        expensePage.submitExpense("1", 100.0, "Lunch", "2023-13-01");

        assertEquals("Error", expensePage.getErrorMessage());
    }
}
