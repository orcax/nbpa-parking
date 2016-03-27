package edu.ru.cee.nbpap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.ru.cee.nbpap.dao.DemoDao;
import edu.ru.cee.nbpap.dao.entity.Demo;
import edu.ru.cee.nbpap.service.DemoService;

public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao demoDao;

	public Demo getDemoById(int id) {
		return demoDao.getDemoById(id);
	}

	public Demo addDemo(String name) {
		return demoDao.addDemo(name);
	}

}
