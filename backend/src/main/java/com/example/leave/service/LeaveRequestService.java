package com.example.leave.service;

import com.example.leave.model.LeaveRequest;
import com.example.leave.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public List<LeaveRequest> findAll() {
        return leaveRequestRepository.findAll();
    }

    public Optional<LeaveRequest> findById(Long id) {
        return leaveRequestRepository.findById(id);
    }

    public LeaveRequest save(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public void delete(LeaveRequest leaveRequest) {
        leaveRequestRepository.delete(leaveRequest);
    }
}
