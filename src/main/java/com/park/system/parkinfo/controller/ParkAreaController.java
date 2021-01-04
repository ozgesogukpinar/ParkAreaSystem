package com.park.system.parkinfo.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.park.system.parkinfo.model.ParkAreaModel;
import com.park.system.parkinfo.serviceImpl.CheckInOutServiceImpl;
import com.park.system.parkinfo.serviceImpl.ParkAreaServiceImpl;

@RequestMapping(value = "/parkAreas")
@RestController
public class ParkAreaController {

	@Autowired
	private ParkAreaServiceImpl parkAreaService;
	@Autowired
	private CheckInOutServiceImpl checkInOutService;

	@RequestMapping(value = "/addParkArea", method = RequestMethod.POST)
	public String addParkArea(@RequestBody ParkAreaModel parkArea) {
		try {
			if (parkAreaService.findByName(parkArea.getName()) == null) {
				parkAreaService.save(parkArea);
				return "park area kaydı yapıldi";
			} else
				return "bu isimde bir park area bulunmaktadir.";
		} catch (Exception e) {
			return e.toString();
		}
	}

	@RequestMapping(value = "/getParkAreaByName/{name}", method = RequestMethod.GET)
	public String getParkAreaByName(@PathVariable("name") String name) {
		if (parkAreaService.findByName(name) != null)
			return parkAreaService.findByName(name).toString();
		else
			return "boyle bir park area bulunamadi";
	}

	@RequestMapping(value = "/deleteParkArea/{id}", method = RequestMethod.GET)
	public String deleteParkArea(@PathVariable("id") Integer id) {
		if (parkAreaService.findByParkAreaId(id) != null) {
			parkAreaService.deleteByParkAreaId(id);
			return "park area silindi";
		} else
			return "boyle bir park area yok";
	}

	@RequestMapping(value = "/updateParkArea/{id}", method = RequestMethod.POST)
	public String updateParkArea(@PathVariable("id") Integer id, @RequestBody ParkAreaModel parkArea) {
		if (parkAreaService.findByParkAreaId(id) != null) {
			ParkAreaModel oldParkArea = parkAreaService.findByParkAreaId(id);
			if (parkArea != null) {
				oldParkArea.setName(parkArea.getName());
				oldParkArea.setCityAttribute(parkArea.getCityAttribute());
				oldParkArea.setCapacity(parkArea.getCapacity());
				oldParkArea.setPriceList(parkArea.getPriceList());
			}
			parkAreaService.save(oldParkArea);
			return "park area güncellendi";
		} else
			return "boyle bir park area yok";
	}

	@RequestMapping(value = "/getDailyIncome/{id}", method = RequestMethod.GET)
	public String getDailyIncome(@PathVariable("id") Integer id) {
		if (parkAreaService.findByParkAreaId(id) != null) {
			if (checkInOutService.findIncomeByParkAreaId(id) != null)
				return checkInOutService.findIncomeByParkAreaId(id).toString();
			else
				return "0";
		}
		else
			return "Boyle bir park area bulunamadi";
	}
}
