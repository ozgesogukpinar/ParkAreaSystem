package com.park.system.parkinfo.serviceImpl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.system.parkinfo.model.VehicleModel;
import com.park.system.parkinfo.repository.VehicleRepository;

@Service
public class VehicleServiceImpl {
	
	@Autowired
	private VehicleRepository vehicleRepository;

	public VehicleModel save(VehicleModel vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public VehicleModel findByLicensePlate(String licensePlate) {
		return vehicleRepository.findByLicensePlate(licensePlate);
	}

	public VehicleModel findByVehicleId(Integer vehicleId) {
		return vehicleRepository.findByVehicleId(vehicleId);
	}

	public List<VehicleModel> getList() {
		return vehicleRepository.findAll();
	}

	public void deleteByVehicleId(Integer vehicleId) {
		VehicleModel vehicle = vehicleRepository.findByVehicleId(vehicleId);
		vehicleRepository.delete(vehicle);
	}
	
}
