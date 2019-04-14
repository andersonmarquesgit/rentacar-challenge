package com.rentacar.restapi.api.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentacar.restapi.api.entity.ParkingLots;

public interface ParkingLotsRepository extends JpaRepository<ParkingLots, String> {

	ParkingLots findByParkingSpace(String parkingSpace);

	@Query("SELECT COUNT(p) FROM ParkingLeased pl RIGHT JOIN pl.parkingLots p WHERE pl.parkingLots IS NULL")
	int countPackingSpaceAvailable();

	@Query("SELECT p FROM ParkingLeased pl RIGHT JOIN pl.parkingLots p WHERE pl.parkingLots IS NULL")
	Set<ParkingLots> findPackingSpaceAvailable();

}
