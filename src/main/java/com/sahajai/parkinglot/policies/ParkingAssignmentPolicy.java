package com.sahajai.parkinglot.policies;

import java.util.Map;

import com.sahajai.parkinglot.entity.ParkingSpot;
import com.sahajai.parkinglot.entity.ParkingSpotType;
import com.sahajai.parkinglot.entity.Vehicle;
import com.sahajai.parkinglot.exception.ParkingLotIsFullException;

public interface ParkingAssignmentPolicy {
    ParkingSpot assignSpot(Map<ParkingSpotType,
            ? extends Iterable<ParkingSpot>> vacantSpots, Vehicle vehicle) throws ParkingLotIsFullException;
}
