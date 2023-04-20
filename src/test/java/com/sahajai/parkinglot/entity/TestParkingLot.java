package com.sahajai.parkinglot.entity;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.sahajai.parkinglot.exception.InvalidParkingTicketException;
import com.sahajai.parkinglot.exception.ParkingLotIsFullException;
import com.sahajai.parkinglot.factory.ParkingLotFactory;

public class TestParkingLot {
	private ParkingLot parkingLot;
	private Vehicle vehicle;
	private ParkingTicket parkingTicket;
	
	@Before
	public void setUp() {
		parkingLot = ParkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_MALL);
		
        int carId = 1;
        VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
        String registrationNumber = "MH31FA4983";
        vehicle = new Vehicle(carId, vehicleType, registrationNumber);
	}
	
	@Test
	public void testParkVehicle() throws ParkingLotIsFullException {
		parkingTicket = parkingLot.parkVehicle(vehicle);
		
		assertNotNull(parkingTicket);
		assertNotNull(parkingTicket.getParkingSpot());
	}

	@Test
	public void testUnparkVehicle() throws InvalidParkingTicketException, ParkingLotIsFullException {
		parkingTicket = parkingLot.parkVehicle(vehicle);
		Receipt receipt = parkingLot.unparkVehicle(vehicle, parkingTicket);
		receipt.setExitDateTime(LocalDateTime.now().plusHours(3).plusMinutes(30));
		
		assertNotNull(receipt);
		assertNotNull(receipt.getFee());
	}
	
	@Test(expected = ParkingLotIsFullException.class)
	public void testParkingLotIsFullException() throws ParkingLotIsFullException {
		
		for(int i = 0; i < 101; i++) {
			int carId = i;
	        VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
	        String registrationNumber = "MH31FA498"+i;
	        Vehicle vehicle = new Vehicle(carId, vehicleType, registrationNumber);
	        parkingLot.parkVehicle(vehicle);
		}
		
	}
	
	@Test(expected = InvalidParkingTicketException.class)
	public void testInvalidParkingTicketException() throws InvalidParkingTicketException {
		parkingLot.unparkVehicle(vehicle, null);
	}
}
