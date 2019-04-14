package com.rentacar.restapi.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.restapi.api.entity.ParkingLots;
import com.rentacar.restapi.api.response.Response;
import com.rentacar.restapi.api.service.ParkingLotsService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/parkingLots")
@CrossOrigin(origins = "*")//Permitindo o acesso de qualquer IP, porta, etc.
@Api(value = "ParkingLots")
public class ParkingLotsController {

	@Autowired
	private ParkingLotsService parkingLotsService;
	
	@GetMapping(value = "/available")
	@PreAuthorize("hasAnyRole('TECHNICIAN')")
	public ResponseEntity<?> countPackingSpaceAvailable(){
		Response<Integer> response = new Response<Integer>();
		int amountAvailable = parkingLotsService.countPackingSpaceAvailable();
		
		response.setData(amountAvailable);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/availablePosition")
	@PreAuthorize("hasAnyRole('TECHNICIAN')")
	public ResponseEntity<?> findPackingSpaceAvailable(){
		Response<Set<ParkingLots> > response = new Response<Set<ParkingLots> >();
		Set<ParkingLots> parkingLotsAvailable = parkingLotsService.findPackingSpaceAvailable();
		
		response.setData(parkingLotsAvailable);
		return ResponseEntity.ok(response);
	}
}
