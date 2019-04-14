package com.rentacar.restapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.restapi.api.entity.ParkingLots;

public interface ParkingLotsRepository extends JpaRepository<ParkingLots, String> {

}
