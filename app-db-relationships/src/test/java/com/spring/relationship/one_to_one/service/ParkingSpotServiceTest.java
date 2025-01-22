package com.spring.relationship.one_to_one.service;

import com.spring.relationship.one_to_one.dto.ParkingSpotRequest;
import com.spring.relationship.one_to_one.dto.ParkingSpotResponse;
import com.spring.relationship.one_to_one.entity.Employee;
import com.spring.relationship.one_to_one.entity.ParkingSpot;
import com.spring.relationship.one_to_one.exception.NoSuchParkingSpotExists;
import com.spring.relationship.one_to_one.repository.EmployeeRepository;
import com.spring.relationship.one_to_one.repository.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    void should_return_parking_spot_response_when_fetch_parking_spot_by_id() {
        when(parkingSpotRepository.findById(anyLong())).thenReturn(Optional.of(parkingSpot));

        ParkingSpotResponse parkingSpotResponse = parkingSpotService.findParkingSpotById(1L);

        assertNotNull(parkingSpotResponse);
        assertEquals(parkingSpot.getSpotNumber(), parkingSpotResponse.getSpotNumber());
        assertFalse(parkingSpotResponse.isAssigned());
        assertNull(parkingSpotResponse.getEmployeeId());
    }

    @Test
    void should_throw_NoSuchParkingSpotException_when_parking_spot_not_found_by_id() {
        when(parkingSpotRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchParkingSpotExists.class,
                () -> parkingSpotService.findParkingSpotById(11L));

        assertEquals("Parking spot with id 11 not found", exception.getMessage());
    }

    @Test
    void should_return_parking_spot_response_when_find_by_parking_spot() {
        when(parkingSpotRepository.findBySpotNumber(anyString())).thenReturn(Optional.of(parkingSpot));

        ParkingSpotResponse parkingSpotResponse = parkingSpotService.findParkingSpotBySpotNumber("A1");

        assertNotNull(parkingSpotResponse);
        assertEquals(parkingSpot.getSpotNumber(), parkingSpotResponse.getSpotNumber());
        assertFalse(parkingSpotResponse.isAssigned());
        assertNull(parkingSpotResponse.getEmployeeId());
    }

    @Test
    void should_throw_NoSuchParkingSpotExist_exception_when_not_found_by_spot_number() {
        when(parkingSpotRepository.findBySpotNumber(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchParkingSpotExists.class,
                () -> parkingSpotService.findParkingSpotBySpotNumber("B1"));

        assertEquals("Parking spot with spot number B1 not found", exception.getMessage());
    }

    @Test
    void should_return_list_of_parking_spot_when_find_all_parking_spots() {
        when(parkingSpotRepository.findAll()).thenReturn(List.of(parkingSpot));

        List<ParkingSpotResponse> parkingSpotResponses = parkingSpotService.findAllParkingSpot();

        assertNotNull(parkingSpotResponses);
        assertEquals(1, parkingSpotResponses.size());
        assertEquals(parkingSpot.getSpotNumber(), parkingSpotResponses.getFirst().getSpotNumber());
        assertFalse(parkingSpotResponses.getFirst().isAssigned());
        assertNull(parkingSpotResponses.getFirst().getEmployeeId());
    }

    @Test
    void should_update_parking_spot_when_update_by_id() {
        ParkingSpotRequest parkingSpotRequest = new ParkingSpotRequest("AA1", true, null);
        when(parkingSpotRepository.findById(anyLong())).thenReturn(Optional.of(parkingSpot));
        parkingSpot.setSpotNumber(parkingSpotRequest.getSpotNumber());
        parkingSpot.setAssigned(parkingSpotRequest.isAssigned());
        when(parkingSpotRepository.save(any(ParkingSpot.class))).thenReturn(parkingSpot);

        ParkingSpotResponse parkingSpotResponse = parkingSpotService.updateParkingSpotById(1L, parkingSpotRequest);

        assertNotNull(parkingSpotResponse);
        assertEquals(parkingSpotRequest.getSpotNumber(), parkingSpotResponse.getSpotNumber());
        assertTrue(parkingSpotResponse.isAssigned());
    }
}