package com.sahajai.parkinglot;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.sahajai.parkinglot.entity.ParkingLot;
import com.sahajai.parkinglot.entity.ParkingLotTypeEnm;
import com.sahajai.parkinglot.entity.Receipt;
import com.sahajai.parkinglot.entity.Vehicle;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;
import com.sahajai.parkinglot.factory.ParkingLotFactory;
import com.sahajai.parkinglot.users.CarOwner;

public class TestCarOwner {

	private ParkingLotFactory parkingLotFactory;
	private ParkingLot parkingLotMall;
	
	@Before
	public void setUp() {
		parkingLotMall = parkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_MALL);
	}
	
	@Test
	public void testParkVehicle() {
		String carOwnerName = "Pritam Mugal";
        int carId = 1;
        VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
        String registrationNumber = "MH31FA4983";
        final Vehicle vehicle = new Vehicle(carId, vehicleType, registrationNumber);
        final CarOwner carOwner = new CarOwner(carOwnerName, vehicle);
        
	    assertNotNull(carOwner.parkVehicle(parkingLotMall).getParkingTicketId());
	}
	
	@Test
	public void testUnParkVehicle() {
		String carOwnerName = "Pritam Mugal";
        int carId = 1;
        VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
        String registrationNumber = "MH31FA4983";
        final Vehicle vehicle = new Vehicle(carId, vehicleType, registrationNumber);
        final CarOwner carOwner = new CarOwner(carOwnerName, vehicle);
        carOwner.parkVehicle(parkingLotMall);
        
	    Receipt receipt = carOwner.unparkVehicle(parkingLotMall);
	    
	    assertNotNull(receipt);
		assertNotNull(receipt.getFee());
	}
}
