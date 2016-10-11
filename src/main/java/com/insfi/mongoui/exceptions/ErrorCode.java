package com.insfi.mongoui.exceptions;

public interface ErrorCode {
	
	public static final String UNKNOWN_EXCEPTION = "UNKNOWN_EXCEPTION";

	public static final String INVALID_SESSION = "INVALID_SESSION";
	public static final String INVALID_CONNECTION = "INVALID_CONNECTION";
	
	public static final String NEED_AUTHORISATION = "NEED_AUTHORISATION";
	public static final String INVALID_USER = "INVALID_USER";
	
	public static final String EMPTY_HOST_ADDRESS = "EMPTY_HOST_ADDRESS";
	public static final String INVALID_PORT_NUMBER = "INVALID_PORT_NUMBER";
	
	public static final String INVALID_ARGUMENT = "INVALID_ARGUMENT";
	
	public static final String MONGO_CONNECTION_EXCEPTION = "MONGO_CONNECTION_EXCEPTION";
	

}
