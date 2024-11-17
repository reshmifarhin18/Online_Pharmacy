package com.jsp.exception;

public class MedicineIdNotFoundException extends RuntimeException{
	public MedicineIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;

}
