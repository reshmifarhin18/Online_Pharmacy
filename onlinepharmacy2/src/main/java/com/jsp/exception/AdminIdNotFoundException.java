package com.jsp.exception;

public class AdminIdNotFoundException extends RuntimeException{
	public AdminIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;
	

	

}
