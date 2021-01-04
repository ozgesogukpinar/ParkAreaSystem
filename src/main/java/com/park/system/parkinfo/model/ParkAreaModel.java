package com.park.system.parkinfo.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data

@Entity
public class ParkAreaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer parkAreaId;
	@NotNull
	@Column(unique = true)
	private String name;
	private int capacity;
	private String cityAttribute;
	private HashMap<String, Double> priceList;
}
