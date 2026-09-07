package com.example.leavemanagement;

import com.example.leavemanagement.page.LeaveRequestPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    public void testSubmitLeaveRequest() {
        leaveRequestPage.open();
        leaveRequestPage.submitLeaveRequest("1", "SICK", "2023-10-01", "2023-10-05");

        assertTrue(leaveRequestPage.isSuccessMessageDisplayed());
    }

    @Test
    public void testSubmitLeaveRequest_InvalidEmployeeId() {
        leaveRequestPage.open();
        leaveRequestPage.submitLeaveRequest("999", "SICK", "2023-10-01", "2023-10-05");

        assertTrue(leaveRequestPage.isErrorMessageDisplayed());
    }

    @Test
    public void testSubmitLeaveRequest_StartDateInPast() {
        leaveRequestPage.open();
        leaveRequestPage.submitLeaveRequest("1", "SICK", "2023-09-30", "2023-10-05");

        assertTrue(leaveRequestPage.isErrorMessageDisplayed());
    }
}
