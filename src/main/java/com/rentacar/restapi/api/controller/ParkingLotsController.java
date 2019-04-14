package com.rentacar.restapi.api.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.restapi.api.entity.ParkingLeased;
import com.rentacar.restapi.api.request.ParkingLeasedRequest;
import com.rentacar.restapi.api.response.Response;
import com.rentacar.restapi.api.service.ParkingLeasedService;
import com.rentacar.restapi.api.service.ParkingLotsService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/parkingLots")
@CrossOrigin(origins = "*")//Permitindo o acesso de qualquer IP, porta, etc.
@Api(value = "ParkingLots")
public class ParkingLotsController {

	@Autowired
	private ParkingLeasedService service;
	
	@Autowired
	private ParkingLotsService parkingLotsService;
	
	@GetMapping(value = "/available")
	@PreAuthorize("hasAnyRole('TECHNICIAN')")
	public ResponseEntity<?> countPackingSpaceAvailable(){
		Response<Integer> response = new Response<Integer>();
		int amountAvailable = 0;
		
		amountAvailable = parkingLotsService.countPackingSpaceAvailable();
		
		
		response.setData(amountAvailable);
		return ResponseEntity.ok(response);
	}
}
