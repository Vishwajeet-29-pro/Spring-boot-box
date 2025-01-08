package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.EmployeeResponse;
import com.spring.relationship.one_to_one.dto.ParkingSpotRequest;
import com.spring.relationship.one_to_one.dto.ParkingSpotResponse;
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
        return null;
    }

    @Override
    public ParkingSpotResponse findParkingSpotById(Long id) {
        return null;
    }

    @Override
    public ParkingSpotResponse findParkingSpotBySpotNumber(String spotNumber) {
        return null;
    }

    @Override
    public List<ParkingSpotRequest> findAllParkingSpot() {
        return List.of();
    }

    @Override
    public ParkingSpotResponse updateParkingSpotById(Long id, ParkingSpotRequest parkingSpotRequest) {
        return null;
    }

    @Override
    public void deleteParkingSpot(Long id) {

    }

    @Override
    public Optional<EmployeeResponse> findEmployeeByParkingSpot(Long parkingSpotId) {
        return Optional.empty();
    }
}
