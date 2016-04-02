package edu.ru.cee.nbpap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ru.cee.nbpap.dao.entity.Demo;
import edu.ru.cee.nbpap.dao.entity.Occupancy;
import edu.ru.cee.nbpap.service.DemoService;
import edu.ru.cee.nbpap.service.OccupancyService;

@Controller
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	@Autowired
	private OccupancyService occupancyService;
	
	@RequestMapping("")
	@ResponseBody
	public Demo getDemoById(@RequestParam(value = "id") int id) {
		return demoService.getDemoById(id);
	}
	
	@RequestMapping("addDemo")
	@ResponseBody
	public Demo addDemo(@RequestParam(value = "name") String name) {
		return demoService.addDemo(name);
	}
	
	/*
	@RequestMapping("occupancy")
	@ResponseBody
	public Occupancy getOccupancyById(@RequestParam(value = "id") int id) {
		return occupancyService.getOccupancyById(id);
	}
	*/

}
