package com.plp.propertymgt.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

public class Booking {
	
	private int id;
	private String courtName;
	private Date bookingDate;
	private Date startTime;
	private Date endTime;
	private String customerName;
	private int cost;
	private boolean isCompleted;
	private boolean isNoBooking;
	
	private List<String> imageLinks = new ArrayList<String>();
	
	private Date actualStartTime = null;
	private Date actualEndTime = null;
	
	private boolean isPlayed;

	
    @Override
    public String toString() {
    	
        return  "id: " + id + "\r" +
        	    "courtName: " + courtName + "\r" +
                "bookingDate: " + bookingDate + "\r" +
                "startTime: " + startTime  + "\r" +
                "endTime: " + endTime + "\r" +
                "customerName: " + customerName + "\r" +
                "cost: " + cost + "\r" +
                "isCompleted: " + isCompleted +"\r" +
        		"isPlayed: " + isPlayed +"\r" + 
        		"actualStartTime: " + actualStartTime +"\r"+
        		"actualEndTime: " + actualEndTime +"\r";
    }
    
	public static Booking getFromLinkedTreeMap(LinkedTreeMap<?,?> linkedMap){
		
		Booking booking = new Booking();
		
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
			booking.setId((int) Double.parseDouble((linkedMap.get("id").toString())));
			booking.setCourtName((String) linkedMap.get("courtName"));
			booking.setBookingDate(sdf.parse(linkedMap.get("bookingDate").toString()));
			booking.setStartTime(sdf.parse(linkedMap.get("startTime").toString()));
			booking.setEndTime(sdf.parse(linkedMap.get("endTime").toString()));
			booking.setCustomerName((String) linkedMap.get("customer"));
			booking.setCost((int) Double.parseDouble((linkedMap.get("cost").toString())));
			booking.setCompleted((Boolean)linkedMap.get("isCompleted"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
    	
		return booking;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public boolean isNoBooking() {
		return isNoBooking;
	}

	public void setNoBooking(boolean isNoBooking) {
		this.isNoBooking = isNoBooking;
	}

	public Date getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public boolean isPlayed() {
		return true;
	}

	public void setPlayed(boolean isPlayed) {
		this.isPlayed = isPlayed;
	}

	public List<String> getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(List<String> imageLinks) {
		this.imageLinks = imageLinks;
	}
}
