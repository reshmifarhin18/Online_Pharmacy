package com.jsp.exception;

public class CantCancelException extends RuntimeException{
	public CantCancelException(String message) {
		super();
		this.message = message;
	}

	String message;
	

}
