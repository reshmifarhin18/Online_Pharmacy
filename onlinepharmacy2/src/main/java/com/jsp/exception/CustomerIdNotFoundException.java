package com.jsp.exception;

public class CustomerIdNotFoundException extends RuntimeException{
	public CustomerIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;
	

}
