package com.example.leavemanagement.service;

import com.example.leavemanagement.dto.LeaveRequestDto;
import com.example.leavemanagement.exception.LeaveRequestException;
import com.example.leavemanagement.mapper.LeaveRequestMapper;
import com.example.leavemanagement.model.LeaveRequest;
import com.example.leavemanagement.repository.LeaveRequestRepository;
import com.example.leavemanagement.service.impl.LeaveRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LeaveRequestServiceTest {

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @Mock
    private LeaveRequestMapper leaveRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitLeaveRequest_happyPath() {
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId("1");
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 10, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 10, 5));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId("1");
        leaveRequest.setStatus("PENDING");

        when(leaveRequestMapper.toEntity(leaveRequestDto)).thenReturn(leaveRequest);
        when(leaveRequestRepository.save(any(LeaveRequest.class))).thenReturn(leaveRequest);
        when(leaveRequestMapper.toDto(leaveRequest)).thenReturn(leaveRequestDto);

        LeaveRequestDto result = leaveRequestService.submitLeaveRequest(leaveRequestDto);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("PENDING", result.getStatus());
        verify(leaveRequestRepository, times(1)).save(any(LeaveRequest.class));
    }

    @Test
    void submitLeaveRequest_invalidEmployeeId() {
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId(null);
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 10, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 10, 5));

        assertThrows(LeaveRequestException.class, () -> leaveRequestService.submitLeaveRequest(leaveRequestDto));
    }

    @Test
    void submitLeaveRequest_endDateBeforeStartDate() {
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId("1");
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 10, 5));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 10, 1));

        assertThrows(LeaveRequestException.class, () -> leaveRequestService.submitLeaveRequest(leaveRequestDto));
    }
}
