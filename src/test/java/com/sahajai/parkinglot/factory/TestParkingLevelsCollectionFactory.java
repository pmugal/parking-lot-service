package com.sahajai.parkinglot.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sahajai.parkinglot.entity.ParkingLevelsCollection;
import com.sahajai.parkinglot.entity.ParkingLotTypeEnm;

public class TestParkingLevelsCollectionFactory {

	private ParkingLevelsCollectionFactory parkingLevelsCollectionFactory;
	
	@Test
	public void testCreateParkingLevelsCollection() {
		ParkingLevelsCollection parkingLevelsCollection = parkingLevelsCollectionFactory.createParkingLevelsCollection(ParkingLotTypeEnm.PARKING_LOT_MALL);
		assertNotNull(parkingLevelsCollection);
	}

}
