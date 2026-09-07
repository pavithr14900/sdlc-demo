package com.example.leave.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveRequestTest {

    @Test
    void testLeaveRequestConstructor() {
        LeaveRequest leaveRequest = new LeaveRequest(1L, 1L, "2023-01-01", "2023-01-02");
        assertEquals(1L, leaveRequest.getId());
        assertEquals(1L, leaveRequest.getEmployeeId());
        assertEquals("2023-01-01", leaveRequest.getStartDate());
        assertEquals("2023-01-02", leaveRequest.getEndDate());
    }

    @Test
    void testLeaveRequestGettersAndSetters() {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setEmployeeId(1L);
        leaveRequest.setStartDate("2023-01-01");
        leaveRequest.setEndDate("2023-01-02");
        assertEquals(1L, leaveRequest.getId());
        assertEquals(1L, leaveRequest.getEmployeeId());
        assertEquals("2023-01-01", leaveRequest.getStartDate());
        assertEquals("2023-01-02", leaveRequest.getEndDate());
    }

    @Test
    void testLeaveRequestEqualsAndHashCode() {
        LeaveRequest leaveRequest1 = new LeaveRequest(1L, 1L, "2023-01-01", "2023-01-02");
        LeaveRequest leaveRequest2 = new LeaveRequest(1L, 1L, "2023-01-01", "2023-01-02");
        assertEquals(leaveRequest1, leaveRequest2);
        assertEquals(leaveRequest1.hashCode(), leaveRequest2.hashCode());
    }
}
