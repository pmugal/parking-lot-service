package com.sahajai.parkinglot;
import com.sahajai.parkinglot.entity.ParkingLot;
import com.sahajai.parkinglot.entity.ParkingLotFacade;
import com.sahajai.parkinglot.entity.ParkingLotTypeEnm;
import com.sahajai.parkinglot.entity.ParkingTicket;
import com.sahajai.parkinglot.entity.Receipt;
import com.sahajai.parkinglot.entity.Vehicle;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;
import com.sahajai.parkinglot.factory.ParkingLotFactory;
import com.sahajai.parkinglot.users.CarOwner;

public class Main {

    public static void main(String[] args) {
        ParkingLot parkingLotMall = ParkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_MALL);
        handleCarOwner(parkingLotMall);
        
        ParkingLot parkingLotStadium = ParkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_STADIUM);
        handleCarOwner(parkingLotStadium);
        
        ParkingLot parkingLotAirport = ParkingLotFactory.createNewParkingLot(ParkingLotTypeEnm.PARKING_LOT_AIRPORT);
        handleCarOwner(parkingLotAirport);
    }

    static void handleCarOwner(ParkingLotFacade parkingLot) {
        String carOwnerName = "Pritam Mugal";
        int carId = 1;
        VehicleTypeEnm vehicleType = VehicleTypeEnm.MOTORCYCLE;
        String registrationNumber = "MH31FA4983";
        Vehicle vehicle = new Vehicle(carId, vehicleType, registrationNumber);

        CarOwner carOwner = new CarOwner(carOwnerName, vehicle);

        ParkingTicket parkingTicket = carOwner.parkVehicle(parkingLot);
        System.out.println(parkingTicket);
        
        Receipt receipt = carOwner.unparkVehicle(parkingLot);
        System.out.println(receipt);
        
    }
}
