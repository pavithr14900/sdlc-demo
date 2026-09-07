package com.example.leavemanagement.service;

import com.example.leavemanagement.dto.LeaveRequestDto;
import com.example.leavemanagement.exception.LeaveRequestNotFoundException;
import com.example.leavemanagement.model.LeaveRequest;
import com.example.leavemanagement.repository.LeaveRequestRepository;
import com.example.leavemanagement.service.impl.LeaveRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LeaveRequestServiceTest {

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitLeaveRequest_Success() {
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId("1");
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 12, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 12, 5));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId("1");

        when(modelMapper.map(leaveRequestDto, LeaveRequest.class)).thenReturn(leaveRequest);
        when(leaveRequestRepository.save(any(LeaveRequest.class))).thenReturn(leaveRequest);
        when(modelMapper.map(leaveRequest, LeaveRequestDto.class)).thenReturn(leaveRequestDto);

        LeaveRequestDto result = leaveRequestService.submitLeaveRequest(leaveRequestDto);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    public void testSubmitLeaveRequest_InvalidEmployeeId_ThrowsException() {
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId("invalid");
        leaveRequestDto.setLeaveType("SICK");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 12, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 12, 5));

        assertThrows(LeaveRequestNotFoundException.class, () -> {
            leaveRequestService.submitLeaveRequest(leaveRequestDto);
        });
    }

    @Test
    public void testSubmitLeaveRequest_InvalidLeaveType_ThrowsException() {
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId("1");
        leaveRequestDto.setLeaveType("INVALID");
        leaveRequestDto.setStartDate(LocalDate.of(2023, 12, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2023, 12, 5));

        assertThrows(IllegalArgumentException.class, () -> {
            leaveRequestService.submitLeaveRequest(leaveRequestDto);
        });
    }
}
