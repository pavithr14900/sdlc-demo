package com.example.leave.service;

import com.example.leave.model.LeaveRequest;
import com.example.leave.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id).orElse(null);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest approveLeaveRequest(Long id) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        if (leaveRequest!= null && leaveRequest.getStatus().equals("PENDING")) {
            leaveRequest.setStatus("APPROVED");
            return leaveRequestRepository.save(leaveRequest);
        }
        return null;
    }

    public LeaveRequest rejectLeaveRequest(Long id) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        if (leaveRequest!= null && leaveRequest.getStatus().equals("PENDING")) {
            leaveRequest.setStatus("REJECTED");
            return leaveRequestRepository.save(leaveRequest);
        }
        return null;
    }
}
