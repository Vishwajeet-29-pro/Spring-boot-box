package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeRequest;
import com.spring.relationship.one_to_one.dto.EmployeeResponse;
import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import com.spring.relationship.one_to_one.exception.EmployeeNotFoundException;
import com.spring.relationship.one_to_one.repository.EmployeeRepository;
import com.spring.relationship.one_to_one.repository.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
        employeeService = new EmployeeServiceImpl(employeeRepository, parkingSpotRepository);
        parkingSpot = new ParkingSpot(1L, "A1", false, null);
        employee = new Employee(1L, "Vishwajeet", "vishwajeet@springbox.com", null);
    }

    @Test
    void test_create_employee_should_add_employee_details() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Vishwajeet", "vishwajeet@springbox.com", null);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);

        assertNotNull(employeeResponse);
        assertThat(employeeResponse.getEmployeeName()).isEqualTo(employee.getEmployeeName());
        assertThat(employeeResponse.getEmail()).isEqualTo(employee.getEmail());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void test_find_employee_by_id_should_return_employee() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        EmployeeResponse employeeResponse = employeeService.findEmployeeById(1L);

        assertNotNull(employeeResponse);
        assertEquals("Vishwajeet", employeeResponse.getEmployeeName());
        assertEquals("vishwajeet@springbox.com", employeeResponse.getEmail());
    }

    @Test
    void test_find_employee_by_id_not_found_should_throw_EmployeeNotFoundException() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findEmployeeById(22L));

        assertEquals("Employee with id: 22 not found", exception.getMessage());
    }

    @Test
    void test_find_all_employees_should_return_list_of_employee_responses() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<EmployeeResponse> employeeResponses = employeeService.findAllEmployees();

        assertEquals(1, employeeResponses.size());
        assertNotNull(employeeResponses);
        assertEquals("Vishwajeet", employeeResponses.getFirst().getEmployeeName());
        assertEquals("vishwajeet@springbox.com", employeeResponses.getFirst().getEmail());
        assertNull(employeeResponses.getFirst().getSpotNumber());
    }

    @Test
    void test_update_employee_by_should_update_employee_and_return_updated_employee_response() {
        Long id = 1L;
        EmployeeRequest employeeRequest = new EmployeeRequest("Vishwajeet Kotkar", "vishwajeetak@springbox.com", null);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setEmail(employeeRequest.getEmail());
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse employeeResponse = employeeService.updateEmployeeById(id, employeeRequest);
        assertEquals(employeeRequest.getEmployeeName(), employeeResponse.getEmployeeName());
        assertEquals(employeeRequest.getEmail(), employeeResponse.getEmail());
        assertNull(employeeResponse.getSpotNumber());
    }

    @Test
    void test_update_employee_id_not_found_should_throw_EmployeeNotFoundException() {
        Long id = 22L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.updateEmployeeById(id, any(EmployeeRequest.class))
        );

        assertEquals("Employee with id 22 not found", exception.getMessage());
    }

    @Test
    void test_delete_employee_by_id_should_delete_remove_employee() {
        Long id = 1L;
        when(employeeRepository.existsById(id)).thenReturn(true);

        employeeService.deleteEmployee(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }

    @Test
    void test_delete_employee_by_id_not_found_should_throw_exception() {
        Long id = 222L;
        when(employeeRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.deleteEmployee(id)
        );
        assertEquals("Employee with id: 222 not found", exception.getMessage());
    }

    @Test
    void test_assign_parking_spot_to_employee_should_employee_response() {
        parkingSpot.setEmployee(employee);
        parkingSpot.setAssigned(true);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(parkingSpotRepository.findBySpotNumber("A1")).thenReturn(Optional.of(parkingSpot));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse employeeResponse = employeeService.assignParkingLot(1L, "A1");

        assertNotNull(employeeResponse);
        assertEquals(employee.getId(), employeeResponse.getId());
        assertEquals(parkingSpot.getSpotNumber(), employeeResponse.getSpotNumber());
        verify(parkingSpotRepository, times(1)).findBySpotNumber("A1");
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void test_remove_parking_spot_assign_to_employee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse employeeResponse = employeeService.removeParkingLot(1L);
        assertNotNull(employeeResponse);
        assertNull(employeeResponse.getSpotNumber());
    }

    @Test
    void test_find_employee_with_parking_spot() {
        parkingSpot.setEmployee(employee);
        parkingSpot.setAssigned(true);
        employee.setParkingSpot(parkingSpot);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<EmployeeResponse> employeeResponse = employeeService.findEmployeeWithParkingSpot(1L);
        assertNotNull(employeeResponse);
        assertEquals(employee.getEmployeeName(), employeeResponse.get().getEmployeeName());
        assertEquals(employee.getParkingSpot().getSpotNumber(), employeeResponse.get().getSpotNumber());
    }
}
