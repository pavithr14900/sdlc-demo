package com.example.leave.controller;

import com.example.leave.model.LeaveRequest;
import com.example.leave.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.findById(id);
        return leaveRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(@Valid @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest savedLeaveRequest = leaveRequestService.save(leaveRequest);
        return new ResponseEntity<>(savedLeaveRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeaveRequest(@PathVariable Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.findById(id);
        if (leaveRequest.isPresent() && leaveRequest.get().getStatus().equals("Pending")) {
            leaveRequest.get().setStatus("Approved");
            LeaveRequest savedLeaveRequest = leaveRequestService.save(leaveRequest.get());
            return ResponseEntity.ok(savedLeaveRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeaveRequest(@PathVariable Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.findById(id);
        if (leaveRequest.isPresent() && leaveRequest.get().getStatus().equals("Pending")) {
            leaveRequest.get().setStatus("Rejected");
            LeaveRequest savedLeaveRequest = leaveRequestService.save(leaveRequest.get());
            return ResponseEntity.ok(savedLeaveRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
