package com.insfi.mongoui.exceptions;

public class ConnectionException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionException(String errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}

	public ConnectionException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
		// TODO Auto-generated constructor stub
	}

}
