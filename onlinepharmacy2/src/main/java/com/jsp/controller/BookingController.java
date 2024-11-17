package com.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.dto.BookingDto;
import com.jsp.entity.Booking;
import com.jsp.service.BookingService;
import com.jsp.util.ResponseStructure;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<BookingDto>> addBooking(@RequestParam int customerId,@RequestParam int medicineId ,
			  @RequestBody Booking booking) {
		
		return bookingService.addBooking(customerId, medicineId, booking);
		
		
	}

}
