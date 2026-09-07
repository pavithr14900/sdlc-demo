package com.example.leave.service;

import com.example.leave.model.LeaveRequest;
import com.example.leave.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest save(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> findAll() {
        return leaveRequestRepository.findAll();
    }

    public Optional<LeaveRequest> findById(UUID id) {
        return leaveRequestRepository.findById(id);
    }

    public void approve(UUID id) {
        leaveRequestRepository.findById(id)
               .ifPresent(leaveRequest -> {
                    leaveRequest.setStatus("APPROVED");
                    leaveRequestRepository.save(leaveRequest);
                });
    }

    public void reject(UUID id) {
        leaveRequestRepository.findById(id)
               .ifPresent(leaveRequest -> {
                    leaveRequest.setStatus("REJECTED");
                    leaveRequestRepository.save(leaveRequest);
                });
    }
}
