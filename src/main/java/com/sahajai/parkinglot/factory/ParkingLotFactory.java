package com.sahajai.parkinglot.factory;

import java.util.HashMap;
import java.util.Map;

import com.sahajai.parkinglot.entity.ParkingLevelsCollection;
import com.sahajai.parkinglot.entity.ParkingLot;
import com.sahajai.parkinglot.entity.ParkingLotTypeEnm;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;
import com.sahajai.parkinglot.payment.AirportPayment;
import com.sahajai.parkinglot.payment.BasicHourlyPayment;
import com.sahajai.parkinglot.payment.PaymentCalculation;
import com.sahajai.parkinglot.payment.StadiumPayment;
import com.sahajai.parkinglot.policies.BasicLevelAssignmentPolicy;
import com.sahajai.parkinglot.policies.BasicParkingAssignmentPolicy;
import com.sahajai.parkinglot.policies.LevelAssignmentPolicy;
import com.sahajai.parkinglot.policies.ParkingAssignmentPolicy;

public class ParkingLotFactory {
    private static Map<VehicleTypeEnm, Double> DOLLARS_PER_HOUR_PER_VEHICLE_TYPE = new HashMap<>();

    public static ParkingLot createNewParkingLot(ParkingLotTypeEnm parkingLotType) {
        ParkingLevelsCollection parkingLevels =
                ParkingLevelsCollectionFactory.createParkingLevelsCollection(parkingLotType);
        
        LevelAssignmentPolicy levelAssignmentPolicy = new BasicLevelAssignmentPolicy();
        ParkingAssignmentPolicy parkingAssignmentPolicy =
                new BasicParkingAssignmentPolicy();
        
        PaymentCalculation paymentCalculation = new BasicHourlyPayment(DOLLARS_PER_HOUR_PER_VEHICLE_TYPE);
        switch(parkingLotType) {
        	case PARKING_LOT_MALL : 
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.MOTORCYCLE, (double) 10);
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.CAR, (double) 20);
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.BUS, (double) 50);
        		paymentCalculation = new BasicHourlyPayment(DOLLARS_PER_HOUR_PER_VEHICLE_TYPE);
        		break;
        	case PARKING_LOT_STADIUM : 
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.MOTORCYCLE, (double) 30);
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.CAR, (double) 60);
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.BUS, (double) 0); //No Bus Parking at Stadium
        		paymentCalculation = new StadiumPayment(DOLLARS_PER_HOUR_PER_VEHICLE_TYPE);
        		break;
        	case PARKING_LOT_AIRPORT : 
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.MOTORCYCLE, (double) 40);
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.CAR, (double) 60);
        		DOLLARS_PER_HOUR_PER_VEHICLE_TYPE.put(VehicleTypeEnm.BUS, (double) 0); //No Bus Parking at Airport
        		paymentCalculation = new AirportPayment(DOLLARS_PER_HOUR_PER_VEHICLE_TYPE);
        		break;
        }
        return new ParkingLot(parkingLotType, parkingLevels, parkingLevels, levelAssignmentPolicy,
                parkingAssignmentPolicy, paymentCalculation);
        
    }
}
