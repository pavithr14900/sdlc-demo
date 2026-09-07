package com.example.leave.service;

import com.example.leave.model.Employee;
import com.example.leave.model.LeaveRequest;
import com.example.leave.repository.LeaveRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaveRequestServiceTest {

    @InjectMocks
    private LeaveRequestService leaveRequestService;

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLeaveRequestHappyPath() {
        LeaveRequest leaveRequest = new LeaveRequest();
        Employee employee = new Employee(1L, "John Doe");
        when(leaveRequestRepository.save(leaveRequest)).thenReturn(leaveRequest);

        LeaveRequest result = leaveRequestService.createLeaveRequest(leaveRequest);

        assertNotNull(result);
        verify(leaveRequestRepository, times(1)).save(leaveRequest);
    }

    @Test
    void testCreateLeaveRequestNullEmployee() {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            leaveRequestService.createLeaveRequest(leaveRequest);
        });

        assertEquals("Employee cannot be null", exception.getMessage());
    }

    @Test
    void testCreateLeaveRequestInvalidDates() {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStartDate("2023-01-02");
        leaveRequest.setEndDate("2023-01-01");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            leaveRequestService.createLeaveRequest(leaveRequest);
        });

        assertEquals("End date must be after start date", exception.getMessage());
    }
}
