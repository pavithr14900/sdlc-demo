package com.example.leave.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    @Size(min = 1, max = 20)
    private String leaveType;

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @Size(min = 1, max = 20)
    private String status = "PENDING";

    @Column(updatable = false)
    private LocalDate createdAt;

    private LocalDate updatedAt;

    // Getters and setters omitted for brevity
}
