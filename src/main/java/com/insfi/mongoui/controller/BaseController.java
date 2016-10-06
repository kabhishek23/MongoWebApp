package com.insfi.mongoui.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.ConnectionException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.DocumentException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.InvalidHTTPRequestException;
import com.insfi.mongoui.services.AuthService;

/**
 * 
 * @author abhishek
 *
 */
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

		errorResponse.put("error", true);
		errorResponse.put("errorCode", e.getErrorCode());
		errorResponse.put("message", e.getMessage());

		logger.error(errorResponse, e);

		return errorResponse;
	}

	protected static class ErrorTemplate {
		public static JSONObject execute(Logger logger, ResponseCallback callback) {
			JSONObject response = new JSONObject();

			try {
				response = (JSONObject) callback.execute();
			} catch (ConnectionException e) {
				response = prepareErrorResponse(logger, e);
			} catch (DatabaseException e) {
				response = prepareErrorResponse(logger, e);
			} catch (CollectionException e) {
				response = prepareErrorResponse(logger, e);
			} catch (DocumentException e) {
				response = prepareErrorResponse(logger, e);
			} catch (ApplicationException e) {
				response = prepareErrorResponse(logger, e);
			} catch (Exception e) {
				ApplicationException ex = new ApplicationException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage(),
						e.getCause());
				response = prepareErrorResponse(logger, ex);
			}

			return response;

		}

	}

	protected static class ResponseTemplate {

		public JSONObject execute(Logger logger, String connectionId, HttpServletRequest request,
				ResponseCallback callback) {
			return execute(logger, connectionId, request, callback, true);
		}

		public JSONObject execute(Logger logger, String connectionId, HttpServletRequest request,
				ResponseCallback callback, boolean wrapResult) {

			JSONObject response = validateConnection(connectionId, logger, request);

			if (response != null) {
				return response;
			}

			return ErrorTemplate.execute(logger, callback);
		}
	}

	protected interface ResponseCallback {
		public Object execute() throws Exception;
	}
}
