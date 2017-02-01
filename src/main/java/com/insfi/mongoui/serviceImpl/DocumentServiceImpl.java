package com.insfi.mongoui.serviceImpl;

import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.DocumentException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.services.DatabaseService;
import com.insfi.mongoui.services.DocumentService;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author abhishek
 *
 */

@Service
public class DocumentServiceImpl implements DocumentService {

	private DatabaseService databaseService;

	private MongoClient mongoClient;

	public DocumentServiceImpl() {
	}

	@Override
	public JSONObject executeQuery(String dbName, String collectionName, String command, String query,
			String projection, String sortBy, int limit, int skip)
			throws ApplicationException, CollectionException, DocumentException, JSONException {

		try {
			MongoCollection<Document> dbCollection = getCollection(dbName, collectionName);

			return null;

		} catch (MongoException e) {
			throw new DocumentException(ErrorCode.QUERY_EXECUTION_EXCEPTION, e.getMessage());
		}

	}

	@Override
	public String insertDocument(String dbName, String collectionName, DBObject document)
			throws DatabaseException, CollectionException, DocumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateDocument(String dbName, String collectionName, String _id, DBObject newData)
			throws DatabaseException, CollectionException, DocumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDocument(String dbName, String collectionName, String _id)
			throws DatabaseException, CollectionException, DocumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get Collection to execute Query
	 * 
	 * @param dbName
	 * @param collectionName
	 * @return
	 * @throws DatabaseException
	 * @throws CollectionException
	 * @throws DocumentException
	 */
	private MongoCollection<Document> getCollection(String dbName, String collectionName)
			throws DatabaseException, CollectionException, DocumentException {

		if (dbName == null) {
			throw new DatabaseException(ErrorCode.EMPTY_DB_NAME, "Database name is null");
		}

		if (dbName.equals("")) {
			throw new DatabaseException(ErrorCode.EMPTY_DB_NAME, "Database name is empty");
		}

		try {
			List<String> dbList = databaseService.getDbList();
			if (!dbList.contains(dbName)) {
				throw new DatabaseException(ErrorCode.DB_NOT_EXIST, "No database found with name " + dbName);
			}

			MongoDatabase db = mongoClient.getDatabase(dbName);

			if (collectionName == null) {
				throw new CollectionException(ErrorCode.EMPTY_COLLECTION_NAME, "Collection name is null");
			}

			if (collectionName.equals("")) {
				throw new CollectionException(ErrorCode.EMPTY_COLLECTION_NAME, "Collection name is Empty");
			}

			MongoCollection<Document> dbCollection = db.getCollection(collectionName);

			return dbCollection;

		} catch (MongoException e) {
			throw new DocumentException(ErrorCode.QUERY_EXECUTION_EXCEPTION, e.getMessage());
		}

	}

}
