package com.example.leave.repository;

import com.example.leave.model.LeaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaveRequestRepositoryTest {

    @InjectMocks
    private LeaveRequestRepository leaveRequestRepository;

    @Mock
    private LeaveRequestRepository mockLeaveRequestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(mockLeaveRequestRepository.findAll()).thenReturn(List.of(new LeaveRequest()));
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        assertNotNull(leaveRequests);
        assertEquals(1, leaveRequests.size());
        verify(mockLeaveRequestRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        LeaveRequest leaveRequest = new LeaveRequest();
        when(mockLeaveRequestRepository.save(leaveRequest)).thenReturn(leaveRequest);
        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        assertNotNull(savedLeaveRequest);
        assertEquals(leaveRequest, savedLeaveRequest);
        verify(mockLeaveRequestRepository, times(1)).save(leaveRequest);
    }
}
