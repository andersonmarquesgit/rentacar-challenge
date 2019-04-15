package com.rentacar.restapi.api.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.rentacar.restapi.api.entity.Tickets;

public interface TicketsService {

	Tickets createOrUpdate(Tickets tickets);

	BigDecimal calculatePayment(LocalDateTime localDateTime, LocalDateTime localDateTime2);

}
