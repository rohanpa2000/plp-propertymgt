package com.plp.propertymgt.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.plp.propertymgt.config.Configuration;
import com.plp.propertymgt.data.BookingData;
import com.plp.propertymgt.model.Booking;

public class BookingDataMySql implements BookingData {
	
	static String url = Configuration.DB_URL;
	static String user = Configuration.DB_USER;
	static String pass = Configuration.DB_PWD;
	
	@Override
	public List<Booking> getBookings(Date bookingDate){
		
		
		List<Booking> bookings = new ArrayList<Booking>();
		
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_select_booking_by_booking_date(?)");
			
			seletMotionStatement.setDate(1,new java.sql.Date(bookingDate.getTime()));
			
			ResultSet results =  seletMotionStatement.executeQuery();
			
			while (results.next()) {
				Booking booking = new Booking();
				
				booking.setId(results.getInt("id"));
				booking.setCourtName(results.getString("court_name"));
				booking.setBookingDate(results.getTimestamp("booking_date"));
				booking.setStartTime(results.getTimestamp("start_time"));
				booking.setEndTime(results.getTimestamp("end_time"));
				booking.setCustomerName(results.getString("customer_name"));
				booking.setCost(results.getInt("cost"));
				booking.setCompleted(results.getBoolean("is_completed"));
				
				bookings.add(booking);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return bookings;
	}
	
	@Override
	public void updateBooking(Booking booking){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_update_booking_by_id(?,?,?,?,?,?,?,?)");
			
			seletMotionStatement.setInt(1, booking.getId());
			seletMotionStatement.setString(2, booking.getCourtName());
			seletMotionStatement.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
			seletMotionStatement.setTimestamp(4, new java.sql.Timestamp(booking.getStartTime().getTime()));
			seletMotionStatement.setTimestamp(5, new java.sql.Timestamp(booking.getEndTime().getTime()));
			seletMotionStatement.setString(6, booking.getCustomerName());
			seletMotionStatement.setInt(7, booking.getCost());
			seletMotionStatement.setBoolean(8, booking.isCompleted());
			
			seletMotionStatement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void deleteBookings(List<Booking> bookings){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
			
			String bookingIds = "";
			
			for (Booking booking : bookings)
				bookingIds += Integer.toString(booking.getId()) + ",";
			
			bookingIds = bookingIds.substring(0, bookingIds.length() - 1);
			
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_delete_bookings_by_ids(?)");
			
			System.out.println("bookingIds " + bookingIds);
			
			seletMotionStatement.setString(1, bookingIds);
			
			seletMotionStatement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void addBooking(Booking booking){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_insert_booking(?,?,?,?,?,?,?,?)");
			
			seletMotionStatement.setInt(1, booking.getId());
			seletMotionStatement.setString(2, booking.getCourtName());
			seletMotionStatement.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
			seletMotionStatement.setTimestamp(4, new java.sql.Timestamp(booking.getStartTime().getTime()));
			seletMotionStatement.setTimestamp(5, new java.sql.Timestamp(booking.getEndTime().getTime()));
			seletMotionStatement.setString(6, booking.getCustomerName());
			seletMotionStatement.setInt(7, booking.getCost());
			seletMotionStatement.setBoolean(8, booking.isCompleted());
			
			seletMotionStatement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
