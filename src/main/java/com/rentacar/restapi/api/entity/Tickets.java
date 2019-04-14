package com.rentacar.restapi.api.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_tickets")
@Getter
@Setter
public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "licence_plate")
	private String licencePlate;
	
	@Column(name = "parking_space")
	private String parkingSpace;
	
	@Column(name = "start_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;
	
	@Column(name = "finish_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime finishDate;
	
	@Column(name = "total_time_hours")
	private Long totalTimeHours;
	
	@Column(name = "total_value")
	private Double totalValue;
}
