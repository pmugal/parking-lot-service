package com.sahajai.parkinglot.users;

import java.util.concurrent.TimeUnit;

import com.sahajai.parkinglot.entity.ParkingLotFacade;
import com.sahajai.parkinglot.entity.ParkingTicket;
import com.sahajai.parkinglot.entity.Receipt;
import com.sahajai.parkinglot.entity.Vehicle;
import com.sahajai.parkinglot.exception.InvalidParkingTicketException;
import com.sahajai.parkinglot.exception.ParkingLotIsFullException;

public class CarOwner {
    String name;
    Vehicle vehicle;
    ParkingTicket parkingTicket;
    Receipt receipt;

    public CarOwner(String name, Vehicle vehicle) {
        this.name = name;
        this.vehicle = vehicle;
        this.parkingTicket = null;
        this.receipt = null;
    }

    public ParkingTicket parkVehicle(ParkingLotFacade parkingLot) {
        int numOfTries = 5;
        while (numOfTries > 0) {
            try {
                this.parkingTicket = parkingLot.parkVehicle(vehicle);
                break;
            } catch (ParkingLotIsFullException parkingLotIsFullException) {
                handleFullParkingLotException(parkingLotIsFullException);
            }
            numOfTries--;
        }
        return this.parkingTicket;
    }

    private void handleFullParkingLotException(ParkingLotIsFullException parkingLotIsFullException) {
        parkingLotIsFullException.printStackTrace();
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Receipt unparkVehicle(ParkingLotFacade parkingLot) {
    	Receipt receipt = null;
    	try {
            receipt = parkingLot.unparkVehicle(this.vehicle, this.parkingTicket);
        }
        catch (InvalidParkingTicketException e) {
            e.printStackTrace();
        }
        return receipt;
    }


}
