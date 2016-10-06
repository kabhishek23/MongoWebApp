package com.insfi.mongoui.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public JSONObject authenticate() {

		JSONObject response = ErrorTemplate.execute(logger, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});

		return response;
	}

}
