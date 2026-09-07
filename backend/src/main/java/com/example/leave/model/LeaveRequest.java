package com.example.leave.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LeaveRequest")
@Data
public class LeaveRequest {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    @NotNull
    @PastOrPresent
    private LocalDate endDate;

    @NotNull
    @Pattern(regexp = "^(Pending|Approved|Rejected)$")
    private String status = "Pending";

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
