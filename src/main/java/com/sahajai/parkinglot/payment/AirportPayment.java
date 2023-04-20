package com.sahajai.parkinglot.payment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import com.sahajai.parkinglot.entity.ParkingSpotType;
import com.sahajai.parkinglot.entity.ParkingTicket;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;
import com.sahajai.parkinglot.mapper.ParkingSpotTypeToVehicleTypeMapper;

public class AirportPayment implements PaymentCalculation {
    private Map<VehicleTypeEnm, Double> dollarsPerHour;
    private ParkingSpotTypeToVehicleTypeMapper spotTypeToVehicleTypeMapper = new ParkingSpotTypeToVehicleTypeMapper();
    
    public AirportPayment(Map<VehicleTypeEnm, Double> dollarsPerHour) {
        this.dollarsPerHour = dollarsPerHour;
    }

    @Override
    public double calculatePaymentSum(ParkingTicket parkingTicket) {
        long parkingDurationInHours = getParkingTime(parkingTicket);
        ParkingSpotType parkingSpotType = parkingTicket.getParkingSpot().getParkingSpotType();
        VehicleTypeEnm vehicleType = spotTypeToVehicleTypeMapper.getVehicleTypeForParkingSpotType(parkingSpotType);
        
        double rate = dollarsPerHour.get(vehicleType);
        double totalFee = 0;
        
        switch(vehicleType) {
        	default : 
        	case MOTORCYCLE : 
        		totalFee = getPaymentForMotorCycle(rate, parkingDurationInHours);
        		break;
        	case CAR :
        		totalFee = getPaymentForCar(rate, parkingDurationInHours);
        		break;
        	case BUS : 
        		totalFee = 0;
        		break;
        }
        
        return totalFee;
    }
    
	private double getPaymentForCar(double rate, long parkingDurationInHours) {
    	if(parkingDurationInHours < 12) {
        	return  60;
        } else if(parkingDurationInHours < 24) {
        	return 80;
        } else {
        	return Math.abs(parkingDurationInHours/24) * 100;
        }
	}

	private double getPaymentForMotorCycle(double rate, long parkingDurationInHours) {
		if(parkingDurationInHours < 1) {
			return 0;
		} else if(parkingDurationInHours < 8) {
			return 40;
		} else if(parkingDurationInHours < 24) {
			return 60;
		} else {
			return Math.abs(parkingDurationInHours/24) * 80;
		}
	}

	//returns absolute hrs passed
    private long getParkingTime(ParkingTicket parkingTicket) {
    	Duration duration = Duration.between(LocalDateTime.now(), parkingTicket.getEntryDateTime());
    	return Math.abs(duration.toHours());
    }
}
