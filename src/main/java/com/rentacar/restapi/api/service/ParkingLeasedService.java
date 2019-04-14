package com.rentacar.restapi.api.service;

import org.springframework.data.domain.Page;

import com.rentacar.restapi.api.entity.ParkingLeased;

public interface ParkingLeasedService {

	ParkingLeased createOrUpdate(ParkingLeased parkingLeased);
	
	ParkingLeased findById(String id);
	
	void delete(String id);
	
	Page<ParkingLeased> findAll(int page, int count);

	ParkingLeased findByLicencePlate(String licencePlate);

	ParkingLeased findByParkingSpace(String parkingSpace);
}
