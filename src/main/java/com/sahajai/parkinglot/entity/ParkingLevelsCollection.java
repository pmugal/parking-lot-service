package com.sahajai.parkinglot.entity;

import java.util.Collection;

public interface ParkingLevelsCollection extends Collection<ParkingLevel> {
    ParkingLevel getParkingLevel(int parkingLevelId);
}