package com.insfi.mongoui.services;

import java.util.Set;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.MongoConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.mongodb.MongoClient;

public interface AuthService {

	String authenticate(ConnectionDetails connectionDetails, Set<String> connectionPool) throws ApplicationException;

	MongoConnectionDetails getMongoConnectionDetails(String connectionId) throws ApplicationException;

	MongoClient getMongoClientInstace(String connectionId) throws ApplicationException;

	void disconnect(String connectionId) throws ApplicationException;

}
