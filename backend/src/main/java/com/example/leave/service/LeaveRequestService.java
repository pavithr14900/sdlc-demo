package com.example.leave.service;

import com.example.leave.model.LeaveRequest;
import com.example.leave.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Transactional
    public LeaveRequest save(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public Optional<LeaveRequest> findById(UUID id) {
        return leaveRequestRepository.findById(id);
    }

    public List<LeaveRequest> findByEmployeeId(UUID employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    @Transactional
    public void approve(UUID id) {
        leaveRequestRepository.findById(id)
               .ifPresent(leaveRequest -> {
                    leaveRequest.setStatus("APPROVED");
                    leaveRequestRepository.save(leaveRequest);
                });
    }

    @Transactional
    public void reject(UUID id) {
        leaveRequestRepository.findById(id)
               .ifPresent(leaveRequest -> {
                    leaveRequest.setStatus("REJECTED");
                    leaveRequestRepository.save(leaveRequest);
                });
    }
}
