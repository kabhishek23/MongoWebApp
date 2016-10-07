package com.insfi.mongoui.serviceImpl;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.services.CollectionService;

/**
 * 
 * @author abhishek
 *
 */
public class CollectionServiceImpl implements CollectionService {

	@Override
	public Set<String> getCollections(String dbName) throws DatabaseException, CollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject insertCollection(String dbName, String newCollectionName, boolean capped, int size, int maxDocs,
			boolean autoIndexId) throws DatabaseException, CollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject updateCollection(String dbName, String newCollectionName, boolean capped, int size, int maxDocs,
			boolean autoIndexId) throws DatabaseException, CollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject dropCollection(String dbName, String collectionName)
			throws DatabaseException, ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getStats(String dbName, String collectionName)
			throws DatabaseException, CollectionException, JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
