package com.jsp.exception;

public class AddressNotFoundException extends RuntimeException{
	public AddressNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;

}
