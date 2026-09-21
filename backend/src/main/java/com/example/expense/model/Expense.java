package com.example.expense.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Expense {

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @DecimalMin(value = "0.01")
    @NotNull
    private BigDecimal amount;

    @NotNull
    private String description;

    @NotNull
    private LocalDate date;

    @OneToOne(mappedBy = "expense", cascade = CascadeType.ALL)
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;

    private String comment;

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", employee=" + employee +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", receipt=" + receipt +
                ", manager=" + manager +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }
}

enum ExpenseStatus {
    SUBMITTED, APPROVED, REJECTED
}
