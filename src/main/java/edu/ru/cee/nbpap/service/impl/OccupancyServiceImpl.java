package edu.ru.cee.nbpap.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ru.cee.nbpap.dao.OccupancyDao;
import edu.ru.cee.nbpap.dao.entity.Occupancy;
import edu.ru.cee.nbpap.service.OccupancyService;
import edu.ru.cee.nbpap.statistic.entity.Statistic;
import edu.ru.cee.nbpap.statistic.entity.StatisticType;

@Service
public class OccupancyServiceImpl implements OccupancyService {
	
	@Autowired
	private OccupancyDao occupancyDao;

	@Override
	public Occupancy getOccupancyById(int id) {
		return occupancyDao.getOccupancyById(id);
	}

	@Override
	public List<Occupancy> search(String location, Date startDate,
			Date endDate, String weekdays, Date startTime, Date endTime) {
		return occupancyDao.getOccupancies(location, startDate, endDate,
				parseWeekdays(weekdays), startTime, endTime);
	}
	
	private List<Integer> parseWeekdays(String weekdays) {
		if (weekdays == null) {
			return null;
		}
		List<String> splittedWeekdays = Arrays.asList(weekdays.split(","));
		List<Integer> parsedWeekdays = new ArrayList<Integer>();
		for (String splittedWeekday : splittedWeekdays) {
			parsedWeekdays.add(Integer.parseInt(splittedWeekday));
		}
		return parsedWeekdays;
	}

	@Override
	public List<Statistic> getColumnStatistics(String location, String column,
			Date startDate, Date endDate, Integer weekday, Double max,
			Double min, StatisticType type) {
		List<Object[]> objs = occupancyDao.getColumnStatistics(location, column, startDate, endDate, weekday, max, min, type);
		if (CollectionUtils.isNotEmpty(objs)) {
			List<Statistic> statistics = new ArrayList<Statistic>();
			for (Object[] obj : objs) {
				statistics.add(new Statistic((Date) obj[0], (Double) obj[1], type));
			}
			return statistics;
		}
		return null;
	}

}
