package com.example.leave.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.FutureOrPresent;
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
    @PastOrPresent(message = "Start date must not be in the future")
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent(message = "End date must not be in the past")
    private LocalDate endDate;

    @NotNull
    @Size(min = 1, max = 10)
    private String status;

    @Column(updatable = false)
    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt = LocalDate.now();

    // Getters and setters omitted for brevity
}
