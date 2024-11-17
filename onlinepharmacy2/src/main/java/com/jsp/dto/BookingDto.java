package com.jsp.dto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.enums.BookingStatus;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class BookingDto {
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getOrdarDate() {
		return ordarDate;
	}
	public void setOrdarDate(LocalDate ordarDate) {
		this.ordarDate = ordarDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public LocalDate getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(LocalDate expectedDate) {
		this.expectedDate = expectedDate;
	}
	public BookingStatus getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(BookingStatus bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	public List<MedicineDto> getMedicinesDto() {
		return medicinesDto;
	}
	public void setMedicinesDto(List<MedicineDto> medicinesDto) {
		this.medicinesDto = medicinesDto;
	}
	public CustomerDto getCustomerDto() {
		return customerDto;
	}
	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int bookingId;
	    private LocalDate ordarDate;
	    private int quantity;
	    private String paymentMode ;
	    private LocalDate expectedDate;
	    private BookingStatus bookingstatus;

	    @ManyToMany
	    private List<MedicineDto> medicinesDto;
	    @ManyToOne
	    
	    private CustomerDto customerDto;
		

}
