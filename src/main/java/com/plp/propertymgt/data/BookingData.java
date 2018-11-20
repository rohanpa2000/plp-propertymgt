package com.plp.propertymgt.data;

import java.util.Date;
import java.util.List;

import com.plp.propertymgt.model.Booking;

public interface BookingData {
	 List<Booking> getBookings(Date bookingDate);
	 void updateBooking(Booking booking);
	 void addBooking(Booking booking);
	 void deleteBookings(List<Booking> bookings);
}
