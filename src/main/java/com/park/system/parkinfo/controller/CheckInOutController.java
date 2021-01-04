package com.park.system.parkinfo.controller;

import java.util.Date; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.park.system.parkinfo.model.CheckInOutModel;
import com.park.system.parkinfo.model.ParkAreaModel;
import com.park.system.parkinfo.model.VehicleModel;
import com.park.system.parkinfo.serviceImpl.CheckInOutServiceImpl;
import com.park.system.parkinfo.serviceImpl.ParkAreaServiceImpl;
import com.park.system.parkinfo.serviceImpl.VehicleServiceImpl;

@RequestMapping(value = "/checkInAndOut")
@RestController
public class CheckInOutController {

	@Autowired
	private VehicleServiceImpl vehicleService;
	@Autowired
	private ParkAreaServiceImpl parkAreaService;
	@Autowired
	private CheckInOutServiceImpl checkInOutService;

	@RequestMapping(value = "/checkIn/{licensePlate}/{parkAreaName}", method = RequestMethod.GET)
	public String checkIn(@PathVariable(name = "licensePlate") String licensePlate,
			@PathVariable(name = "parkAreaName") String parkAreaName) {
		try {
			CheckInOutModel checkInOut = new CheckInOutModel();
			VehicleModel vehicle = vehicleService.findByLicensePlate(licensePlate);
			ParkAreaModel parkArea = parkAreaService.findByName(parkAreaName);
			if (vehicle == null)
				return "arac kaydi yapilmali";
			else if (parkArea == null)
				return "boyle bir park area yok";
			else {
				checkInOut.setVehicleId(vehicle.getVehicleId());
				checkInOut.setParkingAreaId(parkArea.getParkAreaId());
				if (parkArea.getCapacity() > checkInOutService.findVehicleCountByParkArea(parkArea.getParkAreaId())) {
					if (checkInOutService.findOnlyCheckInByVehicleId(checkInOut.getVehicleId()) != null)
						return "check in zaten yapili";
					else {
						checkInOut.setFee((double) 0);
						checkInOutService.save(checkInOut);
						return "check in basarili";
					}
				} else
					return "park area dolu !";
			}
		} catch (Exception e) {
			return e.toString();
		}
	}

	@RequestMapping(value = "/checkOut/{licensePlate}", method = RequestMethod.GET)
	public String checkOut(@PathVariable String licensePlate) {
		Double price = (double) 0;
		String[] priceRange = null;
		if (vehicleService.findByLicensePlate(licensePlate) != null) {
			VehicleModel vehicle = vehicleService.findByLicensePlate(licensePlate);
			CheckInOutModel checkInOut = checkInOutService.findNonCheckOutByVehicleId(vehicle.getVehicleId());
			if (checkInOut != null) {
				ParkAreaModel parkArea = parkAreaService.findByParkAreaId(checkInOut.getParkingAreaId());
				if (checkInOut.getFee() == 0) {
					Date date = new Date();
					checkInOut.setCheckOutDate(date);
					Double totalHour = (double) (checkInOut.getCheckOutDate().getHours()
							- checkInOut.getCheckInDate().getHours());

					// Double totalHour = (double) 4l;
					for (String key : parkArea.getPriceList().keySet()) {
						int i = 0;
						priceRange = key.split("-");
						i++;
						if (i == 1) {
							if (Double.parseDouble(priceRange[0]) <= totalHour
									&& Double.parseDouble(priceRange[1]) >= totalHour) {
								price = parkArea.getPriceList().get(key);
								break;
							}
							i = 0;
						}
					}
					if (vehicle.getType().equalsIgnoreCase("SUV"))
						price = price + (price / 10);
					else if (vehicle.getType().equalsIgnoreCase("MINIVAN"))
						price = price + (price / 15);
					checkInOut.setFee(price);
					checkInOutService.save(checkInOut);
					return "check out basarili. Ucret : " + checkInOut.getFee();
				} else
					return "check out zaten yapilmis !";
			} else
				return "bu plakaya ait kayit bulunamadi";
		} else
			return "once arac kaydi yapilmali !";
	}
}
