package com.rentacar.restapi.api.service;

import com.rentacar.restapi.api.entity.ParkingLots;

public interface ParkingLotsService {

	ParkingLots findByParkingSpace(String parkingSpace);

}
