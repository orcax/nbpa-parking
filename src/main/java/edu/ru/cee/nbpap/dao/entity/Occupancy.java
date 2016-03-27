package edu.ru.cee.nbpap.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Occupancy")
public class Occupancy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6477898278366161236L;
	
	private int id;
	private Location location;
	private Date startHour;
	private int weekday;
	private int ticketNo;
	private int ccExpressNo;
	private int monthlyNo;
	private int dailyTotalNo;
	private float occupancy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LocationId")
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Column(name = "StartHour")
	public Date getStartHour() {
		return startHour;
	}
	
	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	@Column(name = "Weekday")
	public int getWeekday() {
		return weekday;
	}

	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	@Column(name = "TicketNo")
	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	@Column(name = "CcExpressNo")
	public int getCcExpressNo() {
		return ccExpressNo;
	}

	public void setCcExpressNo(int ccExpressNo) {
		this.ccExpressNo = ccExpressNo;
	}

	@Column(name = "MonthlyNo")
	public int getMonthlyNo() {
		return monthlyNo;
	}

	public void setMonthlyNo(int monthlyNo) {
		this.monthlyNo = monthlyNo;
	}

	@Column(name = "DailyTotalNo")
	public int getDailyTotalNo() {
		return dailyTotalNo;
	}

	public void setDailyTotalNo(int dailyTotalNo) {
		this.dailyTotalNo = dailyTotalNo;
	}

	@Column(name = "Occupancy")
	public float getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(float occupancy) {
		this.occupancy = occupancy;
	}

}
