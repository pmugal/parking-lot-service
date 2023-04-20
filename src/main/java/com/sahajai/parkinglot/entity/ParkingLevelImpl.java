package com.sahajai.parkinglot.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sahajai.parkinglot.exception.ParkingLotIsFullException;
import com.sahajai.parkinglot.policies.ParkingAssignmentPolicy;

public class ParkingLevelImpl implements ParkingLevel {

    private int id;
    private Map<ParkingSpotType, Collection<ParkingSpot>> vacantSpots;
    private Map<ParkingSpotType, Collection<ParkingSpot>> takenSpots;
    private int totalNumOfVacantSpots;
    private ParkingAssignmentPolicy parkingAssignmentPolicy;

    public ParkingLevelImpl(int id, Map<ParkingSpotType, Collection<ParkingSpot>> allSpotsForLevel) {
        this.id = id;
        this.vacantSpots = allSpotsForLevel;
        this.takenSpots = new HashMap<ParkingSpotType, Collection<ParkingSpot>>();
        takenSpots.put(ParkingSpotType.MOTORCYCLE, new ArrayList<>());
        takenSpots.put(ParkingSpotType.COMPACT, new ArrayList<>());
        takenSpots.put(ParkingSpotType.LARGE, new ArrayList<>());
        this.totalNumOfVacantSpots =
                allSpotsForLevel.values().stream().
                        mapToInt(Collection::size).sum();
    }

    public int getId() {
        return id;
    }

    public Map<ParkingSpotType, Collection<ParkingSpot>> getVacantSpots() {
		return vacantSpots;
	}

	public void setVacantSpots(Map<ParkingSpotType, Collection<ParkingSpot>> vacantSpots) {
		this.vacantSpots = vacantSpots;
	}

	public Map<ParkingSpotType, Collection<ParkingSpot>> getTakenSpots() {
		return takenSpots;
	}

	public void setTakenSpots(Map<ParkingSpotType, Collection<ParkingSpot>> takenSpots) {
		this.takenSpots = takenSpots;
	}

	public ParkingAssignmentPolicy getParkingAssignmentPolicy() {
		return parkingAssignmentPolicy;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTotalNumOfVacantSpots(int totalNumOfVacantSpots) {
		this.totalNumOfVacantSpots = totalNumOfVacantSpots;
	}

	@Override
    public int getNumOfVacantSpotsOfType(ParkingSpotType parkingSpotType) {
        return vacantSpots.get(parkingSpotType).size();
    }

    @Override
    public int getTotalNumOfVacantSpots() {
        return totalNumOfVacantSpots;
    }


    @Override
    public void addSpot(ParkingSpot parkingSpot) {
        vacantSpots.get(parkingSpot.getParkingSpotType()).add(parkingSpot);
        totalNumOfVacantSpots++;
    }

    @Override
    public void removeSpot(ParkingSpot parkingSpot) {
        Collection<ParkingSpot> vacantSpotsOfType =
                vacantSpots.get(parkingSpot.getParkingSpotType());
        if (vacantSpotsOfType.contains(parkingSpot)) {
            vacantSpotsOfType.remove(parkingSpot);
            totalNumOfVacantSpots--;
            return;
        }
        takenSpots.get(parkingSpot.getParkingSpotType()).remove(parkingSpot);
    }

    @Override
    public void setParkingAssignmentPolicy(ParkingAssignmentPolicy parkingAssignmentPolicy) {
        this.parkingAssignmentPolicy = parkingAssignmentPolicy;
    }

    @Override
    public ParkingSpot parkVehicle
            (Vehicle vehicle, ParkingAssignmentPolicy parkingAssignmentPolicy) throws ParkingLotIsFullException {
    	
    	
        ParkingSpot assignedParkingSpot =
                parkingAssignmentPolicy.assignSpot(vacantSpots, vehicle);
        ParkingSpotType parkingSpotType =
                assignedParkingSpot.getParkingSpotType();
        vacantSpots.get(parkingSpotType).remove(assignedParkingSpot);
        takenSpots.get(parkingSpotType).add(assignedParkingSpot);
        totalNumOfVacantSpots--;
        return assignedParkingSpot;
    }

    @Override
    public void unparkVehicle(Vehicle vehicle, ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
        ParkingSpotType parkingSpotType =
        		parkingTicket.getParkingSpot().getParkingSpotType();
        takenSpots.get(parkingSpotType).remove(parkingSpot);
        vacantSpots.get(parkingSpotType).add(parkingSpot);
        totalNumOfVacantSpots++;
    }

	@Override
	public String toString() {
		return "ParkingLevelImpl [id=" + id + ", vacantSpots=" + vacantSpots + ", takenSpots=" + takenSpots
				+ ", totalNumOfVacantSpots=" + totalNumOfVacantSpots + ", parkingAssignmentPolicy="
				+ parkingAssignmentPolicy + "]";
	}
    
}
