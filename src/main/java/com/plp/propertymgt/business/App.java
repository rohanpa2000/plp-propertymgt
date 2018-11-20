package com.plp.propertymgt.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.plp.propertymgt.data.mysql.BookingDataMySql;
import com.plp.propertymgt.business.BookingBusiness;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException
    {
    	BookingDataMySql bookingdb = new BookingDataMySql();
    	BookingBusiness bookingBusiness = new BookingBusiness();
    	
    	/*
    	//Date bookingDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20180212" );
    	
    	//{id=8.0, courtName=court1, bookingDate=Feb 12, 2018 12:00:00 AM, 
    	//startTime=Feb 12, 2018 01:00:00 AM, endTime=Feb 12, 2018 12:00:00 AM, 
    	//customer=Chathurka, cost=100.0, isCompleted=false, isDeleted=false}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");

    	Booking booking = new Booking();
    	
    	booking.setId(8);
    	booking.setCourtName("court1");
    	booking.setBookingDate(sdf.parse("Feb 12, 2018 12:00:00 AM"));
    	booking.setStartTime(sdf.parse("Feb 12, 2018 05:00:00 PM"));
    	booking.setEndTime(sdf.parse("Feb 14, 2018 06:00:00 PM"));
    	booking.setCustomerName("Obama");
    	booking.setCost(55);
    	booking.setCompleted(false);
    	
    	bookingdb.addBooking(booking);
    	*/
    	
        try {
        		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    			Date startDate = df.parse("06/11/2018 00:00");
    			
    			//System.out.println(bookingBusiness.getLastIncidents());
    			
    			System.out.println(bookingBusiness.getBookings(startDate));
    			
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        
    	
    	
    	//System.out.println(bookings);
    }
}
