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
	public List<Booking> getBookings(Date bookingDate, int tenantId){
		
		
		List<Booking> bookings = new ArrayList<Booking>();
		
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_select_booking_by_booking_date(?,?)");
			
			seletMotionStatement.setDate(1,new java.sql.Date(bookingDate.getTime()));
			//seletMotionStatement.setDate(1,java.sql.Date.valueOf("2018-12-15"));
			seletMotionStatement.setInt(2, tenantId);
			
			ResultSet results =  seletMotionStatement.executeQuery();

			//System.out.println("bookingDate " + results);
			
			while (results.next()) {

				Booking booking = new Booking();
				
				booking.setId(results.getInt("booking_id"));
				booking.setTenantId(results.getInt("tenant_id"));
				booking.setMemberId(results.getInt("member_id"));
				booking.setItemId(results.getInt("item_id"));
				booking.setBookingDate(results.getTimestamp("booking_date"));			
				booking.setStartTime(results.getTimestamp("start_time"));
				booking.setEndTime(results.getTimestamp("end_time"));
				booking.setActualStartTime(results.getTimestamp("actual_start_time"));
				booking.setActualEndTime(results.getTimestamp("actual_end_time"));
				booking.setCost(results.getInt("cost"));
				booking.setCompleted(results.getBoolean("is_paid"));
				booking.setCustomerName(results.getString("member_display_name"));
				
				booking.setCourtName(results.getString("instance_name"));
				
				//System.out.println(new java.sql.Date(bookingDate.getTime()));
				//System.out.println(results.getString("in_date"));
				
				
				
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
					
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_update_booking_by_id(?,?,?,?,?,?,?,?,?,?,?)");
			
			seletMotionStatement.setInt(1, booking.getId());
			seletMotionStatement.setInt(2, booking.getTenantId());
			seletMotionStatement.setInt(3, booking.getMemberId());
			seletMotionStatement.setString(4, booking.getCustomerName());
			seletMotionStatement.setInt(5, booking.getItemId());
			seletMotionStatement.setTimestamp(6, new java.sql.Timestamp(booking.getStartTime().getTime()));
			seletMotionStatement.setTimestamp(7, new java.sql.Timestamp(booking.getEndTime().getTime()));
			
			
			if (booking.getActualStartTime() != null)
				seletMotionStatement.setTimestamp(8, new java.sql.Timestamp(booking.getActualStartTime().getTime()));
			else
				seletMotionStatement.setTimestamp(8, null);
			
			if (booking.getActualEndTime() != null)
				seletMotionStatement.setTimestamp(9, new java.sql.Timestamp(booking.getActualEndTime().getTime()));
			else
				seletMotionStatement.setTimestamp(9, null);			
			
			
			seletMotionStatement.setInt(10, booking.getCost());
			seletMotionStatement.setBoolean(11, booking.isCompleted());
			seletMotionStatement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}	
	
	@Override
	public void deleteBookings(List<Booking> bookings, int tenantid){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
			
			String bookingIds = "";
			
			for (Booking booking : bookings)
				bookingIds += Integer.toString(booking.getId()) + ",";
			
			bookingIds = bookingIds.substring(0, bookingIds.length() - 1);
			
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_delete_bookings_by_ids(?,?)");
			
			//System.out.println("bookingIds " + bookingIds);
			//System.out.println("tenantId " + tenantid);
			
			seletMotionStatement.setString(1, bookingIds);
			seletMotionStatement.setInt(2, tenantid);
			
			seletMotionStatement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void addBooking(Booking booking){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement seletMotionStatement = conn.prepareStatement("CALL sp_insert_booking(?,?,?,?,?,?,?,?,?)");
			
			seletMotionStatement.setInt(1, booking.getId());
			//seletMotionStatement.setString(2, booking.getCourtName());
			seletMotionStatement.setDate(2, new java.sql.Date(booking.getBookingDate().getTime()));
			seletMotionStatement.setTimestamp(3, new java.sql.Timestamp(booking.getStartTime().getTime()));
			seletMotionStatement.setTimestamp(4, new java.sql.Timestamp(booking.getEndTime().getTime()));
			seletMotionStatement.setString(5, booking.getCustomerName());
			seletMotionStatement.setInt(6, booking.getCost());
			seletMotionStatement.setBoolean(7, booking.isCompleted());
			seletMotionStatement.setInt(8, booking.getTenantId());
			seletMotionStatement.setInt(9, booking.getItemId());
			
			//System.out.println("finish pro");
			
			seletMotionStatement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
