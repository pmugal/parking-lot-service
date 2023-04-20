package com.sahajai.parkinglot.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sahajai.parkinglot.entity.ArrayListParkingLevelsCollection;
import com.sahajai.parkinglot.entity.ParkingLevel;
import com.sahajai.parkinglot.entity.ParkingLevelImpl;
import com.sahajai.parkinglot.entity.ParkingLevelsCollection;
import com.sahajai.parkinglot.entity.ParkingLotTypeEnm;
import com.sahajai.parkinglot.entity.ParkingSpot;
import com.sahajai.parkinglot.entity.ParkingSpotType;

public class ParkingLevelsCollectionFactory {
    private static int NUM_OF_LEVELS = 10;
    private static final int NUM_OF_SPOT_TYPES =
            ParkingSpotType.values().length;
    private static Map<ParkingSpotType, Integer>  NUM_OF_SPOTS_PER_SPOT_TYPE = new HashMap<>();

    public static ParkingLevelsCollection createParkingLevelsCollection(ParkingLotTypeEnm parkingLotType) {
        ParkingLevelsCollection levels =
                new ArrayListParkingLevelsCollection();
        
        switch(parkingLotType) {
        	default: 
        	case PARKING_LOT_MALL : NUM_OF_LEVELS = 1;
        		NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.MOTORCYCLE, 100);
        		NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.COMPACT, 80);
        		NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.LARGE, 40);
        		break;
        	case PARKING_LOT_STADIUM : NUM_OF_LEVELS = 10;
	        	NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.MOTORCYCLE, 100);
	    		NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.COMPACT, 80);
	    		NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.LARGE, 40);
        		break;
        	case PARKING_LOT_AIRPORT: NUM_OF_LEVELS = 10;
	        	NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.MOTORCYCLE, 100);
	    		NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.COMPACT, 80);
	    		NUM_OF_SPOTS_PER_SPOT_TYPE.put(ParkingSpotType.LARGE, 40);
        		break;
        }
        
        int parkingSpotId = 0;
        int parkingLevelId = 0;
        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            Map<ParkingSpotType, Collection<ParkingSpot>> allSpotsForLevel = generateSpots(parkingLevelId, parkingSpotId);
            ParkingLevel parkingLevel = new ParkingLevelImpl(parkingLevelId, allSpotsForLevel);
            levels.add(parkingLevel);
            
            int total_spots = NUM_OF_SPOTS_PER_SPOT_TYPE.values().stream().mapToInt(Integer::intValue).sum();

            parkingSpotId += NUM_OF_SPOT_TYPES * total_spots;
            parkingLevelId++;
        }
        return levels;
    }

    private static Map<ParkingSpotType, Collection<ParkingSpot>> generateSpots(int parkingLevelId, int parkingSpotId) {
        Map<ParkingSpotType, Collection<ParkingSpot>> allSpotsForLevel = new HashMap<ParkingSpotType, Collection<ParkingSpot>>();
        for (ParkingSpotType parkingSpotType : ParkingSpotType.values()) {
            Collection<ParkingSpot> spotsForLevel = new ArrayList<ParkingSpot>();
            for (int i = 0; i < NUM_OF_SPOTS_PER_SPOT_TYPE.get(parkingSpotType); i++) {
                spotsForLevel.add(new ParkingSpot(parkingSpotId,
                        parkingLevelId, parkingSpotType));
                parkingSpotId++;
            }
            allSpotsForLevel.put(parkingSpotType, spotsForLevel);
        }
        return allSpotsForLevel;
    }
}
