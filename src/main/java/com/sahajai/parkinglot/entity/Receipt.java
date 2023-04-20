package com.sahajai.parkinglot.entity;

import java.time.LocalDateTime;

public class Receipt {
	private int receiptNumber;
	private ParkingSpot parkingSpot;
	private LocalDateTime entryDateTime;
	private LocalDateTime exitDateTime;
	private double parkingFee;
	private double chargingFee;
	private double totalFee;
	
	public Receipt(int receiptNumber, ParkingSpot parkingSpot, LocalDateTime entryDateTime, LocalDateTime exitDateTime, double parkingFee) {
		super();
		this.receiptNumber = receiptNumber;
		this.parkingSpot = parkingSpot;
		this.entryDateTime = entryDateTime;
		this.exitDateTime = exitDateTime;
		this.parkingFee = parkingFee;
	}

	public int getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(int receiptNumber) {
		this.receiptNumber = receiptNumber;
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

	public LocalDateTime getExitDateTime() {
		return exitDateTime;
	}

	public void setExitDateTime(LocalDateTime exitDateTime) {
		this.exitDateTime = exitDateTime;
	}

	public double getFee() {
		return parkingFee;
	}

	public void setFee(double fee) {
		this.parkingFee = fee;
	}

	@Override
	public String toString() {
		return "Receipt [receiptNumber=" + receiptNumber + ", parkingSpot=" + parkingSpot + ", entryDateTime="
				+ entryDateTime + ", exitDateTime=" + exitDateTime + ", fee=" + parkingFee + "]";
	}
	
}
