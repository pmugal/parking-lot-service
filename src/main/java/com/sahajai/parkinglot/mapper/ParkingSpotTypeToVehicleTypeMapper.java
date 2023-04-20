package com.sahajai.parkinglot.mapper;

import com.sahajai.parkinglot.entity.ParkingSpotType;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;

public class ParkingSpotTypeToVehicleTypeMapper {
	
	public VehicleTypeEnm getVehicleTypeForParkingSpotType(ParkingSpotType parkingSpotType) {
		VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
		
		switch(parkingSpotType) {
			case COMPACT : vehicleType = VehicleTypeEnm.CAR;
				break;
			case LARGE	: vehicleType = VehicleTypeEnm.BUS;
				break;
			case MOTORCYCLE : vehicleType = VehicleTypeEnm.MOTORCYCLE;
				break;
			default : vehicleType = VehicleTypeEnm.MOTORCYCLE;
				break;
		}
		
		return vehicleType;
	}
	
	public ParkingSpotType getParkingSpotTypeForVehicleType(VehicleTypeEnm vehicleType) {
		ParkingSpotType parkingSpotType = ParkingSpotType.MOTORCYCLE;
		
		switch(vehicleType) {
			case CAR : parkingSpotType = ParkingSpotType.COMPACT;
					break;
			
			case BUS : parkingSpotType = ParkingSpotType.LARGE;
					break;
			case MOTORCYCLE : parkingSpotType = ParkingSpotType.MOTORCYCLE;
					break;
			default : parkingSpotType = ParkingSpotType.MOTORCYCLE;
					break;
		}
		
		return parkingSpotType;
	}
}
