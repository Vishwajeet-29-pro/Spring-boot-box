package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.ParkingSpotRequest;
import com.spring.relationship.one_to_one.dto.ParkingSpotResponse;
import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import com.spring.relationship.one_to_one.repository.EmployeeRepository;
import com.spring.relationship.one_to_one.repository.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingSpotServiceTest {

    private ParkingSpotService parkingSpotService;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    private ParkingSpot parkingSpot;
    private Employee employee;

    @BeforeEach
    void setup() {
        parkingSpotService = new ParkingSpotServiceImpl(parkingSpotRepository);
        parkingSpot = new ParkingSpot(1L, "A1", false, null);
        employee = new Employee(1L, "Vishwajeet", "vishwajeet@springbox.com", null);
    }

    @Test
    void create_parking_spot_should_return_parking_spot_response() {
        ParkingSpotRequest parkingSpotRequest = new ParkingSpotRequest("A1", false, null);
        when(parkingSpotRepository.save(any(ParkingSpot.class))).thenReturn(parkingSpot);

        ParkingSpotResponse parkingSpotResponse = parkingSpotService.createParkingSpot(parkingSpotRequest);

        assertNotNull(parkingSpotResponse);
        assertEquals(parkingSpotRequest.getSpotNumber(), parkingSpotResponse.getSpotNumber());
        assertFalse(parkingSpotResponse.isAssigned());
        assertNull(parkingSpotResponse.getEmployeeId());
    }
}