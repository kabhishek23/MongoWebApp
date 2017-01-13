package com.insfi.mongoui.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.MongoConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.InvalidCommandException;
import com.insfi.mongoui.services.AuthService;
import com.insfi.mongoui.services.DatabaseService;
import com.mongodb.MongoClient;

/**
 * 
 * @author abhishek
 *
 */

@Service
public class DatabaseServiceImpl implements DatabaseService {

	private static AuthService AUTH_SERVICE;

	private ConnectionDetails connectionDetails;
	private MongoClient mongoClient;

	@Autowired
	public DatabaseServiceImpl(AuthService auth) {
		AUTH_SERVICE = auth;
	}

	public DatabaseServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public DatabaseServiceImpl(String connectionId) throws ApplicationException {
		MongoConnectionDetails mongoConnectionDetails = AUTH_SERVICE.getMongoConnectionDetails(connectionId);
		mongoClient = mongoConnectionDetails.getMongoClient();
		connectionDetails = mongoConnectionDetails.getConnectionDetails();
	}

	@Override
	public List<String> getDbList() throws DatabaseException {
		Set<String> authenticatedDbList = connectionDetails.getAuthenticatedDbList();
		return new ArrayList<String>(authenticatedDbList);
	}

	@Override
	public String getDbStats(String dbName) throws DatabaseException, JSONException {
		Document statsCommand = new Document("dbstats", 1);
		Document dbStats = mongoClient.getDatabase(dbName).runCommand(statsCommand);
		return dbStats.toJson();
	}

	@Override
	public String createDb(String dbName) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dropDb(String dbName) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject executeQuery(String dbName, String command, String queryStr, String keys, String sortBy,
			int limit, int skip) throws DatabaseException, JSONException, InvalidCommandException {
		// TODO Auto-generated method stub
		return null;
	}
}
