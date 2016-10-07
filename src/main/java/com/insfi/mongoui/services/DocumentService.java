package com.insfi.mongoui.services;

import org.json.JSONException;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.DocumentException;
import com.mongodb.DBObject;

public interface DocumentService {

	public JSONObject executeQuery(String dbName, String collectionName, String command, String queryStr, String keys,
			String sortBy, int limit, int skip, boolean allKeys)
			throws ApplicationException, CollectionException, DocumentException, JSONException;

	public String insertDocument(String dbName, String collectionName, DBObject document)
			throws DatabaseException, CollectionException, DocumentException;

	public String updateDocument(String dbName, String collectionName, String _id, DBObject newData)
			throws DatabaseException, CollectionException, DocumentException;

	public String deleteDocument(String dbName, String collectionName, String _id)
			throws DatabaseException, CollectionException, DocumentException;

}
