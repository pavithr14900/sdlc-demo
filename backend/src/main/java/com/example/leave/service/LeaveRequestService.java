package com.example.leave.service;

import com.example.leave.model.LeaveRequest;
import com.example.leave.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Transactional
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        // Validate leave dates and types
        validateLeaveRequest(leaveRequest);
        return leaveRequestRepository.save(leaveRequest);
    }

    @Transactional(readOnly = true)
    public Optional<LeaveRequest> getLeaveRequestById(UUID id) {
        return leaveRequestRepository.findById(id);
    }

    @Transactional
    public LeaveRequest approveLeaveRequest(UUID id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leaveRequest.setStatus("APPROVED");
        return leaveRequestRepository.save(leaveRequest);
    }

    @Transactional
    public LeaveRequest rejectLeaveRequest(UUID id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leaveRequest.setStatus("REJECTED");
        return leaveRequestRepository.save(leaveRequest);
    }

    private void validateLeaveRequest(LeaveRequest leaveRequest) {
        // Add validation logic here
    }
}
