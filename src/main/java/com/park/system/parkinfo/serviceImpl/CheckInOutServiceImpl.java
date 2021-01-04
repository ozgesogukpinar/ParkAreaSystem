package com.park.system.parkinfo.serviceImpl;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.system.parkinfo.model.CheckInOutModel;
import com.park.system.parkinfo.repository.CheckInOutRepository;


@Service
public class CheckInOutServiceImpl {

	@Autowired
	private CheckInOutRepository checkInOutRepository;

	public CheckInOutModel save(CheckInOutModel checkInOutModel) {
		return checkInOutRepository.save(checkInOutModel);
	}

	public CheckInOutModel findByCheckInOutId(Integer id) {
		return checkInOutRepository.findByCheckInOutId(id);
	}
	
	public List<CheckInOutModel> getList() {
		return checkInOutRepository.findAll();
	}

	public int findVehicleCountByParkArea(Integer parkAreaId) {
		return checkInOutRepository.findVehicleCountByParkArea(parkAreaId);
	}
	
	public ArrayList<CheckInOutModel> findByVehicleId(Integer vehicleId) {
		return checkInOutRepository.findByVehicleId(vehicleId);
	}
	
	public CheckInOutModel findNonCheckOutByVehicleId(Integer vehicleId) {
		return checkInOutRepository.findNonCheckOutByVehicleId(vehicleId);
	}
	
	public CheckInOutModel findOnlyCheckInByVehicleId(Integer vehicleId) {
		return checkInOutRepository.findOnlyCheckInByVehicleId(vehicleId);
	}
	
	public Double findIncomeByParkAreaId(Integer parkAreaId) {
		return checkInOutRepository.findIncomeByParkAreaId(parkAreaId);
	}
		
}
