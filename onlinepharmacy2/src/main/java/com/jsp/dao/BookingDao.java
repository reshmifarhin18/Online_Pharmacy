package com.jsp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.entity.Booking;
import com.jsp.repo.BookingRepo;

@Repository
public class BookingDao {
	@Autowired
	private BookingRepo bookingRepo;
	
	public Booking saveBooking(Booking booking) {
		
		return bookingRepo.save(booking);
	}
	
	public Booking findBooking(int bookingId) {
		Optional<Booking> optional=bookingRepo.findById(bookingId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Booking deleteBooking(int bookingId) {
		Optional<Booking> optional=bookingRepo.findById(bookingId);
		if(optional.isPresent()) {
			bookingRepo.delete(optional.get());
			return optional.get();
		}
		return null;
	}

}
