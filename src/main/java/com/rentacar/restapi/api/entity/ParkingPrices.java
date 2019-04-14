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
@Table(name = "tb_parking_prices")
@Getter
@Setter
public class ParkingPrices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@NotBlank(message = "Permanence required")
	@Column(name = "permanence")
	private String permanence;
	
	@NotBlank(message = "Price required")
	@Column(name = "price", unique = true)
	private Double price;
}
