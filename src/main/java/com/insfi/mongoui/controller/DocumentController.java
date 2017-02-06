package com.insfi.mongoui.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.InvalidCommandException;
import com.insfi.mongoui.serviceImpl.DocumentServiceImpl;
import com.insfi.mongoui.services.DocumentService;

@RestController
@RequestMapping(path = "/{db}/{collection}/documents")
public class DocumentController extends BaseController {

	private final static Logger logger = Logger.getLogger(DocumentController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String executeQuery(@PathVariable("db") final String dbName,
			@PathVariable("collection") final String collectionName,
			@RequestParam("connectionId") final String connectionId, @RequestParam("command") final String command,
			@RequestParam("query") final String query,
			@RequestParam(value = "projection", required = false) final String projection,
			@RequestParam(value = "sort", required = false) final String sortBy,
			@RequestParam(value = "limit", defaultValue = "50") final String limit,
			@RequestParam(value = "skip", defaultValue = "0") final String skip, HttpServletRequest request) {

		String response = new ResponseTemplate().execute(logger, connectionId, request, new ResponseCallback() {

			@Override
			public Object execute() throws Exception {

				if (command.isEmpty()) {
					throw new InvalidCommandException(ErrorCode.INVALID_MONGO_COMMAND_EXCEPTION, "Command is Empty");
				}

				DocumentService documentService = new DocumentServiceImpl(connectionId);

				int numOfDocuments = Integer.parseInt(limit);
				int numOfDocumentsToSkip = Integer.parseInt(skip);

				JSONObject documents = documentService.executeQuery(dbName, collectionName, command, query, projection,
						sortBy, numOfDocuments, numOfDocumentsToSkip);

				return documents;

			}
		});

		return response;

	}

}
