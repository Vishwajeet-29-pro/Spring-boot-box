package com.spring.relationship.one_to_one.repository;

import com.spring.relationship.one_to_one.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findBySpotNumber(String spotNumber);
}
