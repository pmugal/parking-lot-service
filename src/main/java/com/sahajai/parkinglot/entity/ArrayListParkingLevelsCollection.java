package com.sahajai.parkinglot.entity;

import java.util.ArrayList;

public class ArrayListParkingLevelsCollection extends ArrayList<ParkingLevel> implements ParkingLevelsCollection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ParkingLevel getParkingLevel(int parkingLevelId) {
        for (ParkingLevel parkingLevel : this) {
            if (parkingLevel.getId() == parkingLevelId) {
                return parkingLevel;
            }
        }
        return null;
    }
}
