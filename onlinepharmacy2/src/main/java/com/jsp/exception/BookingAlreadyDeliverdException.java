package com.jsp.exception;

public class BookingAlreadyDeliverdException extends RuntimeException{
	public BookingAlreadyDeliverdException(String message) {
		super();
		this.message = message;
	}

	private String message;

}
