package com.insfi.mongoui.exceptions;

public class DatabaseException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException(String errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}

	public DatabaseException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
		// TODO Auto-generated constructor stub
	}

}
