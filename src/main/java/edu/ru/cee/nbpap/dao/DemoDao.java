package edu.ru.cee.nbpap.dao;

import edu.ru.cee.nbpap.dao.entity.Demo;

public interface DemoDao {
	
	Demo getDemoById(int id);
	Demo addDemo(String name);

}
