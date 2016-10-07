package com.insfi.mongoui.services;

import java.util.Set;

import org.json.JSONObject;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.ConnectionProperties;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.mongodb.Mongo;

public interface AuthService {

	JSONObject authenticate(ConnectionDetails connectionDetails, Set<String> connectionPool)
			throws ApplicationException;

	ConnectionProperties getConnectionProperties(String connectionId) throws ApplicationException;

	Mongo getMongoInstace(String connectionId) throws ApplicationException;

	void disconnect(String connectionId) throws ApplicationException;

}
