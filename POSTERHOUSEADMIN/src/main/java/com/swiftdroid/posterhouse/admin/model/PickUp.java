package com.swiftdroid.posterhouse.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PickUp {
	@JsonProperty("pickup_time")
	private String pickUpTime;
	@JsonProperty("pickup_date")
	private String pickUpDate;
	@JsonProperty("pickup_location")
	private String pickUpLocation;
	@JsonProperty("expected_package_count")
	private int count;
	public PickUp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PickUp(String pickUpTime, String pickUpDate, String pickUpLocation, int count) {
		super();
		this.pickUpTime = pickUpTime;
		this.pickUpDate = pickUpDate;
		this.pickUpLocation = pickUpLocation;
		this.count = count;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public String getPickUpLocation() {
		return pickUpLocation;
	}
	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	

}
