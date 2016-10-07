package com.insfi.mongoui.exceptions;

public class InvalidCommandException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public InvalidCommandException(String errorCode, String message) {
		super(errorCode, message);
	}

	public InvalidCommandException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

}
