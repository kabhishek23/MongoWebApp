package com.insfi.mongoui.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insfi.mongoui.credentials.AuthenticationMechanismFactory;
import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.MongoConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.MongoConnectionException;
import com.insfi.mongoui.services.AuthService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author abhishek
 *
 */
@Service
public class AuthServiceImpl implements AuthService {

	private static AuthServiceImpl AUTH_SERVICE;

	private final AtomicLong SUCCESSFUL_CONNECTIONS_COUNT = new AtomicLong();

	private Map<String, Collection<MongoConnectionDetails>> connectionsCollection = new ConcurrentHashMap<String, Collection<MongoConnectionDetails>>();

	@Autowired
	private AuthenticationMechanismFactory credentialManager;

	private AuthServiceImpl() {
		// TODO Auto-generated constructor stub	
	}

	@Override
	public String authenticate(ConnectionDetails connectionDetails, Set<String> connectionPool)
			throws ApplicationException {
		sanitizeConnectionDetails(connectionDetails);
		String connectionDetailsHashCode = String.valueOf(connectionDetails.hashCode());

		Collection<MongoConnectionDetails> mongoConnectionDetailsList = connectionsCollection
				.get(connectionDetailsHashCode);

		if (connectionPool != null && mongoConnectionDetailsList != null) {
			for (MongoConnectionDetails mongoConnectionDetails : mongoConnectionDetailsList) {
				if (connectionPool.contains(mongoConnectionDetails.getConnectionId())
						&& connectionDetails.equals(mongoConnectionDetails.getConnectionDetails())) {
					return mongoConnectionDetails.getConnectionId();
				}
			}
		}

		MongoClient mongoClient = getMongoClient(connectionDetails);

		String connectionId = SUCCESSFUL_CONNECTIONS_COUNT.incrementAndGet() + "_" + connectionDetailsHashCode;

		if (mongoConnectionDetailsList == null) {
			mongoConnectionDetailsList = new ArrayList<MongoConnectionDetails>(1);
			connectionsCollection.put(connectionDetailsHashCode, mongoConnectionDetailsList);
		}

		mongoConnectionDetailsList.add(new MongoConnectionDetails(connectionId, mongoClient, connectionDetails));

		return connectionId;
	}

	private MongoClient getMongoClient(ConnectionDetails connectionDetails) throws ApplicationException {
		MongoClient mongoClient = null;

		// server List
		List<ServerAddress> serverAddressList = getServerAddressList(connectionDetails);

		// MongoCredentials
		MongoCredential credentials = getMongoCredentials(connectionDetails);

		// MongoOptions
		// TODO : Develop Connection manager to handle all sorts of auth
		// services on mongo
		Builder mongoOptions = new MongoClientOptions.Builder();
		mongoOptions.connectTimeout(2000);

		mongoClient = new MongoClient(serverAddressList, Arrays.asList(credentials), mongoOptions.build());

		// get Authenticated db list
		getAuthenticatedDatabases(mongoClient, connectionDetails);

		return mongoClient;
	}

	/**
	 * Create MongoCredentials
	 * 
	 * @param connectionDetails
	 * @return
	 * @throws ApplicationException
	 */
	private MongoCredential getMongoCredentials(ConnectionDetails connectionDetails) throws ApplicationException {

		MongoCredential mongoCredentials = credentialManager.createCredentials(connectionDetails.getUsername(),
				connectionDetails.getDbNames(), connectionDetails.getPassword());

		return mongoCredentials;

	}

	@Deprecated
	private void authenticateDatabases(MongoClient mongoClient, ConnectionDetails connectionDetails)
			throws ApplicationException {
		String dbNames = connectionDetails.getDbNames();
		String[] dbNamesList = dbNames.split(",");
		String username = connectionDetails.getUsername();
		String password = connectionDetails.getPassword();

		for (String dbName : dbNamesList) {
			boolean isDbAuthenticated = isDbAuthenticated(mongoClient, dbName);
			if (isDbAuthenticated)
				connectionDetails.addToAuthenticatedDbList(dbName);
		}

		if (connectionDetails.getAuthenticatedDbList().isEmpty()) {
			throw new ApplicationException(("".equals(username) && "".equals(password)) ? ErrorCode.NEED_AUTHORISATION
					: ErrorCode.INVALID_USER, "Invalid UserName or Password");
		}

	}

