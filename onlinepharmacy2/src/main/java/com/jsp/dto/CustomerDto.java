package com.jsp.dto;

import java.util.List;

import com.jsp.entity.Address;
import com.jsp.entity.Booking;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class CustomerDto {
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<BookingDto> getBookingDto() {
		return bookingDto;
	}

	public void setBookingDto(List<BookingDto> bookingDto) {
		this.bookingDto = bookingDto;
	}

	public List<AddressDto> getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(List<AddressDto> addressDto) {
		this.addressDto = addressDto;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int customerId;
	    private String customerName;
	    private String customerEmail;
	    private String password;
	    private  long phoneNumber;
	    
	    @OneToMany 
	    private List<BookingDto> bookingDto;
		
	    @OneToMany
		private List<AddressDto> addressDto;
		

}
