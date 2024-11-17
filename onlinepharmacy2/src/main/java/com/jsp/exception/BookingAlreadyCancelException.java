package com.jsp.exception;

public class BookingAlreadyCancelException extends RuntimeException {
	
  public BookingAlreadyCancelException(String message) {
		super();
		this.message = message;
	}

private String message;

}
