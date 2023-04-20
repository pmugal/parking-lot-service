package com.sahajai.parkinglot.entity;

import java.time.LocalDateTime;

public class ParkingTicket {
	private int parkingTicketId;
	private ParkingSpot parkingSpot;
	private LocalDateTime entryDateTime;
	private int vehicleId;
	
	public ParkingTicket(int parkingTicketId, ParkingSpot parkingSpot, LocalDateTime entryDateTime, int vehicleId) {
		super();
		this.parkingTicketId = parkingTicketId;
		this.parkingSpot = parkingSpot;
		this.entryDateTime = entryDateTime;
		this.vehicleId = vehicleId;
	}

	public ParkingTicket() {
	}

	public int getParkingTicketId() {
		return parkingTicketId;
	}

	public void setParkingTicketId(int parkingTicketId) {
		this.parkingTicketId = parkingTicketId;
	}

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public LocalDateTime getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(LocalDateTime entryDateTime) {
		this.entryDateTime = entryDateTime;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "ParkingTicket [parkingTicketId=" + parkingTicketId + ", parkingSpot=" + parkingSpot + ", entryDateTime="
				+ entryDateTime + ", vehicleId=" + vehicleId + "]";
	}
	
}
