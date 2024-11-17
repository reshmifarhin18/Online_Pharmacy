package com.jsp.exception;

public class FailedResetPasswordException extends RuntimeException{
public FailedResetPasswordException(String message) {
		super();
		this.message = message;
	}

private String message;

}
