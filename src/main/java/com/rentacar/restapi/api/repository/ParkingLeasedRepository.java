package com.rentacar.restapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rentacar.restapi.api.entity.ParkingLeased;

public interface ParkingLeasedRepository extends JpaRepository<ParkingLeased, String> {

	ParkingLeased findOneById(String id);

	ParkingLeased findByLicencePlate(String licencePlate);

	@Query("SELECT pl FROM ParkingLeased pl WHERE pl.parkingLots.parkingSpace = :parkingSpace")
	ParkingLeased findByParkingSpace(@Param("parkingSpace") String parkingSpace);
}
