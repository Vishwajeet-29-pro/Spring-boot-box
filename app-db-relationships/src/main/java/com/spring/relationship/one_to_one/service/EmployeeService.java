package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeRequest;
import com.spring.relationship.one_to_one.dto.EmployeeResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse findEmployeeById(Long id);
    List<EmployeeResponse> findAllEmployees();
    EmployeeResponse updateEmployeeById(Long id, EmployeeRequest employeeRequest);
    void deleteEmployee(Long id);
    EmployeeResponse assignParkingLot(Long employeeId, String spotNumber);
    EmployeeResponse removeParkingLot(Long employeeId);
    Optional<EmployeeResponse> findEmployeeWithParkingSpot(Long employeeId);

}
