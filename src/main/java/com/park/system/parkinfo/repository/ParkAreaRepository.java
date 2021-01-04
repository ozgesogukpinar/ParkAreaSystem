package com.park.system.parkinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;   
import org.springframework.stereotype.Repository;

import com.park.system.parkinfo.model.ParkAreaModel;

@Repository
public interface ParkAreaRepository extends JpaRepository<ParkAreaModel, Integer>{

	ParkAreaModel findByName(String name);
	
	ParkAreaModel findByParkAreaId(Integer id);
	
}
