package edu.ru.cee.nbpap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ru.cee.nbpap.dao.DemoDao;
import edu.ru.cee.nbpap.dao.entity.Demo;
import edu.ru.cee.nbpap.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao demoDao;

	@Override
	public Demo getDemoById(int id) {
		return demoDao.getDemoById(id);
	}

	@Override
	public Demo addDemo(String name) {
		return demoDao.addDemo(name);
	}

}
