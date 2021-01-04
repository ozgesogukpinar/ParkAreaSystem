package com.park.system.parkinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.park.system.parkinfo.model.VehicleModel;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Integer>{
	
	@Query("FROM VehicleModel u WHERE u.licensePlate=:licensePlate")
	VehicleModel findByLicensePlate(String licensePlate);
	
	VehicleModel findByVehicleId(Integer vehicleId);
}
