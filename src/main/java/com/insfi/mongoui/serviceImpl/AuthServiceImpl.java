package com.insfi.mongoui.serviceImpl;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONObject;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.ConnectionProperties;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.services.AuthService;
import com.mongodb.Mongo;

/**
 * 
 * @author abhishek
 *
 */
public class AuthServiceImpl implements AuthService {

	private static AuthService AUTH_SERVICE = new AuthServiceImpl();
	
	private final AtomicLong SUCCESSFUL_CONNECTIONS_COUNT = new AtomicLong();
	
//	private Collection<ConnectionDetails> connectionDetailsCollection = 

	private AuthServiceImpl() {
	}

	@Override
	public JSONObject authenticate(ConnectionDetails connectionDetails, Set<String> connectionPool)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionProperties getConnectionProperties(String connectionId) throws ApplicationException {
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

	private void sanitizeConnectionDetails(ConnectionDetails connectionDetails) {
		if ("localhost".equals(connectionDetails.getHostIp())) {
			connectionDetails.setHostIp("127.0.0.1");
		}
	}

	public static AuthService getInstance() {
		return AUTH_SERVICE;
	}

}
