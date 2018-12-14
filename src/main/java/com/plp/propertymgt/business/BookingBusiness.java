package com.plp.propertymgt.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import com.plp.motiondetection.business.MotionBusiness;
import com.plp.motiondetection.model.Incident;
import com.plp.motiondetection.model.IncidentSeries;
import com.plp.motiondetection.model.TimeSpacedIncident;
import com.plp.propertymgt.data.BookingData;
import com.plp.propertymgt.data.mysql.BookingDataMySql;
import com.plp.propertymgt.model.Booking;

public class BookingBusiness {
	
	BookingData bookingData = new BookingDataMySql();
	MotionBusiness motionBusiness = new MotionBusiness(); 
	
	
	public List<Incident> getLastIncidents(){ 
		
		return motionBusiness.getLastIncidents();
		
	}
	
	public List<Booking> getBookings(Date bookingDate,int tenantId){
		
        
        Calendar calEndDate = Calendar.getInstance();
        calEndDate.setTime(bookingDate);
        calEndDate.add(Calendar.DATE, 1);
        //Date dateBefore30Days = calEndDate.getTime();
        
        List<Booking> bookings = bookingData.getBookings(bookingDate, tenantId);
        
        
        List<TimeSpacedIncident> Motions = motionBusiness.getIncidents(bookingDate, calEndDate.getTime(), 30,2,8);

        
        for (TimeSpacedIncident timeSpacedIncident: Motions){
        	
        	for (String instanceName : timeSpacedIncident.getInstanceNames()){
        		
        		       		
        		if (timeSpacedIncident.getIncidentSeriesesByInstanceName(instanceName) != null){
        			for (IncidentSeries incidentSeries : timeSpacedIncident.getIncidentSeriesesByInstanceName(instanceName)){
        				
        				boolean isBookingAvailable = isBookingAvailableForIncident(bookings, incidentSeries);
        				
        				if (!isBookingAvailable){
        					
        					Booking booking = new Booking();
        					
        					//int rand = new Random().nextInt(5000) * -1;
        			       	booking.setId((int)incidentSeries.getStartDateTime().getTime());
        		        	booking.setCourtName(incidentSeries.getInstanceName().toLowerCase());
        		        	booking.setBookingDate(bookingDate);
        		        	booking.setStartTime(incidentSeries.getStartDateTime());
        		        	booking.setEndTime(incidentSeries.getEndtDateTime());
        		        	booking.setCustomerName("<<< NO BOOKING >>>");
        		        	booking.setCost(0);
        		        	booking.setCompleted(false);
        		        	booking.setNoBooking(true);
        		        	
        		        	
        		        	setSeriesImages(booking, incidentSeries);
        		        	
        		        	//Uncomment to go to old code
        		        	bookings.add(booking);
        		        	
        		        	//New code can be removed
        		        	/*Booking adjusentBooking = getAdjusentBooking(incidentSeries,bookings);
        		        	setCostAndName(adjusentBooking, booking,incidentSeries );
        		        	
        		        	if (adjusentBooking == null){
        		        		bookings.add(booking);
        		        	}
        		        	else{
        		        		updateBooking(adjusentBooking);
        		        		//adjusentBooking.setNoBooking(true);
        		        	}
        		        	*/
        		        	//End of new code to be removed
        		        	
        				}
        				
        				setActualPlayTime(bookings, incidentSeries);
         			}
        		}
        	}
        }
        
 		return bookings;
	}
	
