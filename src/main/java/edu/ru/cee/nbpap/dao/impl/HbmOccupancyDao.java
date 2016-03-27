package edu.ru.cee.nbpap.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import edu.ru.cee.nbpap.dao.OccupancyDao;
import edu.ru.cee.nbpap.dao.entity.Occupancy;

public class HbmOccupancyDao implements OccupancyDao {
	
	@Autowired
	private HibernateTemplate template;

	@Override
	public Occupancy getOccupancyById(int id) {
		return template.get(Occupancy.class, id);
	}

}
