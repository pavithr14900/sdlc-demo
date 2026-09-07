package com.example.leave.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    // Getters and setters omitted for brevity
}
