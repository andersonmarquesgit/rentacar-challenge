package com.rentacar.restapi.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentacar.restapi.api.entity.ParkingPrices;
import com.rentacar.restapi.api.repository.ParkingPricesRepository;
import com.rentacar.restapi.api.service.ParkingPricesService;

@Service
public class ParkingPricesServiceImpl implements ParkingPricesService {

	@Autowired
	private ParkingPricesRepository parkingPricesRepository;

	@Override
	public ParkingPrices findById(String id) {
		return parkingPricesRepository.findOneById(id);
	}
	
	
}
