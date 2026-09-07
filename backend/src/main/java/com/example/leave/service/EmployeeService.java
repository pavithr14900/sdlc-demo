package com.example.leave.service;

import com.example.leave.model.Employee;
import com.example.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }
}
