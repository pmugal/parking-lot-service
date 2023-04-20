package com.sahajai.parkinglot.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sahajai.parkinglot.entity.ParkingLot;
import com.sahajai.parkinglot.entity.ParkingLotTypeEnm;

public class TestParkingLotFactory {

	@Test
	public void testCreateNewParkingLot() {
		ParkingLot parkingLotMall = ParkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_MALL);
		
		assertNotNull(parkingLotMall);
		assertEquals(ParkingLotTypeEnm.PARKING_LOT_MALL, parkingLotMall.getParkingLotType());
	}

	@Test
	public void testCreateNewParkingLotStadium() {
		ParkingLot parkingLotMall = ParkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_STADIUM);
		
		assertNotNull(parkingLotMall);
		assertEquals(ParkingLotTypeEnm.PARKING_LOT_STADIUM, parkingLotMall.getParkingLotType());
	}
	
	@Test
	public void testCreateNewParkingLotAirport() {
		ParkingLot parkingLotMall = ParkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_AIRPORT);
		
		assertNotNull(parkingLotMall);
		assertEquals(ParkingLotTypeEnm.PARKING_LOT_AIRPORT, parkingLotMall.getParkingLotType());
	}
}
