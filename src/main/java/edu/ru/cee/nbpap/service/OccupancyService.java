package edu.ru.cee.nbpap.service;

import java.util.Date;
import java.util.List;

import edu.ru.cee.nbpap.dao.entity.Occupancy;

public interface OccupancyService {
	
	Occupancy getOccupancyById(int id);
	List<Occupancy> search(String location, Date startDate, Date endDate,
			String weekdays, Date startTime, Date endTime);

}
