package com.example.leave;

import com.example.leave.model.LeaveRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class LeaveRequestE2ETest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:8080");
    }

    @AfterEach
    void tearDown() {
        if (driver!= null) {
            driver.quit();
        }
    }

    @Test
    void testCreateLeaveRequest() {
        driver.findElement(By.linkText("Leave Requests")).click();
        driver.findElement(By.linkText("Create New Leave Request")).click();

        WebElement employeeIdInput = driver.findElement(By.id("employeeId"));
        WebElement startDateInput = driver.findElement(By.id("startDate"));
        WebElement endDateInput = driver.findElement(By.id("endDate"));

        employeeIdInput.sendKeys("1");
        startDateInput.sendKeys("2023-01-01");
        endDateInput.sendKeys("2023-01-02");

        driver.findElement(By.id("submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        assertTrue(driver.getPageSource().contains("Leave request created successfully"));
    }
}
