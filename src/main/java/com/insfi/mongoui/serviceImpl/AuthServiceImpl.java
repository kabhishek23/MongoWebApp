package com.insfi.mongoui.serviceImpl;

import java.util.Set;

import org.json.JSONObject;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.MongoConnectionProperties;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.services.AuthService;
import com.mongodb.Mongo;

/**
 * 
 * @author abhishek
 *
 */
public class AuthServiceImpl implements AuthService {

	@Override
	public JSONObject authenticate(ConnectionDetails connectionDetails, Set<String> connectionPool)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoConnectionProperties getConnectionProperties(String connectionId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mongo getMongoInstace(String connectionId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect(String connectionId) throws ApplicationException {
		// TODO Auto-generated method stub

	}

}
