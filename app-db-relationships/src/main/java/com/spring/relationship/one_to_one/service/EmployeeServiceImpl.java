package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeRequest;
import com.spring.relationship.one_to_one.dto.EmployeeResponse;
import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import com.spring.relationship.one_to_one.exception.EmployeeNotFoundException;
import com.spring.relationship.one_to_one.exception.NoSuchParkingSpotExists;
import com.spring.relationship.one_to_one.repository.EmployeeRepository;
import com.spring.relationship.one_to_one.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.save(EmployeeRequest.toEmployee(employeeRequest));
        return EmployeeResponse.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with id: " + id + " not found")
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
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setEmail(employeeRequest.getEmail());
        return EmployeeResponse.toEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with id: "+id+" not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponse assignParkingLot(Long employeeId, String spotNumber) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with id: " + employeeId + " not found")
        );
        ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNumber(spotNumber).orElseThrow(
                () -> new NoSuchParkingSpotExists("Parking spot with Spot number: "+spotNumber+" not found")
        );
        parkingSpot.setEmployee(employee);
        parkingSpot.setAssigned(true);
        parkingSpotRepository.save(parkingSpot);

        employee.setParkingSpot(parkingSpot);
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeResponse.toEmployeeResponse(updatedEmployee);
    }

    @Override
    public EmployeeResponse removeParkingLot(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with id: "+employeeId+" not found")
        );
        employee.setParkingSpot(null);
        Employee removedParkingSpot = employeeRepository.save(employee);
        return EmployeeResponse.toEmployeeResponse(removedParkingSpot);
    }

    @Override
    public Optional<EmployeeResponse> findEmployeeWithParkingSpot(Long employeeId) {
        return Optional.empty();
    }
}
