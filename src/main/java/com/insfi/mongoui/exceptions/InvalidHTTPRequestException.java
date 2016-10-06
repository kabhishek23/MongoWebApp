package com.insfi.mongoui.exceptions;

public class InvalidHTTPRequestException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public InvalidHTTPRequestException(String errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}

	public InvalidHTTPRequestException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}
}