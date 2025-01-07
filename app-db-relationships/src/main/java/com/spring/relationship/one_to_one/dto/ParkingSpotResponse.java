package com.spring.relationship.one_to_one.dto;

import com.spring.relationship.one_to_one.entity.ParkingSpot;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpotResponse {
    private Long id;
    private String spotNumber;
    private boolean isAssigned;
    private Long employeeId;

    public static ParkingSpotResponse toParkingSpotResponse(ParkingSpot parkingSpot) {
        Long employeeId = parkingSpot.getEmployee() != null ? parkingSpot.getEmployee().getId() : null;
        return new ParkingSpotResponse(
                parkingSpot.getId(), parkingSpot.getSpotNumber(), parkingSpot.isAssigned(), employeeId
        );
    }
}
