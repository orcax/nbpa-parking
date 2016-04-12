package edu.ru.cee.nbpap.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ru.cee.nbpap.dao.entity.Occupancy;
import edu.ru.cee.nbpap.service.OccupancyService;
import edu.ru.cee.nbpap.statistic.entity.Statistic;
import edu.ru.cee.nbpap.statistic.entity.StatisticType;

@Controller
@RequestMapping(value = "/api/occupancy")
public class OccupancyController {

    @Autowired
    private OccupancyService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Occupancy> search(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) String weekdays,
            @RequestParam(required = false) @DateTimeFormat(pattern = "HH:mm") Date startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "HH:mm") Date endTime) {
        return service.search(location, startDate, endDate, weekdays,
                startTime, endTime);
    }

    @RequestMapping(value = "/meanhour", method = RequestMethod.GET)
    @ResponseBody
    public List<Statistic> meanHour(
            @RequestParam(required = false) String location,
            @RequestParam(required = true) String column,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) Integer weekday,
            @RequestParam(required = false) Double max,
            @RequestParam(required = false) Double min) {
        return service.getColumnStatistics(location, column, startDate,
                endDate, weekday, max, min, StatisticType.AVG);
    }

    @RequestMapping(value = "/occupancy/stdHour", method = RequestMethod.GET)
    @ResponseBody
    public List<Statistic> stdHour(
            @RequestParam(required = false) String location,
            @RequestParam(required = true) String column,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) Integer weekday,
            @RequestParam(required = false) Double max,
            @RequestParam(required = false) Double min) {
        return service.getColumnStatistics(location, column, startDate,
                endDate, weekday, max, min, StatisticType.STD);
    }
}
