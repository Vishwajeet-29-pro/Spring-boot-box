package com.spring.relationship.one_to_one.dto;

import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpotRequest {
    private String spotNumber;
    private boolean isAssigned;
    private Long employeeId;

    public static ParkingSpot toParkingSpot(ParkingSpotRequest request) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSpotNumber(request.getSpotNumber());
        parkingSpot.setAssigned(request.isAssigned());
        return parkingSpot;
    }
}
