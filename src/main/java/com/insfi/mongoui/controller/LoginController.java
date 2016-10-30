package com.insfi.mongoui.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.MongoConnectionException;
import com.insfi.mongoui.models.LoginFormModel;
import com.insfi.mongoui.services.AuthService;
import com.mongodb.MongoException;

@Controller
public class LoginController extends BaseController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public LoginController(AuthService auth) {
		AUTH_SERVICE = auth;
	}

	private static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public JSONObject authenticate(@ModelAttribute("loginForm") LoginFormModel loginForm, BindingResult result,
			HttpServletRequest request) {

		JSONObject response = ErrorTemplate.execute(logger, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {

				if ("".equals(loginForm.getHostIp()) || loginForm.getPort() == 0) {
					ApplicationException e = new ApplicationException(ErrorCode.EMPTY_HOST_ADDRESS,
							"Host Address cannot be Empty");
					return prepareErrorResponse(logger, e);
				}

				HttpSession session = request.getSession();
				Set<String> existingConnectionIdsInSession = (Set<String>) session
						.getAttribute("existingConnectionIdsInSession");

				// validate Port
				int port = 0;

				try {
					port = loginForm.getPort();
				} catch (NumberFormatException e) {
					throw new MongoConnectionException(ErrorCode.INVALID_PORT_NUMBER, "Invalid Port number");
				}

				ConnectionDetails connectionDetails = new ConnectionDetails(loginForm.getHostIp(), loginForm.getPort(),
						loginForm.getUsername(), loginForm.getPassword(), loginForm.getDatabase());

				String connectionId = null;

				try {

					connectionId = AUTH_SERVICE.authenticate(connectionDetails, existingConnectionIdsInSession);

				} catch (IllegalArgumentException e) {
					throw new MongoConnectionException(ErrorCode.INVALID_ARGUMENT, "Invalid Data Entered");
				} catch (MongoException me) {
					throw new MongoConnectionException(ErrorCode.MONGO_CONNECTION_EXCEPTION, me.getMessage());
				}

				if (existingConnectionIdsInSession == null) {
					existingConnectionIdsInSession = new HashSet<String>();
					session.setAttribute("existingConnectionIdsInSession", existingConnectionIdsInSession);
				}

				existingConnectionIdsInSession.add(connectionId);

				JSONObject response = new JSONObject();
				try {
					response.put("connectionId", connectionId);
				} catch (JSONException e) {
					logger.error(e);
				}
				return response;

			}
		});

		return response;
	}

}
