package com.sahajai.parkinglot.policies;

import java.util.Map;

import com.sahajai.parkinglot.entity.ParkingSpot;
import com.sahajai.parkinglot.entity.ParkingSpotType;
import com.sahajai.parkinglot.entity.Vehicle;
import com.sahajai.parkinglot.entity.VehicleFuelTypeEnm;
import com.sahajai.parkinglot.exception.ParkingLotIsFullException;
import com.sahajai.parkinglot.mapper.ParkingSpotTypeToVehicleTypeMapper;

public class BasicParkingAssignmentPolicy implements ParkingAssignmentPolicy {
    private ParkingSpotTypeToVehicleTypeMapper vehicleTypeMapper = new ParkingSpotTypeToVehicleTypeMapper();
    
	public ParkingSpot assignSpot(Map<ParkingSpotType, ? extends Iterable<ParkingSpot>> vacantSpots, 
			Vehicle vehicle) throws ParkingLotIsFullException {
		ParkingSpotType parkingSpotTypeForVehicleType = vehicleTypeMapper.getParkingSpotTypeForVehicleType(vehicle.getVehicleType());
		if (!vacantSpots.get(parkingSpotTypeForVehicleType).iterator().hasNext()) {
            throw new ParkingLotIsFullException("No vacant Spot for parking");
        } else {
//        	parkingSpotTypeForVehicleType
        	//match vehicleFuelType with the Spot.IsEvSupported
        	if(VehicleFuelTypeEnm.ELECTRIC == vehicle.getVehicleFuelType()) {
        		//filter out vacantSpots with isEvSupported only
//        		vacantSpots.get(parkingSpotTypeForVehicleType).iterator().
        	}
        	return vacantSpots.get(parkingSpotTypeForVehicleType).iterator().next();
        }
    }
}
