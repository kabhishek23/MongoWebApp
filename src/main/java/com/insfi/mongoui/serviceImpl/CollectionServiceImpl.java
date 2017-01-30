package com.insfi.mongoui.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.db.MongoConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.services.AuthService;
import com.insfi.mongoui.services.CollectionService;
import com.insfi.mongoui.services.DatabaseService;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * 
 * @author abhishek
 *
 */
@Service
public class CollectionServiceImpl implements CollectionService {

	private DatabaseService databaseService;

	private static AuthService AUTH_SERVICE;

	private MongoClient mongoClient;

	@Autowired
	public CollectionServiceImpl(AuthService auth) {
		AUTH_SERVICE = auth;
	}

	public CollectionServiceImpl() {
	}

	public CollectionServiceImpl(String connectionId) throws ApplicationException {
		mongoClient = AUTH_SERVICE.getMongoClientInstace(connectionId);
		databaseService = new DatabaseServiceImpl(connectionId);
	}

	@Override
	public Set<String> getCollections(String dbName) throws DatabaseException, CollectionException {
		if (dbName == null || dbName.equals("")) {
			throw new DatabaseException(ErrorCode.EMPTY_DB_NAME, "Empty Database Name");
		}

		try {
			List<String> dbList = databaseService.getDbList();

			if (!dbList.contains(dbName)) {
				throw new DatabaseException(ErrorCode.DB_NOT_EXIST, "Database [" + dbName + "] does not exist");
			}

			Set<String> collectionList = new HashSet<>();
			mongoClient.getDatabase(dbName).listCollectionNames().into(collectionList);

			return collectionList;

		} catch (MongoException e) {
			throw new CollectionException(ErrorCode.GET_COLLECTION_LIST_EXCEPTION, e.getMessage());
		}

	}

	@Override
	public String insertCollection(String dbName, String newCollectionName, boolean capped, int size, int maxDocs,
			boolean autoIndexId) throws DatabaseException, CollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCollection(String dbName, String newCollectionName, boolean capped, int size, int maxDocs,
			boolean autoIndexId) throws DatabaseException, CollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dropCollection(String dbName, String collectionName) throws DatabaseException, ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStats(String dbName, String collectionName)
			throws DatabaseException, CollectionException, JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
