package com.park.system.parkinfo.repository;

import java.util.ArrayList;  

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.park.system.parkinfo.model.CheckInOutModel;


@Repository
public interface CheckInOutRepository extends JpaRepository<CheckInOutModel, Integer>{

	@Query("SELECT COUNT(u) FROM CheckInOutModel u WHERE u.checkOutDate=null AND u.parkingAreaId=:parkAreaId")
	int findVehicleCountByParkArea(Integer parkAreaId);

	@Query("FROM CheckInOutModel u WHERE u.vehicleId=:vehicleId")
	ArrayList<CheckInOutModel> findByVehicleId(Integer vehicleId);
	
	@Query("FROM CheckInOutModel u WHERE u.vehicleId=:vehicleId AND u.checkOutDate=null")
	CheckInOutModel findNonCheckOutByVehicleId(Integer vehicleId);
	
	@Query("FROM CheckInOutModel u WHERE u.checkOutDate=null AND u.checkInDate!=null AND u.vehicleId=:vehicleId")
	CheckInOutModel findOnlyCheckInByVehicleId(Integer vehicleId);
	
	CheckInOutModel findByCheckInOutId(Integer id);
	
	@Query("SELECT SUM(u.fee) FROM CheckInOutModel u WHERE u.parkingAreaId=:parkAreaId")
	Double findIncomeByParkAreaId(Integer parkAreaId);
	
}
