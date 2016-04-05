package edu.ru.cee.nbpap.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

	@Override
	public List<Occupancy> search(String location, Date startDate,
			Date endDate, String weekdays, String columns) {
		return occupancyDao.search(location, startDate, endDate,
				parseWeekdays(weekdays), Arrays.asList(columns.split(",")));
	}
	
	private List<Integer> parseWeekdays(String weekdays) {
		List<String> splittedWeekdays = Arrays.asList(weekdays.split(","));
		List<Integer> parsedWeekdays = new ArrayList<Integer>();
		for (String splittedWeekday : splittedWeekdays) {
			parsedWeekdays.add(Integer.parseInt(splittedWeekday));
		}
		return parsedWeekdays;
	}

}
