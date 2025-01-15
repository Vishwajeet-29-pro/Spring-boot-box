package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeRequest;
import com.spring.relationship.one_to_one.dto.EmployeeResponse;
import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import com.spring.relationship.one_to_one.exception.EmployeeNotFoundException;
import com.spring.relationship.one_to_one.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.save(EmployeeRequest.toEmployee(employeeRequest));
        return EmployeeResponse.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with id: "+id+" not found")
        );
        return EmployeeResponse.toEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeResponse::toEmployeeResponse)
                .toList();
    }

    @Override
    public EmployeeResponse updateEmployeeById(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setEmail(employeeRequest.getEmail());
        return EmployeeResponse.toEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {

    }

    @Override
    public EmployeeResponse assignParkingLot(Long employeeId, String spotNumber) {
        return null;
    }

    @Override
    public EmployeeResponse removeParkingLot(Long employeeId) {
        return null;
    }

    @Override
    public Optional<EmployeeResponse> findEmployeeWithParkingSpot(Long employeeId) {
        return Optional.empty();
    }
}
