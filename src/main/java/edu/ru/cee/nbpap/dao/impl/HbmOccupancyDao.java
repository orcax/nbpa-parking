package edu.ru.cee.nbpap.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ru.cee.nbpap.dao.OccupancyDao;
import edu.ru.cee.nbpap.dao.entity.Occupancy;

@Repository
public class HbmOccupancyDao implements OccupancyDao {
	
	@Autowired
	private HibernateTemplate template;

	@Override
	public Occupancy getOccupancyById(int id) {
		return template.get(Occupancy.class, id);
	}

	@Override
	public List<Occupancy> search(String location, Date startDate,
			Date endDate, List<Integer> weekdays, List<String> columns) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Occupancy.class);
		criteria.add(Restrictions.between("datetime", startDate, endDate));
		criteria.add(Restrictions.in("weekday", weekdays));
		ProjectionList projectionList = Projections.projectionList();
		for (String column : columns) {
			projectionList.add(Projections.property(column));
		}
		criteria.setProjection(projectionList);
		return (List<Occupancy>) template.findByCriteria(criteria);
	}

}
