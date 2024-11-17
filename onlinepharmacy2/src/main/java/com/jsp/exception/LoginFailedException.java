package com.jsp.exception;

public class LoginFailedException  extends RuntimeException{
	public LoginFailedException(String message) {
		super();
		this.message = message;
	}

	private String message;

}
