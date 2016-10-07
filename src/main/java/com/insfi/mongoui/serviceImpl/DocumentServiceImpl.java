package com.insfi.mongoui.serviceImpl;

import org.json.JSONException;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.DocumentException;
import com.insfi.mongoui.services.DocumentService;
import com.mongodb.DBObject;

/**
 * 
 * @author abhishek
 *
 */
public class DocumentServiceImpl implements DocumentService {

	@Override
	public JSONObject executeQuery(String dbName, String collectionName, String command, String queryStr, String keys,
			String sortBy, int limit, int skip, boolean allKeys)
			throws ApplicationException, CollectionException, DocumentException, JSONException {
		// TODO Auto-generated method stub
		return null;
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

}
