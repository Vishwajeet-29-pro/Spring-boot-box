package com.spring.relationship.one_to_one.dto;

import com.spring.relationship.one_to_one.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String employeeName;
    private String email;
    private String spotNumber;

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        String parkingSpotNumber = employee.getParkingSpot() != null ? employee.getParkingSpot().getSpotNumber() : null;
        return new EmployeeResponse(
                employee.getId(), employee.getEmployeeName(),
                employee.getEmail(), parkingSpotNumber
        );
    }
}
