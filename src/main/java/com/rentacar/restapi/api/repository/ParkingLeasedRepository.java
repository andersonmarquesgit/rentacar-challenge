package com.rentacar.restapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.restapi.api.entity.ParkingLeased;

public interface ParkingLeasedRepository extends JpaRepository<ParkingLeased, String> {

	ParkingLeased findOneById(String id);

	ParkingLeased findByLicencePlate(String licencePlate);
}
