package com.sahajai.parkinglot.payment;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sahajai.parkinglot.entity.ParkingLot;
import com.sahajai.parkinglot.entity.ParkingLotTypeEnm;
import com.sahajai.parkinglot.entity.ParkingTicket;
import com.sahajai.parkinglot.entity.Vehicle;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;
import com.sahajai.parkinglot.factory.ParkingLotFactory;
import com.sahajai.parkinglot.users.CarOwner;

public class TestStadiumPayment {

	private ParkingLotFactory parkingLotFactory;
	private ParkingLot parkingLotStadium;
	private ParkingTicket parkingTicket;
	private StadiumPayment stadiumPayment;
	private static Map<VehicleTypeEnm, Double> DOLLARS_PER_HOUR_PER_VEHICLE_TYPE = new HashMap<>();
	
	@Before
	public void setUp() {
		parkingLotStadium = parkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_STADIUM);
		String carOwnerName = "Pritam Mugal";
        int carId = 1;
        VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
        String registrationNumber = "MH31FA4983";
        Vehicle vehicle = new Vehicle(carId, vehicleType, registrationNumber);

        CarOwner carOwner = new CarOwner(carOwnerName, vehicle);

        parkingTicket = carOwner.parkVehicle(parkingLotStadium);
        parkingTicket.setEntryDateTime(LocalDateTime.now().minusHours(3).minusMinutes(40));
	}
	

	@Test
	public void testCalculatePaymentSum() {
		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.MOTORCYCLE, (double) 30);
		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.CAR, (double) 60);
		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.BUS, (double) 0); //No Bus Parking at Stadium
		
		stadiumPayment = new StadiumPayment(DOLLARS_PER_HOUR_PER_VEHICLE_TYPE);
		double totalFee = stadiumPayment.calculatePaymentSum(parkingTicket);
		
		assertEquals((double)30, totalFee, 0);
	}
}
