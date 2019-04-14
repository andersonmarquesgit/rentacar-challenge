package com.rentacar.restapi.api.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentacar.restapi.api.entity.ParkingLots;
import com.rentacar.restapi.api.repository.ParkingLotsRepository;
import com.rentacar.restapi.api.service.ParkingLotsService;

@Service
public class ParkingLotsServiceImpl implements ParkingLotsService {

	@Autowired
	private ParkingLotsRepository repository;
	
	@Override
	public ParkingLots findByParkingSpace(String parkingSpace) {
		return repository.findByParkingSpace(parkingSpace);
	}

	@Override
	public int countPackingSpaceAvailable() {
		return repository.countPackingSpaceAvailable();
	}

	@Override
	public Set<ParkingLots> findPackingSpaceAvailable() {
		return repository.findPackingSpaceAvailable();
	}

}
