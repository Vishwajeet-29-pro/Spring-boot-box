package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeResponse;
import com.spring.relationship.one_to_one.dto.ParkingSpotRequest;
import com.spring.relationship.one_to_one.dto.ParkingSpotResponse;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotService {
    ParkingSpotResponse createParkingSpot(ParkingSpotRequest parkingSpotRequest);
    ParkingSpotResponse findParkingSpotById(Long id);
    ParkingSpotResponse findParkingSpotBySpotNumber(String spotNumber);
    List<ParkingSpotRequest> findAllParkingSpot();
    ParkingSpotResponse updateParkingSpotById(Long id, ParkingSpotRequest parkingSpotRequest);
    void deleteParkingSpot(Long id);
    Optional<EmployeeResponse> findEmployeeByParkingSpot(Long parkingSpotId);

}
