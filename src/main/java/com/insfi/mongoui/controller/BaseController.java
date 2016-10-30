package com.insfi.mongoui.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.DocumentException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.InvalidHTTPRequestException;
import com.insfi.mongoui.exceptions.MongoConnectionException;
import com.insfi.mongoui.services.AuthService;

/**
 * 
 * @author abhishek
 *
 */
@Resource
public class BaseController {

	protected static AuthService AUTH_SERVICE;

	/**
	 * Validate Connection for given ConnectionId
	 * 
	 * @param connectionId
	 * @param logger
	 * @param request
	 * @return
	 */
	protected static JSONObject validateConnection(String connectionId, Logger logger, HttpServletRequest request) {
		JSONObject response = null;

		HttpSession session = request.getSession();

		Set<String> connectionPool = (Set<String>) session.getAttribute("existingConnectionIdsInSession");

		if (connectionPool == null) {
			InvalidHTTPRequestException ex = new InvalidHTTPRequestException(ErrorCode.INVALID_SESSION,
					"Invalid Session");
			return prepareErrorResponse(logger, ex);
		}

		if (connectionId == null || !connectionPool.contains(connectionId)) {
			InvalidHTTPRequestException ex = new InvalidHTTPRequestException(ErrorCode.INVALID_CONNECTION,
					"Invalid Connection");
			return prepareErrorResponse(logger, ex);
		}

		return response;
	}

	/**
	 * Prepare Error Response
	 * 
	 * @param logger
	 * @param e
	 * @return
	 */
	protected static JSONObject prepareErrorResponse(Logger logger, ApplicationException e) {
		JSONObject errorResponse = new JSONObject();

		errorResponse.put("error", true);
		errorResponse.put("errorCode", e.getErrorCode());
		errorResponse.put("message", e.getMessage());

		logger.error(errorResponse, e);

		return errorResponse;
	}

	protected static JSONObject prepareSuccessResponse(ResponseCallback callback) throws Exception {
		JSONObject response = new JSONObject();
		
		response.put("success", true);
		response = (JSONObject) callback.execute();
		
		return response;
	}

	/**
	 * Error Template
	 * 
	 * @author abk6kor
	 *
	 */
	protected static class ErrorTemplate {
		public static JSONObject execute(Logger logger, ResponseCallback callback) {
			JSONObject response = new JSONObject();

			try {
				response = prepareSuccessResponse(callback);
			} catch (MongoConnectionException e) {
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

	/**
	 * Response Template
	 * 
	 * @author abk6kor
	 *
	 */
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
