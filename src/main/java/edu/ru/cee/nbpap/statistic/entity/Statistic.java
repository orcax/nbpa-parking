package edu.ru.cee.nbpap.statistic.entity;

import java.util.Date;

public final class Statistic {
	
	private Date time;
	private Double value;
	private StatisticType type;
	
	public Statistic(Date time, Double value, StatisticType type) {
		this.time = time;
		this.value = value;
		this.type = type;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public StatisticType getType() {
		return type;
	}
	
	public void setType(StatisticType type) {
		this.type = type;
	}

}
