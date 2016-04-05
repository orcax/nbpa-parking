package edu.ru.cee.nbpap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ru.cee.nbpap.dao.OccupancyDao;
import edu.ru.cee.nbpap.dao.entity.Occupancy;
import edu.ru.cee.nbpap.service.OccupancyService;

@Service
public class OccupancyServiceImpl implements OccupancyService {
	
	@Autowired
	private OccupancyDao occupancyDao;

	@Override
	public Occupancy getOccupancyById(int id) {
		return occupancyDao.getOccupancyById(id);
	}

}
