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

	public static final String INVALID_AUTH_MECHANISM = "INVALID_AUTH_MECHANISM";

	public static final String EMPTY_DB_NAME = "EMPTY_DB_NAME";
	public static final String DB_NOT_EXIST = "DB_NOT_EXIST";
	
	public static final String GET_COLLECTION_LIST_EXCEPTION = "GET_COLLECTION_LIST_EXCEPTION";

}
