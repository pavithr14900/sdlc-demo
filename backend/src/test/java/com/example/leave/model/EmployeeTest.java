package com.example.leave.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeConstructor() {
        Employee employee = new Employee(1L, "John Doe");
        assertEquals(1L, employee.getId());
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void testEmployeeGettersAndSetters() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        assertEquals(1L, employee.getId());
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void testEmployeeEqualsAndHashCode() {
        Employee employee1 = new Employee(1L, "John Doe");
        Employee employee2 = new Employee(1L, "John Doe");
        assertEquals(employee1, employee2);
        assertEquals(employee1.hashCode(), employee2.hashCode());
    }
}
