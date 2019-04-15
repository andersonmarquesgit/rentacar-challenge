package com.rentacar.restapi.api.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentacar.restapi.api.entity.Tickets;
import com.rentacar.restapi.api.repository.TicketsRepository;
import com.rentacar.restapi.api.service.TicketsService;

@Service
public class TicketsServiceImpl implements TicketsService {

	@Autowired
	private TicketsRepository repository;
	
	@Override
	public Tickets createOrUpdate(Tickets tickets) {
		return this.repository.save(tickets);
	}

	@Override
	public BigDecimal calculatePayment(LocalDateTime startDate, LocalDateTime finishDate) {
		return repository.sumTotalValueTickets(startDate, finishDate);
	}
}
