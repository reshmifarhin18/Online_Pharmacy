package com.jsp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.BookingDao;
import com.jsp.dao.CustomerDao;
import com.jsp.dao.MedicineDao;
import com.jsp.dto.BookingDto;
import com.jsp.dto.CustomerDto;
import com.jsp.dto.MedicineDto;
import com.jsp.entity.Booking;
import com.jsp.entity.Customer;
import com.jsp.entity.Medicine;
import com.jsp.enums.BookingStatus;
import com.jsp.exception.BookingAlreadyCancelException;
import com.jsp.exception.BookingAlreadyDeliverdException;
import com.jsp.exception.CantCancelException;
import com.jsp.exception.CustomerIdNotFoundException;
import com.jsp.exception.MedicineIdNotFoundException;
import com.jsp.util.ResponseStructure;


@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private MedicineDao medicineDao;
	@Autowired
	ModelMapper modelMapper;
	 
	
	public ResponseEntity<ResponseStructure<BookingDto>> addBooking(int customerId,int medicineId,Booking booking){
		Customer dbCustomer=customerDao.findCustomer(customerId);
		if(dbCustomer!=null) {
			Medicine dbMedicines=medicineDao.findMedicineById(medicineId);
			if(dbMedicines!=null) {
				LocalDate orderdate=LocalDate.now();
				booking.setOrdarDate(orderdate);
				LocalDate expectedDate=LocalDate.now().plusDays(7);
				booking.setExpectedDate(expectedDate);
				booking.setBookingstatus(BookingStatus.ACTIVE);
				
				booking.setCustomer(dbCustomer);
				List<Medicine> list=new ArrayList<Medicine>();
				list.add(dbMedicines);
				booking.setMedicines(list);
				
				Booking dbBooking=bookingDao.saveBooking(booking);
//				Update the customer details
				List<Booking> bookings=dbCustomer.getBooking();
				bookings.add(dbBooking);
				dbCustomer.setBooking(bookings);
				customerDao.updateCustomer(dbCustomer.getCustomerId(),dbCustomer);
				BookingDto bookingDto=this.modelMapper.map(dbBooking, BookingDto.class);
				
				List<Medicine> medicines=new ArrayList<Medicine>();
				List<MedicineDto> medicineDtos=new ArrayList<MedicineDto>();
				for(Medicine med:medicines) {
					MedicineDto medicineDto=this.modelMapper.map(med, MedicineDto.class);
					medicineDtos.add(medicineDto);
				}
				
				bookingDto.setMedicinesDto(medicineDtos);
				bookingDto.setCustomerDto(this.modelMapper.map(dbBooking.getCustomer(), CustomerDto.class));
				
				
				ResponseStructure<BookingDto> structure=new ResponseStructure<>();
				structure.setMessage("Booking done successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(bookingDto);
				return new ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.CREATED);
				
				
				
			}else {
				
				throw new MedicineIdNotFoundException("Sorry failed to add Booking");
			}
			
		}else {
			throw new CustomerIdNotFoundException("Sorry failed to add Boooking");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<BookingDto>> cancelBooking(int bookingId){
		
		Booking dbBooking=bookingDao.findBooking(bookingId);
		LocalDate cantcancelDate=dbBooking.getExpectedDate().minusDays(2);
		if(dbBooking.getBookingstatus().equals(BookingStatus.CANCEL)) {
			
			throw new BookingAlreadyCancelException("Sorry failed to cancel booking");
		}
		else if(dbBooking.getBookingstatus().equals(BookingStatus.DELIVERD))
		{
			
			throw new BookingAlreadyDeliverdException("Sorry failed to cancel booking");
		} else if(LocalDate.now().equals(cantcancelDate)||LocalDate.now().isAfter(cantcancelDate)) {
			throw new CantCancelException("Sorry failed to cancel Booking");
		} else {
			      bookingDao.deleteBooking(bookingId);
			BookingDto bookingDto=this.modelMapper.map(dbBooking, BookingDto.class);
			
			List<Medicine> medicines=new ArrayList<Medicine>();
			List<MedicineDto> medicineDtos=new ArrayList<MedicineDto>();
			for(Medicine med:medicines) {
				MedicineDto medicineDto=this.modelMapper.map(med, MedicineDto.class);
				medicineDtos.add(medicineDto);
			}
			
			bookingDto.setMedicinesDto(medicineDtos);
			bookingDto.setCustomerDto(this.modelMapper.map(dbBooking.getCustomer(), CustomerDto.class));
			
			
			ResponseStructure<BookingDto> structure=new ResponseStructure<>();
			structure.setMessage("Booking Cancelled successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(bookingDto);
			return new ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.FORBIDDEN);
			
		}
		
	}

}
