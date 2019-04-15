package com.rentacar.restapi.api.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rentacar.restapi.api.entity.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, String> {

	@Query("SELECT SUM(t.totalValue) FROM Tickets t WHERE t.startDate BETWEEN :startDate AND :finishDate")
	BigDecimal sumTotalValueTickets(@Param("startDate") LocalDateTime startDate, @Param("finishDate") LocalDateTime finishDate);

}
