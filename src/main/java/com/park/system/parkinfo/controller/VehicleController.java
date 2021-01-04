package com.park.system.parkinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.park.system.parkinfo.model.VehicleModel;
import com.park.system.parkinfo.serviceImpl.CheckInOutServiceImpl;
import com.park.system.parkinfo.serviceImpl.VehicleServiceImpl;

@RequestMapping(value = "/vehicles")
@RestController
public class VehicleController {

	@Autowired
	private VehicleServiceImpl vehicleService;
	@Autowired
	private CheckInOutServiceImpl checkInOutService;

	@RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
	public String addVehicle(@RequestBody VehicleModel vehicle) {
		try {
			if (vehicle.getType().equalsIgnoreCase("sedan") || vehicle.getType().equalsIgnoreCase("suv")
					|| vehicle.getType().equalsIgnoreCase("minivan")) {
				vehicleService.save(vehicle);
				return "arac kaydı basarili";
			} else
				return "arac tipi sedan, suv ya da minivan olmali !";
		} catch (Exception ConstraintViolationException) {
			return "plaka zaten tanimli";
		}
	}

	@RequestMapping(value = "/getMyParkDetails/{licensePlate}", method = RequestMethod.GET)
	public String getMyParkDetails(@PathVariable("licensePlate") String licensePlate) {
		try {
			VehicleModel vehicle = vehicleService.findByLicensePlate(licensePlate);
			if(vehicle != null)
				return checkInOutService.findByVehicleId(vehicle.getVehicleId()).toString();
			else
				return "boyle bir arac bulunamadi";

		} catch (Exception e) {

		}
		return null;
	}

}
