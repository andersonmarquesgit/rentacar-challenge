package com.rentacar.restapi.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_parking_lots")
@Getter
@Setter
public class ParkingLots {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@NotBlank(message = "Parking Space required")
	@Column(name = "parking_space", unique = true)
	private String parkingSpace;
}
