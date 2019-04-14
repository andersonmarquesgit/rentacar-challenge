package com.rentacar.restapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.restapi.api.entity.ParkingPrices;

public interface ParkingPricesRepository extends JpaRepository<ParkingPrices, String> {

	ParkingPrices findOneById(String id);
}
