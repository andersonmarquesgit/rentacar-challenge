package com.rentacar.restapi.api.controller;

import java.math.BigDecimal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.restapi.api.entity.ParkingLeased;
import com.rentacar.restapi.api.entity.ParkingLots;
import com.rentacar.restapi.api.request.ParkingLeasedRequest;
import com.rentacar.restapi.api.request.PeriodoRequest;
import com.rentacar.restapi.api.response.Response;
import com.rentacar.restapi.api.service.ParkingLeasedService;
import com.rentacar.restapi.api.service.TicketsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*")//Permitindo o acesso de qualquer IP, porta, etc.
@Api(value = "Report")
public class ReportController {

	@Autowired
	private ParkingLeasedService parkingLeasedService;
	
	@Autowired
	private TicketsService ticketsSevice;
	
	@GetMapping(value = "/parkingLeased/{page}/{count}")
	@PreAuthorize("hasAnyRole('TECHNICIAN')")
	@ApiOperation(value = "Relatório com ocupação atual do estacionamento")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "Page", required = false, dataType = "string", paramType = "query", defaultValue = "0"),
		@ApiImplicitParam(name = "count", value = "Count", required = false, dataType = "string", paramType = "query", defaultValue = "10")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", responseContainer = "Page"),
		@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<Response<Page<ParkingLeased>>> reportPackingLeased(@PathVariable int page, @PathVariable int count){
		Response<Page<ParkingLeased>> response = new Response<Page<ParkingLeased>>();
		Page<ParkingLeased> packingLeasedList = parkingLeasedService.findAll(page, count);
		response.setData(packingLeasedList);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/payments")
	@PreAuthorize("hasAnyRole('TECHNICIAN')")
	@ApiOperation(value = "Relatório com valor arrecadado por período", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(@ApiResponse(code = 200, message = "ok", response = Double.class))
	public ResponseEntity<?> reportPayments(HttpServletRequest request, 
			@ApiParam(
				    value="ParkingLeasedRequest", 
				    name="parkingLeasedRequest", 
				    required=true)
			@RequestBody PeriodoRequest periodRequest, BindingResult result){
		Response<BigDecimal> response = new Response<BigDecimal>();
		BigDecimal amountPayment = ticketsSevice.calculatePayment(periodRequest.getStartDate(), periodRequest.getFinishDate());
		
		response.setData(amountPayment);
		return ResponseEntity.ok(response);
	}
}
