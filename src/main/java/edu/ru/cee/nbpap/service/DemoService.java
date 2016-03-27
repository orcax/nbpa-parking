package edu.ru.cee.nbpap.service;

import edu.ru.cee.nbpap.dao.entity.Demo;

public interface DemoService {
	
	Demo getDemoById(int id);
	Demo addDemo(String name);

}
