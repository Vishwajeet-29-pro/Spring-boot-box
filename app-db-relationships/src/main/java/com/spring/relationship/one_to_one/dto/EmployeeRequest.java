package com.spring.relationship.one_to_one.dto;

import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequest {
    private String employeeName;
    private String email;
    private ParkingSpot parkingSpot;

    public static Employee toEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setParkingSpot(employeeRequest.getParkingSpot());
        return employee;
    }
}
