package edu.ru.cee.nbpap.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ru.cee.nbpap.dao.OccupancyDao;
import edu.ru.cee.nbpap.dao.entity.Occupancy;
import edu.ru.cee.nbpap.statistic.entity.StatisticType;

@Repository
public class HbmOccupancyDao implements OccupancyDao {
	
	private static final String BASE_SEARCH_HQL = "from Occupancy where 1 = 1";
	private static final String STATISTIC_HQL_PREFIX = "select cast(datetime as time), %s " + BASE_SEARCH_HQL;
	private static final String STATISTIC_HQL_SUFFIX = "group by cast(datetime as time) order by cast(datetime as time)";
	private static final String M_STATISTIC_HQL_PREFIX = "select cast(datetime as time), %s, weekday " + BASE_SEARCH_HQL;
	private static final String M_STATISTIC_HQL_SUFFIX = "group by weekday, cast(datetime as time) order by weekday, cast(datetime as time)";
	
	@Autowired
	private HibernateTemplate template;

	@Override
	public Occupancy getOccupancyById(int id) {
		return template.get(Occupancy.class, id);
	}

	@Override
	public List<Occupancy> getOccupancies(final String location, final Date startDate,
			final Date endDate, final List<Integer> weekdays, final Date startTime, final Date endTime) {
		return template.execute(new HibernateCallback<List<Occupancy>>() {

			@Override
			public List<Occupancy> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String, Object> parameters = new HashMap<String, Object>();
				String hql = BASE_SEARCH_HQL;
				hql += (buildLocationClause(location, parameters) +
						buildStartEndDateClause(startDate, endDate, parameters) +
						buildWeekdaysClause(weekdays, parameters) +
						buildStartEndTimeClause(startTime, endTime, parameters)) +
						" order by datetime";
				Query query = session.createQuery(hql);
				setParameters(query, parameters);
				return query.list();
			}
			
		});
	}
	
	private void setParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof Collection) {
				query.setParameterList(entry.getKey(), (Collection) value);
			} else {
				query.setParameter(entry.getKey(), value);
			}
		}
	}
	
	private String buildLocationClause(String location, Map<String, Object> parameters) {
		if (StringUtils.isNotEmpty(location)) {
			parameters.put("location", location);
			return " and location.name = :location";
		}
		return StringUtils.EMPTY;
	}
	
	private String buildStartEndDateClause(Date startDate, Date endDate, Map<String, Object> parameters) {
		String clause = StringUtils.EMPTY;
		if (startDate != null && endDate != null) {
			clause = " and (datetime >= :startDate and datetime <= :endDate)";
			parameters.put("startDate", startDate);
			parameters.put("endDate", stretchEndDate(endDate));
		} else if (startDate != null) {
			clause = " and datetime >= :startDate";
			parameters.put("startDate", startDate);
		} else if (endDate != null) {
			clause = " and datetime <= :endDate";
			parameters.put("endDate", stretchEndDate(endDate));
		}
		return clause;
	}
	
	private Date stretchEndDate(Date endDate) {
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}
	
	private String buildWeekdaysClause(List<Integer> weekdays, Map<String, Object> parameters) {
		if (CollectionUtils.isNotEmpty(weekdays)) {
			parameters.put("weekdays", weekdays);
			return " and weekday in (:weekdays)";
		}
		return StringUtils.EMPTY;
	}
	
	private String buildStartEndTimeClause(Date startTime, Date endTime, Map<String, Object> parameters) {
		String clause = StringUtils.EMPTY;
		if (startTime != null && endTime != null) {
			clause = " and (cast(datetime as time) >= :startTime and cast(datetime as time) <= :endTime)";
			parameters.put("startTime", startTime);
			parameters.put("endTime", endTime);
		} else if (startTime != null) {
			clause = " and cast(datetime as time) >= :startTime";
			parameters.put("startTime", startTime);
		} else if (endTime != null) {
			clause = " and cast(datetime as time) <= :endTime";
			parameters.put("endTime", endTime);
		}
		return clause;
	}

	@Override
	public List<Object[]> getColumnStatistics(final String location, final String column,
			final Date startDate, final Date endDate, final Integer weekday, final Double max,
			final Double min, final StatisticType type) {
		return template.execute(new HibernateCallback<List<Object[]>>() {

			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String, Object> parameters = new HashMap<String, Object>();
				String hql = String.format(STATISTIC_HQL_PREFIX, type.name() + "(" + column + ")") +
						buildLocationClause(location, parameters) +
						buildStartEndDateClause(startDate, endDate, parameters) +
						buildWeekdayClause(weekday, parameters) +
						buildRangeClause(column, max, min, parameters) +
						" " + STATISTIC_HQL_SUFFIX;
				Query query = session.createQuery(hql);
				setParameters(query, parameters);
				return query.list();
			}
			
		});
	}
	
	private String buildWeekdayClause(Integer weekday, Map<String, Object> parameters) {
		if (weekday != null) {
			parameters.put("weekday", weekday);
			return " and weekday = :weekday";
		}
		return StringUtils.EMPTY;
	}
	
	private String buildRangeClause(String column, Double max, Double min, Map<String, Object> parameters) {
		String clause = StringUtils.EMPTY;
		if (max != null && min != null) {
			clause = " and(" + column + " >= :min and " + column + " <= :max)";
			parameters.put("max", max);
			parameters.put("min", min);
		} else if (max != null) {
			clause = " and " + column + " <= :max";
			parameters.put("max", max);
		} else if (min != null) {
			clause = " and " + column + " >= :min";
			parameters.put("min", min);
		}
		return clause;
	}

	@Override
	public List<Object[]> getColumnStatistics(final String location, final String column,
			final Date startDate, final Date endDate, final List<Integer> weekdays, final Double max,
			final Double min, final StatisticType type) {
		return template.execute(new HibernateCallback<List<Object[]>>() {

			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String, Object> parameters = new HashMap<String, Object>();
				String hql = String.format(M_STATISTIC_HQL_PREFIX, type.name() + "(" + column + ")") +
						buildLocationClause(location, parameters) +
						buildStartEndDateClause(startDate, endDate, parameters) +
						buildWeekdaysClause(weekdays, parameters) +
						buildRangeClause(column, max, min, parameters) +
						" " + M_STATISTIC_HQL_SUFFIX;
				Query query = session.createQuery(hql);
				setParameters(query, parameters);
				return query.list();
			}
			
		});
	}

}
