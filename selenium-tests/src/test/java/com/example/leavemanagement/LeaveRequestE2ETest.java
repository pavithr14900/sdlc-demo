package com.example.leavemanagement;

import com.example.leavemanagement.page.LeaveRequestPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LeaveRequestE2ETest {

    private WebDriver driver;
    private LeaveRequestPage leaveRequestPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        leaveRequestPage = new LeaveRequestPage(driver);
    }

    @Test
    public void testSubmitLeaveRequest_Success() {
        leaveRequestPage.open();
        leaveRequestPage.submitLeaveRequest("1", "SICK", "2023-12-01", "2023-12-05");

        assertTrue(leaveRequestPage.isSuccessMessageDisplayed());
        assertEquals("Leave request submitted successfully", leaveRequestPage.getSuccessMessage());
    }

    @Test
    public void testSubmitLeaveRequest_InvalidEmployeeId_ShowsErrorMessage() {
        leaveRequestPage.open();
        leaveRequestPage.submitLeaveRequest("invalid", "SICK", "2023-12-01", "2023-12-05");

        assertTrue(leaveRequestPage.isErrorMessageDisplayed());
        assertEquals("Invalid employee ID", leaveRequestPage.getErrorMessage());
    }
}