	private void setCostAndName(Booking adjusentBooking, Booking noBooking, IncidentSeries incidentSeries ){
		
	
			if (adjusentBooking != null && adjusentBooking.getCost() != 0){
				
				long milis = adjusentBooking.getEndTime().getTime() - adjusentBooking.getStartTime().getTime();
				
				long minutes = ((milis / 1000) / 60);
				
				minutes++;
				
				long bookingGapHours = (minutes  / 60);
				long bookingMinutes =  (minutes  % 60);
				
				//noBooking.setCustomerName(adjusentBooking.getCustomerName());
				
				long cost = 350 *  bookingGapHours;
				
				if (bookingMinutes > 12 && bookingMinutes < 20){
					cost += 100;
				}
				
				if (bookingMinutes >= 20){
					cost += 200;
				}
				adjusentBooking.setCost((int) cost);
			}
		}

	
	private Booking getAdjusentBooking(IncidentSeries incidentSeries, List<Booking> bookings){
		
		Booking adjusentBooking = null;
		
		Calendar calBookEnd = Calendar.getInstance();
		
		for (Booking booking : bookings){
			
			calBookEnd.setTime(booking.getEndTime());
			calBookEnd.add(Calendar.MINUTE, -1);
			
			//continue from previous booking
			if (incidentSeries.getStartDateTime().getTime() - calBookEnd.getTime().getTime() > 0 
					&& incidentSeries.getInstanceName().toLowerCase().equals(booking.getCourtName().toLowerCase())){
			
				long minutesGap = ((incidentSeries.getStartDateTime().getTime() - calBookEnd.getTime().getTime()) / 1000) / 60;
				
				if (minutesGap <= 11){
					adjusentBooking = booking;
					booking.setEndTime(incidentSeries.getEndtDateTime());
				}
			}
			
			//early start from next booking
			if (booking.getStartTime().getTime() - incidentSeries.getEndtDateTime().getTime()  > 0 
					&& incidentSeries.getInstanceName().toLowerCase().equals(booking.getCourtName().toLowerCase())){
			
				long minutesGap = ((booking.getStartTime().getTime() - incidentSeries.getEndtDateTime().getTime()) / 1000) / 60;
				
				if (minutesGap <= 11){
					adjusentBooking = booking;
					booking.setStartTime(incidentSeries.getStartDateTime());
				}
			}

		}
		
		/*if (adjusentBooking !=null && adjusentBooking.getCost() == 0){
			adjusentBooking = null;
		}*/
		
		return adjusentBooking;
	}
	
	private boolean isBookingAvailableForIncident(List<Booking> bookings, IncidentSeries incidentSeries){
		Calendar calBookStaart = Calendar.getInstance();
		
		for (Booking booking : bookings){
			calBookStaart.setTime(booking.getStartTime());
			calBookStaart.add(Calendar.MINUTE, -1);
			
			if (incidentSeries.getStartDateTime().getTime() >= calBookStaart.getTime().getTime()
					&& incidentSeries.getEndtDateTime().getTime() <= booking.getEndTime().getTime()
					&& incidentSeries.getInstanceName().toLowerCase().equals(booking.getCourtName().toLowerCase())){
				
				
				setSeriesImages(booking, incidentSeries);
				
				/*
				if (booking.getActualStartTime() == null || (incidentSeries.getStartDateTime().getTime() < booking.getActualStartTime().getTime())){
					booking.setActualStartTime(incidentSeries.getStartDateTime());
				}
				
				if (booking.getActualEndTime() == null || (incidentSeries.getEndtDateTime().getTime() > booking.getActualEndTime().getTime())){
					booking.setActualEndTime(incidentSeries.getEndtDateTime());
				}
				
				booking.setPlayed(true);
				*/
				return true;
			}
				
		}
		return false;
	}
	
	private List<String> setSeriesImages(Booking booking, IncidentSeries incidentSeries){
		
		List<String> imageLinks = new ArrayList<String>();
		
		List<Incident> incidents = incidentSeries.getIncidents();
		
		for (Incident incident : incidents){
			imageLinks.add(incident.getPathToImage());
		}
		
		booking.getImageLinks().addAll(imageLinks);
		
		return imageLinks;
	}
	
	
	private void setActualPlayTime(List<Booking> bookings, IncidentSeries incidentSeries){
		Calendar calBookStaart = Calendar.getInstance();
		
		for (Booking booking : bookings){
			calBookStaart.setTime(booking.getStartTime());
			calBookStaart.add(Calendar.MINUTE, -1);
			
			if (incidentSeries.getStartDateTime().getTime() >= calBookStaart.getTime().getTime()
					&& incidentSeries.getEndtDateTime().getTime() <= booking.getEndTime().getTime()
					&& incidentSeries.getInstanceName().toLowerCase().equals(booking.getCourtName().toLowerCase())){
				
				if (booking.getActualStartTime() == null || (incidentSeries.getStartDateTime().getTime() < booking.getActualStartTime().getTime())){
					booking.setActualStartTime(incidentSeries.getStartDateTime());
				}
				
				if (booking.getActualEndTime() == null || (incidentSeries.getEndtDateTime().getTime() > booking.getActualEndTime().getTime())){
					booking.setActualEndTime(incidentSeries.getEndtDateTime());
				}
				
				booking.setPlayed(true);
			}				
		}
	}

	
	public void updateBooking(Booking booking){
		bookingData.updateBooking(booking);
	}
	
	public void deleteBookings(List<Booking> bookings, int tenantid){
		bookingData.deleteBookings(bookings, tenantid);		
	}
	
	public void addBooking(Booking booking){
		bookingData.addBooking(booking);
	}

}
