package com.rentacar.restapi.api.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.restapi.api.entity.ParkingLeased;
import com.rentacar.restapi.api.entity.ParkingPrices;
import com.rentacar.restapi.api.entity.Tickets;
import com.rentacar.restapi.api.enums.ParkingPricesEnum;
import com.rentacar.restapi.api.request.ParkingLeasedRequest;
import com.rentacar.restapi.api.request.ParkingSpaceRequest;
import com.rentacar.restapi.api.response.Response;
import com.rentacar.restapi.api.service.ParkingLeasedService;
import com.rentacar.restapi.api.service.ParkingPricesService;
import com.rentacar.restapi.api.service.TicketsService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")//Permitindo o acesso de qualquer IP, porta, etc.
@Api(value = "Tickets")
public class TicketController {

	@Autowired
	private ParkingLeasedService parkingLeasedService;
	
	@Autowired
	private TicketsService ticketsSevice;
	
	@Autowired
	private ParkingPricesService parkingPricesService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('TECHNICIAN')")
	public ResponseEntity<?> create(HttpServletRequest request, @RequestBody ParkingSpaceRequest parkingSpaceRequest, BindingResult result) {
		Response<Tickets> response = new Response<Tickets>();
		
		try {
			validateTicketPayment(parkingSpaceRequest.getParkingSpace(), result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			} 
			
			Tickets ticket = new Tickets();
			ParkingLeased parkingLeased = this.parkingLeasedService.findByParkingSpace(parkingSpaceRequest.getParkingSpace());
			ticket.setStartDate(parkingLeased.getStartDate());
			ticket.setFinishDate(LocalDateTime.now());
			ticket.setLicencePlate(parkingLeased.getLicencePlate());
			ticket.setParkingSpace(parkingSpaceRequest.getParkingSpace());
			this.calcTicketPayment(ticket);
			Tickets ticketsPersisted = this.ticketsSevice.createOrUpdate(ticket);
			this.parkingLeasedService.delete(parkingLeased.getId());
			response.setData(ticketsPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	private void calcTicketPayment(Tickets ticket) {
		Duration duration = Duration.between(ticket.getStartDate(), ticket.getFinishDate());
		long hours = duration.toHours();
		
		ParkingPrices priceUpTo3Hours = parkingPricesService.findById(ParkingPricesEnum.UP_TO_3_HOURS.getId());
		ParkingPrices priceExtraHour = parkingPricesService.findById(ParkingPricesEnum.EXTRA_HOUR.getId());
		
		if(hours <= 3) {
			ticket.setTotalValue(priceUpTo3Hours.getPrice());
		}else {
			ticket.setTotalValue((hours - 3)*priceExtraHour.getPrice() + priceUpTo3Hours.getPrice());
		}
		ticket.setTotalTimeHours(hours);
	}

	private void validateTicketPayment(String parkingSpace, BindingResult result) {
		if(parkingSpace == null) {
			result.addError(new ObjectError("parkingSpace", "Parking Space no information"));
		}else if(this.parkingLeasedService.findByParkingSpace(parkingSpace) == null) {
			result.addError(new ObjectError("parkingSpace", "Parking Space is not leased"));
		}
	}
	
}
