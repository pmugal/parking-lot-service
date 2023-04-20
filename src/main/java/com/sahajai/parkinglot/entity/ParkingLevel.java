package com.sahajai.parkinglot.entity;

import com.sahajai.parkinglot.exception.ParkingLotIsFullException;
import com.sahajai.parkinglot.policies.ParkingAssignmentPolicy;

public interface ParkingLevel {

    int getId();

    int getNumOfVacantSpotsOfType(ParkingSpotType parkingSpotType);

    int getTotalNumOfVacantSpots();

    ParkingSpot parkVehicle(Vehicle vehicle, ParkingAssignmentPolicy parkingAssignmentPolicy) throws ParkingLotIsFullException;

    public void unparkVehicle(Vehicle vehicle, ParkingTicket parkingTicket);

    void addSpot(ParkingSpot parkingSpot);
    void removeSpot(ParkingSpot parkingSpot);

    void setParkingAssignmentPolicy(ParkingAssignmentPolicy parkingAssignmentPolicy);
}
