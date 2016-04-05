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
@Table(name = "occupancy")
public class Occupancy implements Serializable {

	private static final long serialVersionUID = -6477898278366161236L;
	
	private int id;
	private Location location;
	private Date datetime;
	private int weekday;
	private int dailyNumber;
	private int monthlyNumber;
	private int totalNumber;
	private double occupancy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(name = "datetime")
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

	@Column(name = "weekday")
    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

	@Column(name = "daily_number")
    public int getDailyNumber() {
        return dailyNumber;
    }

    public void setDailyNumber(int dailyNumber) {
        this.dailyNumber = dailyNumber;
    }

	@Column(name = "monthly_number")
    public int getMonthlyNumber() {
        return monthlyNumber;
    }

    public void setMonthlyNumber(int monthlyNumber) {
        this.monthlyNumber = monthlyNumber;
    }

	@Column(name = "total_number")
    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

	@Column(name = "occupancy")
    public double getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(double occupancy) {
        this.occupancy = occupancy;
    }

}	