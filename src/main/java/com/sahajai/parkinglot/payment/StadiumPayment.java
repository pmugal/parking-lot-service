package com.sahajai.parkinglot.payment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import com.sahajai.parkinglot.entity.ParkingSpotType;
import com.sahajai.parkinglot.entity.ParkingTicket;
import com.sahajai.parkinglot.entity.VehicleTypeEnm;
import com.sahajai.parkinglot.mapper.ParkingSpotTypeToVehicleTypeMapper;

public class StadiumPayment implements PaymentCalculation {
    private Map<VehicleTypeEnm, Double> dollarsPerHour;
    private ParkingSpotTypeToVehicleTypeMapper spotTypeToVehicleTypeMapper = new ParkingSpotTypeToVehicleTypeMapper();
    
    public StadiumPayment(Map<VehicleTypeEnm, Double> dollarsPerHour) {
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
    	long applicableHrs = 0;
    	if(parkingDurationInHours < 4) {
        	//flat charges till 4hrs
        	return  rate;
        } else if(parkingDurationInHours < 12) {
        	//additional than 4hrs charged at 60
        	applicableHrs = parkingDurationInHours - 4;
        	return 30 + 60;
        } else {
        	//additional than 12hrs charged at 100/hr
        	applicableHrs = parkingDurationInHours - 12;
        	return (rate + 60 + applicableHrs*200);
        }
	}

	private double getPaymentForMotorCycle(double rate, long parkingDurationInHours) {
		long applicableHrs = 0;
    	if(parkingDurationInHours < 4) {
        	//flat charges till 4hrs
        	return  rate;
        } else if(parkingDurationInHours < 12) {
        	//additional than 4hrs charged at 60
        	applicableHrs = parkingDurationInHours - 4;
        	return 40 + 60;
        } else {
        	//additional than 12hrs charged at 100/hr
        	applicableHrs = parkingDurationInHours - 12;
        	return (rate + 60 + applicableHrs*100);
        }
    	
	}

	//returns absolute hrs passed
    public long getParkingTime(ParkingTicket parkingTicket) {
    	Duration duration = Duration.between(LocalDateTime.now(), parkingTicket.getEntryDateTime());
    	return Math.abs(duration.toHours());
    }
}
