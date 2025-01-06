package com.spring.relationship.one_to_one.dto;

import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String employeeName;
    private String email;
    private ParkingSpot parkingSpot;

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(), employee.getEmployeeName(),
                employee.getEmail(), employee.getParkingSpot()
        );
    }
}
