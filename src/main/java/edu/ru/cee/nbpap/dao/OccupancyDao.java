package edu.ru.cee.nbpap.dao;

import java.util.Date;
import java.util.List;

import edu.ru.cee.nbpap.dao.entity.Occupancy;
import edu.ru.cee.nbpap.statistic.entity.StatisticType;

public interface OccupancyDao {
	
	Occupancy getOccupancyById(int id);
	List<Occupancy> getOccupancies(String location, Date startDate, Date endDate,
			List<Integer> weekdays, Date startTime, Date endTime);
	List<Object[]> getColumnStatistics(String location, String column, Date startDate,
			Date endDate, Integer weekday, Double max, Double min, StatisticType type);

}
