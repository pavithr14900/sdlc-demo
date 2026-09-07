package com.example.leave.controller;

import com.example.leave.model.LeaveRequest;
import com.example.leave.service.LeaveRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LeaveRequestController.class)
class LeaveRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaveRequestService leaveRequestService;

    @Test
    void createLeaveRequest() throws Exception {
        LeaveRequest leaveRequest = new LeaveRequest();
        when(leaveRequestService.createLeaveRequest(any(LeaveRequest.class))).thenReturn(leaveRequest);

        mockMvc.perform(post("/leaveRequests")
                       .contentType("application/json")
                       .content("{ \"employeeId\": 1, \"startDate\": \"2023-01-01\", \"endDate\": \"2023-01-02\" }"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.employeeId").value(1));

        verify(leaveRequestService, times(1)).createLeaveRequest(any(LeaveRequest.class));
    }
}
