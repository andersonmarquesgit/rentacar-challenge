package com.rentacar.restapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.restapi.api.entity.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, String> {

}
