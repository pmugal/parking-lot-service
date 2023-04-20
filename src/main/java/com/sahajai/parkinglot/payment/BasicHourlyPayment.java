package com.sahajai.parkinglot.payment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import com.sahajai.parkinglot.entity.ParkingSpotType;
import com.sahajai.parkinglot.entity.ParkingTicket;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;
import com.sahajai.parkinglot.mapper.ParkingSpotTypeToVehicleTypeMapper;

public class BasicHourlyPayment implements PaymentCalculation {
    private Map<VehicleTypeEnm, Double> dollarsPerHour;
    private ParkingSpotTypeToVehicleTypeMapper spotTypeToVehicleTypeMapper = new ParkingSpotTypeToVehicleTypeMapper();
    
    public BasicHourlyPayment(Map<VehicleTypeEnm, Double> dollarsPerHour) {
        this.dollarsPerHour = dollarsPerHour;
    }

    @Override
    public double calculatePaymentSum(ParkingTicket parkingTicket) {
        long parkingDurationInHours = getParkingTime(parkingTicket);
        ParkingSpotType parkingSpotType = parkingTicket.getParkingSpot().getParkingSpotType();
        VehicleTypeEnm vehicleType = spotTypeToVehicleTypeMapper.getVehicleTypeForParkingSpotType(parkingSpotType);
        return dollarsPerHour.get(vehicleType) * parkingDurationInHours;
    }
    
    //returns absolute hrs passed
    public long getParkingTime(ParkingTicket parkingTicket) {
    	Duration duration = Duration.between(LocalDateTime.now(), parkingTicket.getEntryDateTime());
    	return Math.abs(duration.toHours());
    }

	
}
