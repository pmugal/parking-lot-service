package com.sahajai.parkinglot.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sahajai.parkinglot.entity.ParkingSpotType;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;

public class TestParkingSpotTypeToVehicleTypeMapper {

	private ParkingSpotTypeToVehicleTypeMapper parkingSpotTypeToVehicleTypeMapper = new ParkingSpotTypeToVehicleTypeMapper();
	
	@Test
	public void testGetVehicleTypeForParkingSpotType() {
		VehicleTypeEnm vehicleType = parkingSpotTypeToVehicleTypeMapper.getVehicleTypeForParkingSpotType(ParkingSpotType.MOTORCYCLE);
		assertEquals(VehicleTypeEnm.MOTORCYCLE, vehicleType);
		
		vehicleType = parkingSpotTypeToVehicleTypeMapper.getVehicleTypeForParkingSpotType(ParkingSpotType.COMPACT);
		assertEquals(VehicleTypeEnm.CAR, vehicleType);
		
		vehicleType = parkingSpotTypeToVehicleTypeMapper.getVehicleTypeForParkingSpotType(ParkingSpotType.LARGE);
		assertEquals(VehicleTypeEnm.BUS, vehicleType);
	}

	@Test
	public void testGetParkingSpotTypeForVehicleType() {
		ParkingSpotType parkingSpotType = parkingSpotTypeToVehicleTypeMapper.getParkingSpotTypeForVehicleType(VehicleTypeEnm.MOTORCYCLE);
		assertEquals(ParkingSpotType.MOTORCYCLE, parkingSpotType);
		
		parkingSpotType = parkingSpotTypeToVehicleTypeMapper.getParkingSpotTypeForVehicleType(VehicleTypeEnm.CAR);
		assertEquals(ParkingSpotType.COMPACT, parkingSpotType);
		
		parkingSpotType = parkingSpotTypeToVehicleTypeMapper.getParkingSpotTypeForVehicleType(VehicleTypeEnm.BUS);
		assertEquals(ParkingSpotType.LARGE, parkingSpotType);
		
	}
}
