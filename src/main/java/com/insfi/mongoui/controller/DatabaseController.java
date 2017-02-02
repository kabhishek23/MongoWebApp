package com.insfi.mongoui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.serviceImpl.DatabaseServiceImpl;
import com.insfi.mongoui.services.DatabaseService;

@RestController
public class DatabaseController extends BaseController {

	private final static Logger logger = Logger.getLogger(DatabaseController.class);

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String getDatabaseList(@RequestParam("connectionId") String connectionId, final HttpServletRequest request)
			throws ApplicationException {

		String response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

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

	@RequestMapping(value = "/{dbName}/stats", method = RequestMethod.GET)
	public String getDatabaseStats(@RequestParam("dbName") final String dbName,
			@RequestParam("connectionId") final String connectionId, HttpServletRequest request) {

		String response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {
				DatabaseService dbService = new DatabaseServiceImpl(connectionId);
				String statsStr = dbService.getDbStats(dbName);

				return statsStr;
			}
		});

		return response;

	}

	/**
	 * TODO : Implement this later when required
	 * 
	 * @param dbName
	 * @param connectionId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{dbName}", method = RequestMethod.PUT)
	public String createDatabase(@RequestParam("dbName") final String dbName,
			@RequestParam("connectionId") final String connectionId, HttpServletRequest request) {
		String response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {
				DatabaseService dbService = new DatabaseServiceImpl(connectionId);
				String result = dbService.createDb(dbName);
				return new JSONObject().put("message", result).toString();
			}
		});
		return response;
	}

	/**
	 * TODO : Implement this later when required
	 * 
	 * @param dbName
	 * @param connectionId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{dbName}", method = RequestMethod.DELETE)
	public String deleteDatabase(@RequestParam("dbName") final String dbName,
			@RequestParam("connectionId") final String connectionId, HttpServletRequest request) {
		String response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public String execute() throws Exception {
				DatabaseService dbService = new DatabaseServiceImpl(connectionId);
				String result = dbService.dropDb(dbName);
				return new JSONObject().put("message", result).toString();
			}
		});
		return response;
	}

	@RequestMapping(value = "/{dbName}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String executeQuery(@RequestParam("dbName") final String dbName,
			@RequestParam("connectionId") final String connectionId, @RequestBody JSONObject payload,
			HttpServletRequest request) {
		String response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public String execute() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});

		return response;
	}

}
