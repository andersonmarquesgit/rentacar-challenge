package com.rentacar.restapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.restapi.api.entity.ParkingLots;

public interface ParkingPricesRepository extends JpaRepository<ParkingLots, String> {

}
