package com.example.leavemanagement.service;

import com.example.leavemanagement.dto.LeaveRequestDto;
import com.example.leavemanagement.entity.Employee;
import com.example.leavemanagement.entity.LeaveRequest;
import com.example.leavemanagement.exception.LeaveRequestException;
import com.example.leavemanagement.repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitLeaveRequest_HappyPath() {
        Employee employee = new Employee();
        employee.setId(1L);

        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId(1L);
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 10, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 10, 5));

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(leaveRequestRepository.save(any(LeaveRequest.class))).thenReturn(new LeaveRequest());

        LeaveRequestDto result = leaveRequestService.submitLeaveRequest(leaveRequestDto);

        assertNotNull(result);
        assertEquals(1L, result.getEmployeeId());
        verify(leaveRequestRepository, times(1)).save(any(LeaveRequest.class));
    }

    @Test
    void submitLeaveRequest_InvalidEmployeeId() {
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId(999L);
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 10, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 10, 5));

        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(LeaveRequestException.class, () -> leaveRequestService.submitLeaveRequest(leaveRequestDto));
        verify(leaveRequestRepository, never()).save(any(LeaveRequest.class));
    }

    @Test
    void submitLeaveRequest_StartDateInPast() {
        Employee employee = new Employee();
        employee.setId(1L);

        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId(1L);
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 9, 30));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 10, 5));

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(LeaveRequestException.class, () -> leaveRequestService.submitLeaveRequest(leaveRequestDto));
        verify(leaveRequestRepository, never()).save(any(LeaveRequest.class));
    }
}
