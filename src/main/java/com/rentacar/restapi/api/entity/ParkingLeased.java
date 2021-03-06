package com.rentacar.restapi.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_parking_leased")
@Getter
@Setter
public class ParkingLeased {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@OneToOne
	@JoinColumn(name="parking_lots_id")
	private ParkingLots parkingLots;
	
	@NotBlank(message = "Licence Plate required")
	@Column(name = "licence_plate", unique = true)
	private String licencePlate;
	
	@Column(name = "start_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;
}
