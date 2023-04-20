package com.sahajai.parkinglot.entity;

import com.sahajai.parkinglot.exception.InvalidParkingTicketException;
import com.sahajai.parkinglot.exception.ParkingLotIsFullException;

public interface ParkingLotFacade {
    ParkingTicket parkVehicle(Vehicle vehicle) throws ParkingLotIsFullException;
    Receipt unparkVehicle(Vehicle vehicle, ParkingTicket parkingTicket) throws InvalidParkingTicketException;
}
