package com.insfi.mongoui.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.InvalidHTTPRequestException;
import com.insfi.mongoui.services.AuthService;

public class BaseController {
	protected static final AuthService AUTH_SERVICE = null;

	protected static JSONObject validateConnection(String connectionId, Logger logger, HttpServletRequest request) {
		JSONObject response = new JSONObject();

		HttpSession session = request.getSession();

		Set<String> connectionIdPoolInSession = (Set<String>) session.getAttribute("connectionIdPool");

		if (connectionIdPoolInSession == null) {
			InvalidHTTPRequestException ex = new InvalidHTTPRequestException(ErrorCode.INVALID_SESSION,
					"Invalid Session");
			return prepareErrorResponse(logger, ex);
		}

		if (connectionId == null || !connectionIdPoolInSession.contains(connectionId)) {
			InvalidHTTPRequestException ex = new InvalidHTTPRequestException(ErrorCode.INVALID_CONNECTION,
					"Invalid Connection");
			return prepareErrorResponse(logger, ex);
		}

		return response;
	}

	protected static JSONObject prepareErrorResponse(Logger logger, ApplicationException e) {
		JSONObject errorResponse = new JSONObject();
		
		return errorResponse;
	}

	protected static class ErrorTemplate {
		// TODO : complete body
	}

	protected static class ResponseTemplate {
		// TODO : complete body
	}

	protected interface ResponseCallback {
		public Object execute() throws Exception;
	}
}
