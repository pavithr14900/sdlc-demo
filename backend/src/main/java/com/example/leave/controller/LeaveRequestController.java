package com.example.leave.controller;

import com.example.leave.model.LeaveRequest;
import com.example.leave.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequest);
        return new ResponseEntity<>(createdLeaveRequest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable UUID id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return leaveRequest.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeaveRequest(@PathVariable UUID id) {
        LeaveRequest approvedLeaveRequest = leaveRequestService.approveLeaveRequest(id);
        return new ResponseEntity<>(approvedLeaveRequest, HttpStatus.OK);
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeaveRequest(@PathVariable UUID id) {
        LeaveRequest rejectedLeaveRequest = leaveRequestService.rejectLeaveRequest(id);
        return new ResponseEntity<>(rejectedLeaveRequest, HttpStatus.OK);
    }
}
