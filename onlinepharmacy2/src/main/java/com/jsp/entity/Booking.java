package com.jsp.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
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

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	    private List<Medicine> medicines;
	    
	    @ManyToOne
	    private Customer customer;
		

}
