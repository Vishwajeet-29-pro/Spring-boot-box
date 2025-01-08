package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeRequest;
import com.spring.relationship.one_to_one.dto.EmployeeResponse;
import com.spring.relationship.one_to_one.entity.Employee;
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
        return null;
    }

    @Override
    public List<EmployeeResponse> findAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponse updateEmployeeById(Long id, EmployeeRequest employeeRequest) {
        return null;
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
