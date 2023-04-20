package com.sahajai.parkinglot.policies;

import com.sahajai.parkinglot.entity.ParkingLevel;
import com.sahajai.parkinglot.entity.Vehicle;

public class BasicLevelAssignmentPolicy implements LevelAssignmentPolicy{

	public ParkingLevel assignLevel(Iterable<ParkingLevel> levels, Vehicle vehicle) {
		return levels.iterator().next();
	}

	
}
