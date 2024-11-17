package com.jsp.exception;

public class StoreIdNotFoundException extends RuntimeException {
	public StoreIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;
	

}
