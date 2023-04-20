package com.sahajai.parkinglot.entity;

import java.time.LocalDateTime;

import com.sahajai.parkinglot.exception.InvalidParkingTicketException;
import com.sahajai.parkinglot.exception.ParkingLotIsFullException;
import com.sahajai.parkinglot.payment.PaymentCalculation;
import com.sahajai.parkinglot.policies.LevelAssignmentPolicy;
import com.sahajai.parkinglot.policies.ParkingAssignmentPolicy;

public class ParkingLot implements ParkingLotFacade { 
	private ParkingLotTypeEnm parkingLotType;
	
	private ParkingLevelsCollection fullLevels;
    private ParkingLevelsCollection nonFullLevels;
    private LevelAssignmentPolicy levelAssignmentPolicy;
    private ParkingAssignmentPolicy parkingAssignmentPolicy;
    private PaymentCalculation paymentCalculation;

    
    public ParkingLot(ParkingLotTypeEnm parkingLotType,
			ParkingLevelsCollection fullLevels, ParkingLevelsCollection nonFullLevels,
			LevelAssignmentPolicy levelAssignmentPolicy, ParkingAssignmentPolicy parkingAssignmentPolicy,
			PaymentCalculation paymentCalculation) {
		super();
		this.parkingLotType = parkingLotType;
		this.fullLevels = fullLevels;
		this.nonFullLevels = nonFullLevels;
		this.levelAssignmentPolicy = levelAssignmentPolicy;
		this.parkingAssignmentPolicy = parkingAssignmentPolicy;
		this.paymentCalculation = paymentCalculation;
	}

    public ParkingTicket parkVehicle(Vehicle vehicle) throws ParkingLotIsFullException {
        if (nonFullLevels.size() == 0) {
            throw new ParkingLotIsFullException("No vacant levels for parking");
        }
        ParkingLevel assignedParkingLevel = levelAssignmentPolicy.assignLevel(nonFullLevels, vehicle);
        ParkingSpot assignedParkingSpot =
                assignedParkingLevel.parkVehicle(vehicle, parkingAssignmentPolicy);

        if (assignedParkingLevel.getTotalNumOfVacantSpots() == 0) {
            nonFullLevels.remove(assignedParkingLevel);
            fullLevels.add(assignedParkingLevel);
        }
        return generateParkingTicket(vehicle, assignedParkingSpot);
    }

    public Receipt unparkVehicle(Vehicle vehicle, ParkingTicket parkingTicket) throws InvalidParkingTicketException {
        if (parkingTicket == null) {
            throw new InvalidParkingTicketException("Invalid parking ticket was passed");
        }
        int parkingLevelID = parkingTicket.getParkingSpot().getParkingLevelID();
        ParkingLevel parkingLevel = getLevel(parkingLevelID);
        if (parkingLevel.getTotalNumOfVacantSpots() == 0) {
            fullLevels.remove(parkingLevel);
            nonFullLevels.add(parkingLevel);
        }
        parkingLevel.unparkVehicle(vehicle, parkingTicket);
        return generateReceipt(parkingTicket, paymentCalculation.calculatePaymentSum(parkingTicket));
    }

    private ParkingTicket generateParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        return new ParkingTicket(1, parkingSpot, LocalDateTime.now(), vehicle.getVehicleId());
    }

    private Receipt generateReceipt(ParkingTicket parkingTicket, double paymentSum) {
        return new Receipt(parkingTicket.getVehicleId(),
                parkingTicket.getParkingSpot(),
                parkingTicket.getEntryDateTime(),
                LocalDateTime.now(),
                paymentSum);
    }


    public void setLevelSelectionPolicy(LevelAssignmentPolicy levelAssignmentPolicy) {
        this.levelAssignmentPolicy = levelAssignmentPolicy;
    }

    public void setPaymentPolicy(PaymentCalculation paymentCalculation) {
        this.paymentCalculation = paymentCalculation;
    }

    public ParkingLevel getLevel(int parkingLevelID) {
        ParkingLevel parkingLevel = fullLevels.getParkingLevel(parkingLevelID);
        if (parkingLevel != null) {
            return parkingLevel;
        }
        return nonFullLevels.getParkingLevel((parkingLevelID));
    }

    public void addLevel(ParkingLevel parkingLevel) {
        nonFullLevels.add(parkingLevel);
    }

    public void removeLevel(ParkingLevel parkingLevel) {
        fullLevels.remove(parkingLevel);
        nonFullLevels.remove(parkingLevel);
    }

    public ParkingLevelsCollection getAllLevels(){
        ParkingLevelsCollection combined= new ArrayListParkingLevelsCollection();
        combined.addAll(nonFullLevels);
        combined.addAll(fullLevels);
        return combined;
    }

	public ParkingLotTypeEnm getParkingLotType() {
		return parkingLotType;
	}

	public void setParkingLotType(ParkingLotTypeEnm parkingLotType) {
		this.parkingLotType = parkingLotType;
	}

	public ParkingLevelsCollection getFullLevels() {
		return fullLevels;
	}

	public void setFullLevels(ParkingLevelsCollection fullLevels) {
		this.fullLevels = fullLevels;
	}

	public ParkingLevelsCollection getNonFullLevels() {
		return nonFullLevels;
	}

	public void setNonFullLevels(ParkingLevelsCollection nonFullLevels) {
		this.nonFullLevels = nonFullLevels;
	}

	public LevelAssignmentPolicy getLevelAssignmentPolicy() {
		return levelAssignmentPolicy;
	}

	public void setLevelAssignmentPolicy(LevelAssignmentPolicy levelAssignmentPolicy) {
		this.levelAssignmentPolicy = levelAssignmentPolicy;
	}

	public ParkingAssignmentPolicy getParkingAssignmentPolicy() {
		return parkingAssignmentPolicy;
	}

	public void setParkingAssignmentPolicy(ParkingAssignmentPolicy parkingAssignmentPolicy) {
		this.parkingAssignmentPolicy = parkingAssignmentPolicy;
	}

	public PaymentCalculation getPaymentCalculation() {
		return paymentCalculation;
	}

	public void setPaymentCalculation(PaymentCalculation paymentCalculation) {
		this.paymentCalculation = paymentCalculation;
	}

	@Override
	public String toString() {
		return "ParkingLot [parkingLotType=" + parkingLotType + ", fullLevels=" + fullLevels
				+ ", nonFullLevels=" + nonFullLevels + ", levelAssignmentPolicy=" + levelAssignmentPolicy
				+ ", parkingAssignmentPolicy=" + parkingAssignmentPolicy + ", paymentCalculation=" + paymentCalculation
				+ "]";
	}
    
}
