package com.rentacar.restapi.api.service;

import java.util.Set;

import com.rentacar.restapi.api.entity.ParkingLots;

public interface ParkingLotsService {

	ParkingLots findByParkingSpace(String parkingSpace);

	int countPackingSpaceAvailable();

	Set<ParkingLots> findPackingSpaceAvailable();

}
