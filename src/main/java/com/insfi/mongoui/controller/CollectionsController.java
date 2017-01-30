package com.insfi.mongoui.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insfi.mongoui.serviceImpl.CollectionServiceImpl;
import com.insfi.mongoui.services.CollectionService;

@RestController
public class CollectionsController extends BaseController {

	private final static Logger logger = Logger.getLogger(CollectionsController.class);

	@RequestMapping(path = "/{db}/collections", method = RequestMethod.GET)
	public String getCollectionList(@PathVariable("db") final String dbName,
			@RequestParam("connectionId") final String connectionId, HttpServletRequest request) {
		return ErrorTemplate.execute(logger, new ResponseCallback() {

			@Override
			public String execute() throws Exception {
				CollectionService collectionService = new CollectionServiceImpl(connectionId);

				Set<String> collectionList = collectionService.getCollections(dbName);

				JSONObject result = new JSONObject();
				result.put("db", dbName);
				result.put("collections", collectionList);

				return result.toString();
			}
		});
	}
}
