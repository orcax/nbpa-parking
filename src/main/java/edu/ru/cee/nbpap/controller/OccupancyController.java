package edu.ru.cee.nbpap.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ru.cee.nbpap.dao.entity.Occupancy;

@Controller
public class OccupancyController {

    @RequestMapping(value = "/occupancy", method = RequestMethod.GET)
    @ResponseBody
    public List<Occupancy> search(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) String weekdays,
            @RequestParam(required = false) Timestamp startTime,
            @RequestParam(required = false) Timestamp endTime,
            @RequestParam(required = false) String columns) {
        List<Occupancy> results = new ArrayList<Occupancy>();
        return results;
    }
}
