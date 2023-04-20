package com.sahajai.parkinglot.payment;

import static org.junit.Assert.assertEquals;

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

public class TestAirportPayment {

	private ParkingLotFactory parkingLotFactory;
	private ParkingLot parkingLotAirport;
	private ParkingTicket parkingTicket;
	private AirportPayment airportPayment;
	private static Map<VehicleTypeEnm, Double> DOLLARS_PER_HOUR_PER_VEHICLE_TYPE = new HashMap<>();
	
	@Before
	public void setUp() {
		parkingLotAirport = parkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_AIRPORT);
		String carOwnerName = "Pritam Mugal";
        int carId = 1;
        VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
        String registrationNumber = "MH31FA4983";
        Vehicle vehicle = new Vehicle(carId, vehicleType, registrationNumber);

        CarOwner carOwner = new CarOwner(carOwnerName, vehicle);

        parkingTicket = carOwner.parkVehicle(parkingLotAirport);
        parkingTicket.setEntryDateTime(LocalDateTime.now().minusHours(3).minusMinutes(30));
	}
	
	@Test
	public void testCalculatePaymentSum() {
		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.MOTORCYCLE, (double) 40);
		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.CAR, (double) 60);
		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.BUS, (double) 0); //No Bus Parking at Airport
		
		airportPayment = new AirportPayment(DOLLARS_PER_HOUR_PER_VEHICLE_TYPE);
		double totalFee = airportPayment.calculatePaymentSum(parkingTicket);
		
		assertEquals((double)40, totalFee, 0);
	}

}
