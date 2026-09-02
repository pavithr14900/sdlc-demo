package com.example.leave.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate startDate;

    @Column(nullable = false)
    @PastOrPresent
    @AssertTrue(message = "End date must be after start date")
    private LocalDate endDate;

    @Column(nullable = false, length = 20)
    @Pattern(regexp = "SICK|VACATION|PERSONAL", message = "Leave type must be one of: SICK, VACATION, PERSONAL")
    private String leaveType;

    @Column(nullable = false, length = 20)
    @Pattern(regexp = "PENDING|APPROVED|REJECTED", message = "Status must be one of: PENDING, APPROVED, REJECTED")
    private String status = "PENDING";

    @Column(nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    // Getters and setters omitted for brevity
}
