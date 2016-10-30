package com.insfi.mongoui.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.serviceImpl.DatabaseServiceImpl;
import com.insfi.mongoui.services.DatabaseService;

@Controller
public class DatabaseController extends BaseController {

	private final static Logger logger = Logger.getLogger(DatabaseController.class);

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public JSONObject getDatabaseList(@RequestParam("connectionId") String connectionId,
			final HttpServletRequest request) throws ApplicationException {

		JSONObject response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {
				DatabaseService dbService = new DatabaseServiceImpl(connectionId);

				List<String> dbList = dbService.getDbList();

				JSONObject dbListResponse = new JSONObject();
				dbListResponse.put("databases", dbList);

				return dbListResponse;
			}
		});

		return response;
	}

	@RequestMapping(value = "/{dbName}", method = RequestMethod.PUT)
	public JSONObject createDatabase(@RequestParam("dbName") final String dbName,
			@RequestParam("connectionId") final String connectionId, HttpServletRequest request) {
		JSONObject response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {
				DatabaseService dbService = new DatabaseServiceImpl(connectionId);
				String result = dbService.createDb(dbName);
				return new JSONObject().put("message", result);
			}
		});
		return response;
	}

	@RequestMapping(value = "/{dbName}", method = RequestMethod.DELETE)
	public JSONObject deleteDatabase(@RequestParam("dbName") final String dbName,
			@RequestParam("connectionId") final String connectionId, HttpServletRequest request) {
		JSONObject response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {
				DatabaseService dbService = new DatabaseServiceImpl(connectionId);
				String result = dbService.dropDb(dbName);
				return new JSONObject().put("message", result);
			}
		});
		return response;
	}

	@RequestMapping(value = "/{dbName}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject executeQuery(@RequestParam("dbName") final String dbName,
			@RequestParam("connectionId") final String connectionId, @RequestBody JSONObject payload,
			HttpServletRequest request) {
		JSONObject response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});

		return response;
	}
}
