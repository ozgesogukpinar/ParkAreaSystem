package com.park.system.parkinfo.serviceImpl;

import java.util.List;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.system.parkinfo.model.ParkAreaModel;
import com.park.system.parkinfo.repository.ParkAreaRepository;


@Service
public class ParkAreaServiceImpl {

	@Autowired
	private ParkAreaRepository parkAreaRepository;

	public ParkAreaModel save(ParkAreaModel parkArea) {
		return parkAreaRepository.save(parkArea);
	}

	public ParkAreaModel findByParkAreaId(Integer vehicleId) {
		return parkAreaRepository.findByParkAreaId(vehicleId);
	}
	
	public ParkAreaModel findByName(String vehicleName) {
		return parkAreaRepository.findByName(vehicleName);
	}

	public List<ParkAreaModel> getList() {
		return parkAreaRepository.findAll();
	}

	public void deleteByParkAreaId(Integer parkAreaId) {
		ParkAreaModel parkArea = parkAreaRepository.findByParkAreaId(parkAreaId);
		parkAreaRepository.delete(parkArea);
	}

}
