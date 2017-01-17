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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.MongoConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.MongoConnectionException;
import com.insfi.mongoui.models.LoginFormModel;
import com.insfi.mongoui.services.AuthService;
import com.mongodb.MongoException;

@RestController
public class LoginController extends BaseController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public LoginController(AuthService auth) {
		AUTH_SERVICE = auth;
	}

	private static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String authenticate(@ModelAttribute("loginForm") LoginFormModel loginForm, BindingResult result,
			HttpServletRequest request) {

		String response = ErrorTemplate.execute(logger, new ResponseCallback() {

			@Override
			public String execute() throws Exception {

				if ("".equals(loginForm.getHost()) || loginForm.getPort() == 0) {
					ApplicationException e = new ApplicationException(ErrorCode.EMPTY_HOST_ADDRESS,
							"Host Address cannot be Empty");
					throw e;
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

				ConnectionDetails connectionDetails = new ConnectionDetails(loginForm.getHost(), loginForm.getPort(),
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
				return response.toString();

			}
		});

		return response;
	}

	@RequestMapping(path = "/connection/details", method = RequestMethod.GET)
	public String getConnectionDetails(@RequestParam("connectionId") final String connectionId,
			final HttpServletRequest request) {
		return ErrorTemplate.execute(logger, new ResponseCallback() {

			@Override
			public String execute() throws Exception {
				MongoConnectionDetails mongoConnectionDetails = AUTH_SERVICE.getMongoConnectionDetails(connectionId);
				ConnectionDetails connectionDetails = mongoConnectionDetails.getConnectionDetails();

				ObjectMapper objectMapper = new ObjectMapper();

				return objectMapper.writeValueAsString(connectionDetails);
			}
		});
	}

}
