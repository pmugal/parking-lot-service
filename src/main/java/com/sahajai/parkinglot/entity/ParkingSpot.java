package com.sahajai.parkinglot.entity;

public class ParkingSpot {

    private int parkingSpotId;
    private int parkingLevelId;
    private ParkingSpotType parkingSpotType;
    private boolean isEvSupported;
    
    public ParkingSpot() {
    }
    public ParkingSpot(int parkingSpotID, int parkingLevelID, ParkingSpotType parkingSpotType) {
        this.parkingSpotId = parkingSpotID;
        this.parkingLevelId = parkingLevelID;
        this.parkingSpotType = parkingSpotType;
    }

    public int getParkingSpotID() {
        return parkingSpotId;
    }

    public int getParkingLevelID() {
        return parkingLevelId;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }
    
    
	public boolean isEvSupported() {
		return isEvSupported;
	}
	@Override
	public String toString() {
		return "ParkingSpot [parkingSpotId=" + parkingSpotId + ", parkingLevelId=" + parkingLevelId
				+ ", parkingSpotType=" + parkingSpotType + "]";
	}
    
}