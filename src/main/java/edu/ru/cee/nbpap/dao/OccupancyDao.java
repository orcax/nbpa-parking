package edu.ru.cee.nbpap.dao;

import java.util.Date;
import java.util.List;

import edu.ru.cee.nbpap.dao.entity.Occupancy;

public interface OccupancyDao {
	
	Occupancy getOccupancyById(int id);
	List<Occupancy> search(String location, Date startDate, Date endDate,
			List<Integer> weekdays, Date startTime, Date endTime);

}
