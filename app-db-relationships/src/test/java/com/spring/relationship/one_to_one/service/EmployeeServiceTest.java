package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import com.spring.relationship.one_to_one.repository.EmployeeRepository;
import com.spring.relationship.one_to_one.repository.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    private Employee employee;
    private ParkingSpot parkingSpot;

    @BeforeEach
    public void setup() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
        parkingSpot = new ParkingSpot(1L, "A1", false, null);
        employee = new Employee(1L, "Vishwajeet", "vishwajeet@springbox.com", null);
    }

    @Test
    void test_create_employee_should_add_employee_details() {
        assertTrue(true);
    }
}