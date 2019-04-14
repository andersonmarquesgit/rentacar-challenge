package com.rentacar.restapi.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rentacar.restapi.api.entity.ParkingLeased;
import com.rentacar.restapi.api.repository.ParkingLeasedRepository;
import com.rentacar.restapi.api.service.ParkingLeasedService;

@Service
public class ParkingLeasedServiceImpl implements ParkingLeasedService{

	@Autowired
	private ParkingLeasedRepository repository;
	
	@Override
	public ParkingLeased createOrUpdate(ParkingLeased parkingLeased) {
		return repository.save(parkingLeased);
	}

	@Override
	public ParkingLeased findById(String id) {
		return repository.findOneById(id);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);		
	}

	@Override
	public Page<ParkingLeased> findAll(int page, int count) {
		return repository.findAll(PageRequest.of(page, count));
	}

	@Override
	public ParkingLeased findByLicencePlate(String licencePlate) {
		return repository.findByLicencePlate(licencePlate);
	}

	@Override
	public ParkingLeased findByParkingSpace(String parkingSpace) {
		return repository.findByParkingSpace(parkingSpace);
	}

}
