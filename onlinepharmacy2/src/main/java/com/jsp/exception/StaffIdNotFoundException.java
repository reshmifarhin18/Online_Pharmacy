package com.jsp.exception;

public class StaffIdNotFoundException extends RuntimeException {
	public StaffIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	String message;

}
