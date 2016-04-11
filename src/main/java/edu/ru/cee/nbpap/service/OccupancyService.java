package edu.ru.cee.nbpap.service;

import java.util.Date;
import java.util.List;

import edu.ru.cee.nbpap.dao.entity.Occupancy;
import edu.ru.cee.nbpap.statistic.entity.Statistic;
import edu.ru.cee.nbpap.statistic.entity.StatisticType;

public interface OccupancyService {
	
	Occupancy getOccupancyById(int id);
	List<Occupancy> search(String location, Date startDate, Date endDate,
			String weekdays, Date startTime, Date endTime);
	List<Statistic> getColumnStatistics(String location, String column, Date startDate,
			Date endDate, Integer weekday, Double max, Double min, StatisticType type);

}
