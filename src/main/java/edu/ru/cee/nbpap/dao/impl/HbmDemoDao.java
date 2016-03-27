package edu.ru.cee.nbpap.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import edu.ru.cee.nbpap.dao.DemoDao;
import edu.ru.cee.nbpap.dao.entity.Demo;

public class HbmDemoDao implements DemoDao {
	
	@Autowired
	private HibernateTemplate template;

	public Demo getDemoById(int id) {
		return template.get(Demo.class, id);
	}

	public Demo addDemo(String name) {
		Demo demo = new Demo();
		demo.setName(name);
		template.saveOrUpdate(demo);
		return demo;
	}

}
