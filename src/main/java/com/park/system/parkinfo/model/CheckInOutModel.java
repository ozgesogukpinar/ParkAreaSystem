package com.park.system.parkinfo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Data

@Entity
public class CheckInOutModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer checkInOutId;
	
	private Date checkInDate = new Date();
	private Date checkOutDate;
	private int vehicleId;
	private int parkingAreaId;
	private Double fee;
	
}
