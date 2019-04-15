package com.rentacar.restapi.api.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.rentacar.restapi.api.entity.User;
import com.rentacar.restapi.api.request.ParkingLeasedRequest;
import com.rentacar.restapi.api.response.Response;
import com.rentacar.restapi.api.service.ParkingLeasedService;
import com.rentacar.restapi.api.service.ParkingLotsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

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
	@ApiOperation(value = "Estacionar um carro numa vaga", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(@ApiResponse(code = 201, message = "Carro estacionado", response = ParkingLeased.class, 
		responseHeaders = @ResponseHeader(name = "ParkingLeased", description = "Vaga ocupada", response = ParkingLeased.class)))
	public ResponseEntity<?> create(HttpServletRequest request, 
			@ApiParam(
				    value="ParkingLeasedRequest", 
				    name="parkingLeasedRequest", 
				    required=true)
			@RequestBody ParkingLeasedRequest parkingLeasedRequest, BindingResult result) {
		Response<ParkingLeased> response = new Response<ParkingLeased>();
		
		try {
			validateCreateParkingLeased(parkingLeasedRequest, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			} 
			
			ParkingLeased parkingLeased = new ParkingLeased();
			parkingLeased.setStartDate(LocalDateTime.now());
			parkingLeased.setLicencePlate(parkingLeasedRequest.getLicencePlate());
			parkingLeased.setParkingLots(this.parkingLotsService.findByParkingSpace(parkingLeasedRequest.getParkingSpace()));
			ParkingLeased parkingLeasedPersisted = this.service.createOrUpdate(parkingLeased);
			response.setData(parkingLeasedPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	private void validateCreateParkingLeased(ParkingLeasedRequest parkingLeasedRequest, BindingResult result) {
		if(parkingLeasedRequest.getLicencePlate() == null) {
			result.addError(new ObjectError("licencePlate", "Licence plate no information"));
		}else if(this.service.findByLicencePlate(parkingLeasedRequest.getLicencePlate()) != null) {
			result.addError(new ObjectError("licencePlate", "Licence plate already exists"));
		}
		
		if(parkingLeasedRequest.getParkingSpace() == null) {
			result.addError(new ObjectError("parkingSpace", "Parking space no information"));
		}else if(this.service.findByParkingSpace(parkingLeasedRequest.getParkingSpace()) != null) {
			result.addError(new ObjectError("parkingSpace", "Parking space already exists"));
		}
		
	}
}
