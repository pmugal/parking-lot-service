package com.sahajai.parkinglot.policies;

import com.sahajai.parkinglot.entity.ParkingLevel;
import com.sahajai.parkinglot.entity.Vehicle;

public interface LevelAssignmentPolicy {
    public ParkingLevel assignLevel(Iterable<ParkingLevel> levels, Vehicle vehicle);
}
