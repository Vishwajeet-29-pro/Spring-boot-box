package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeResponse;
import com.spring.relationship.one_to_one.dto.ParkingSpotRequest;
import com.spring.relationship.one_to_one.dto.ParkingSpotResponse;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import com.spring.relationship.one_to_one.exception.NoSuchParkingSpotExists;
import com.spring.relationship.one_to_one.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpotResponse createParkingSpot(ParkingSpotRequest parkingSpotRequest) {
        ParkingSpot parkingSpot = parkingSpotRepository.save(ParkingSpotRequest.toParkingSpot(parkingSpotRequest));
        return ParkingSpotResponse.toParkingSpotResponse(parkingSpot);
    }

    @Override
    public ParkingSpotResponse findParkingSpotById(Long id) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElseThrow(
                () -> new NoSuchParkingSpotExists("Parking spot with id "+id+" not found")
        );
        return ParkingSpotResponse.toParkingSpotResponse(parkingSpot);
    }

    @Override
    public ParkingSpotResponse findParkingSpotBySpotNumber(String spotNumber) {
        ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNumber(spotNumber).orElseThrow(
                () -> new NoSuchParkingSpotExists("Parking spot with spot number "+spotNumber+" not found")
        );
        return ParkingSpotResponse.toParkingSpotResponse(parkingSpot);
    }

    @Override
    public List<ParkingSpotResponse> findAllParkingSpot() {
        List<ParkingSpot> parkingSpotResponses = parkingSpotRepository.findAll();
        return parkingSpotResponses.stream().map(
                ParkingSpotResponse::toParkingSpotResponse
        ).toList();
    }

    @Override
    public ParkingSpotResponse updateParkingSpotById(Long id, ParkingSpotRequest parkingSpotRequest) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElseThrow();
        parkingSpot.setSpotNumber(parkingSpotRequest.getSpotNumber());
        parkingSpot.setAssigned(parkingSpotRequest.isAssigned());

        ParkingSpot updatedParkingSpot = parkingSpotRepository.save(parkingSpot);
        return ParkingSpotResponse.toParkingSpotResponse(updatedParkingSpot);
    }

    @Override
    public void deleteParkingSpot(Long id) {

    }

    @Override
    public Optional<EmployeeResponse> findEmployeeByParkingSpot(Long parkingSpotId) {
        return Optional.empty();
    }
}
