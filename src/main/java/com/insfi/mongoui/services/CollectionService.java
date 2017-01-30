package com.insfi.mongoui.services;

import java.util.Set;

import org.json.JSONException;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.CollectionException;
import com.insfi.mongoui.exceptions.DatabaseException;

public interface CollectionService {

	public Set<String> getCollections(String dbName) throws DatabaseException, CollectionException;

	public String insertCollection(String dbName, String newCollectionName, boolean capped, int size, int maxDocs,
			boolean autoIndexId) throws DatabaseException, CollectionException;

	public String updateCollection(String dbName, String newCollectionName, boolean capped, int size, int maxDocs,
			boolean autoIndexId) throws DatabaseException, CollectionException;

	public String dropCollection(String dbName, String collectionName) throws DatabaseException, ApplicationException;

	public String getStats(String dbName, String collectionName)
			throws DatabaseException, CollectionException, JSONException;
}
