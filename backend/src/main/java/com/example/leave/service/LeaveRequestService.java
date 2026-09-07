package com.example.leave.service;

import com.example.leave.model.Employee;
import com.example.leave.model.LeaveRequest;
import com.example.leave.repository.LeaveRequestRepository;
import com.example.leave.repository.EmployeeRepository;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public LeaveRequest createLeaveRequest(UUID employeeId, LeaveRequest leaveRequest) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            leaveRequest.setEmployee(employee.get());
            return leaveRequestRepository.save(leaveRequest);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    @Transactional(readOnly = true)
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(UUID employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    @Transactional
    public LeaveRequest approveLeaveRequest(UUID id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leaveRequest.setStatus("Approved");
        return leaveRequestRepository.save(leaveRequest);
    }

    @Transactional
    public LeaveRequest rejectLeaveRequest(UUID id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leaveRequest.setStatus("Rejected");
        return leaveRequestRepository.save(leaveRequest);
    }
}
