package com.example.leavemanagement;

import com.example.leavemanagement.pages.LeaveRequestPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LeaveRequestE2ETest {

    private WebDriver driver;
    private LeaveRequestPage leaveRequestPage;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        leaveRequestPage = new LeaveRequestPage(driver);
    }

    @Test
    void submitLeaveRequest_happyPath() {
        leaveRequestPage.open();
        leaveRequestPage.fillLeaveRequestForm("1", "SICK", "2023-10-01", "2023-10-05");
        leaveRequestPage.submitLeaveRequest();

        assertEquals("Leave request submitted successfully", leaveRequestPage.getSuccessMessage());
    }

    @Test
    void submitLeaveRequest_invalidEmployeeId() {
        leaveRequestPage.open();
        leaveRequestPage.fillLeaveRequestForm(null, "SICK", "2023-10-01", "2023-10-05");
        leaveRequestPage.submitLeaveRequest();

        assertEquals("Employee ID is required", leaveRequestPage.getValidationErrorMessage());
    }

    @Test
    void submitLeaveRequest_endDateBeforeStartDate() {
        leaveRequestPage.open();
        leaveRequestPage.fillLeaveRequestForm("1", "SICK", "2023-10-05", "2023-10-01");
        leaveRequestPage.submitLeaveRequest();

        assertEquals("End date must be after start date", leaveRequestPage.getValidationErrorMessage());
    }
}
