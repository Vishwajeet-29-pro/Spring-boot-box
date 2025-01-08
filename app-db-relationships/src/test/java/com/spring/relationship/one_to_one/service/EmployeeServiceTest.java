package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeRequest;
import com.spring.relationship.one_to_one.dto.EmployeeResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        EmployeeRequest employeeRequest = new EmployeeRequest("Vishwajeet", "vishwajeet@springbox.com", null);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);

        assertNotNull(employeeRequest);
        assertThat(employeeResponse.getEmployeeName()).isEqualTo(employee.getEmployeeName());
        assertThat(employeeResponse.getEmail()).isEqualTo(employee.getEmail());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
}