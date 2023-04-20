package com.sahajai.parkinglot.entity;

public class Vehicle {

	private int vehicleId;
	private VehicleTypeEnm vehicleType;
	private String registrationNumber;
	private VehicleFuelTypeEnm vehicleFuelType;
	
	public Vehicle() {
	}

	public Vehicle(int vehicleId, VehicleTypeEnm vehicleType, String registrationNumber) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.registrationNumber = registrationNumber;
	}

	public VehicleFuelTypeEnm getVehicleFuelType() {
		return vehicleFuelType;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleTypeEnm getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleTypeEnm vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleType=" + vehicleType + ", registrationNumber="
				+ registrationNumber + "]";
	}
	
}
