package com.example.leave.controller;

import com.example.leave.model.LeaveRequest;
import com.example.leave.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(
            @RequestParam UUID employeeId,
            @Valid @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(employeeId, leaveRequest);
        return new ResponseEntity<>(createdLeaveRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByEmployeeId(@RequestParam UUID employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestsByEmployeeId(employeeId);
        return ResponseEntity.ok(leaveRequests);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeaveRequest(@PathVariable UUID id) {
        LeaveRequest approvedLeaveRequest = leaveRequestService.approveLeaveRequest(id);
        return ResponseEntity.ok(approvedLeaveRequest);
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeaveRequest(@PathVariable UUID id) {
        LeaveRequest rejectedLeaveRequest = leaveRequestService.rejectLeaveRequest(id);
        return ResponseEntity.ok(rejectedLeaveRequest);
    }
}
