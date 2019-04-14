package com.rentacar.restapi.api.controller;

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
import com.rentacar.restapi.api.request.ParkingLeasedRequest;
import com.rentacar.restapi.api.response.Response;
import com.rentacar.restapi.api.service.ParkingLeasedService;
import com.rentacar.restapi.api.service.ParkingLotsService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/parkingLeased")
@CrossOrigin(origins = "*")//Permitindo o acesso de qualquer IP, porta, etc.
@Api(value = "ParkingLeased")
public class ParkingLeasedController {

	@Autowired
	private ParkingLeasedService service;
	
	@Autowired
	private ParkingLotsService parkingLotsService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('TECHNICIAN')")
	public ResponseEntity<?> create(HttpServletRequest request, @RequestBody ParkingLeasedRequest parkingLeasedRequest, BindingResult result) {
		Response<ParkingLeased> response = new Response<ParkingLeased>();
		
		try {
			validateCreateParkingLeased(parkingLeasedRequest, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			} 
			
			ParkingLeased parkingLeased = new ParkingLeased();
			parkingLeased.setStartDate(new Date());
			parkingLeased.setLicencePlate(parkingLeasedRequest.getLicencePlate());
			parkingLeased.setParkingLots(this.parkingLotsService.findByParkingSpace(parkingLeasedRequest.getParkingSpace()));
			ParkingLeased parkingLeasedPersisted = this.service.createOrUpdate(parkingLeased);
			response.setData(parkingLeasedPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	private void validateCreateParkingLeased(ParkingLeasedRequest parkingLeasedRequest, BindingResult result) {
		if(parkingLeasedRequest.getLicencePlate() == null) {
			result.addError(new ObjectError("licencePlate", "Licence plate no information"));
		}else if(this.service.findByLicencePlate(parkingLeasedRequest.getLicencePlate()) != null) {
			result.addError(new ObjectError("licencePlate", "Parking space already exists"));
		}
		
		if(parkingLeasedRequest.getParkingSpace() == null) {
			result.addError(new ObjectError("parkingSpace", "Parking space no information"));
		}
		
	}
}