	/**
	 * TODO: Get Authenticated List of Databases authorized to user
	 * 
	 * @param mongoClient
	 * @param connectionDetails
	 */
	private void getAuthenticatedDatabases(MongoClient mongoClient, ConnectionDetails connectionDetails) {

		String dbName = connectionDetails.getDbNames();
		connectionDetails.addToAuthenticatedDbList(dbName);

		// get list of databases if user is admin
		if (dbName.equals("admin")) {
			MongoCursor<String> dbListItr = mongoClient.listDatabaseNames().iterator();
			while (dbListItr.hasNext()) {
				connectionDetails.addToAuthenticatedDbList(dbListItr.next());
			}
		}

	}

	@Deprecated
	private boolean isDbAuthenticated(MongoClient mongoClient, String dbName) {
		boolean isAuthenticated = false;
		dbName = dbName.trim();

		MongoDatabase db = mongoClient.getDatabase(dbName);

		try {
			db.listCollectionNames();
			isAuthenticated = true;
		} catch (MongoException me) {
			isAuthenticated = false;
		}
		return isAuthenticated;
	}

	/**
	 * Get Server Address List TODO : Add Feature to connect to replica set
	 * 
	 * @param connectionDetails
	 * @return
	 * @throws ApplicationException
	 */
	private List<ServerAddress> getServerAddressList(ConnectionDetails connectionDetails) throws ApplicationException {
		List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();

		String serverAddressStr = connectionDetails.getHostIp();

		if (serverAddressStr == null || "".equals(serverAddressStr)) {
			throw new MongoConnectionException(ErrorCode.EMPTY_HOST_ADDRESS, "Empty Server Address");
		}

		ServerAddress serverAddress = new ServerAddress(serverAddressStr, connectionDetails.getPort());

		serverAddressList = Arrays.asList(serverAddress);

		return serverAddressList;
	}

	@Override
	public MongoConnectionDetails getMongoConnectionDetails(String connectionId) throws ApplicationException {
		String[] connectionExtract = connectionId.split("_");
		if (connectionExtract.length != 2) {
			throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
		}

		String connectionHashCode = String.valueOf(connectionExtract[1]);
		Collection<MongoConnectionDetails> mongoConnectionDetailsList = connectionsCollection.get(connectionHashCode);

		if (mongoConnectionDetailsList == null) {
			throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
		}

		for (MongoConnectionDetails mongoConnectionDetails : mongoConnectionDetailsList) {
			if (connectionId.equals(mongoConnectionDetails.getConnectionId())) {
				return mongoConnectionDetails;
			}
		}

		throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
	}

	@Override
	public MongoClient getMongoClientInstace(String connectionId) throws ApplicationException {
		String[] connectionExtract = connectionId.split("_");
		if (connectionExtract.length != 2) {
			throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
		}

		String connectionHashCode = String.valueOf(connectionExtract[1]);
		Collection<MongoConnectionDetails> mongoConnectionDetailsList = connectionsCollection.get(connectionHashCode);

		if (mongoConnectionDetailsList == null) {
			throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
		}

		for (MongoConnectionDetails mongoConnectionDetails : mongoConnectionDetailsList) {
			if (connectionId.equals(mongoConnectionDetails.getConnectionId())) {
				return mongoConnectionDetails.getMongoClient();
			}
		}

		throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
	}

	@Override
	public void disconnect(String connectionId) throws ApplicationException {
		String[] connectionExtract = connectionId.split("_");
		if (connectionExtract.length != 2) {
			throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
		}

		String connectionHashCode = String.valueOf(connectionExtract[1]);
		Collection<MongoConnectionDetails> mongoConnectionDetailsList = connectionsCollection.get(connectionHashCode);

		if (mongoConnectionDetailsList == null) {
			throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
		}

		Iterator<MongoConnectionDetails> mongoConnectionDetailsIterator = mongoConnectionDetailsList.iterator();

		while (mongoConnectionDetailsIterator.hasNext()) {
			MongoConnectionDetails mongoConnectionDetails = mongoConnectionDetailsIterator.next();
			if (connectionId.equals(mongoConnectionDetails.getConnectionId())) {
				mongoConnectionDetails.getMongoClient().close();
				mongoConnectionDetailsIterator.remove();
				return;
			}
		}

		throw new ApplicationException(ErrorCode.INVALID_CONNECTION, "Invalid Connection");
	}

	private void sanitizeConnectionDetails(ConnectionDetails connectionDetails) {
		if ("localhost".equals(connectionDetails.getHostIp())) {
			connectionDetails.setHostIp("127.0.0.1");
		}
	}

	public static AuthService getInstance() {
		if (AUTH_SERVICE == null) {
			AUTH_SERVICE = new AuthServiceImpl();
		}
		return AUTH_SERVICE;
	}

}
