package com.insfi.mongoui.services;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.InvalidCommandException;

public interface DatabaseService {

	public List<String> getDbList() throws DatabaseException;

	public String getDbStats(String dbName) throws DatabaseException, JSONException;

	public String createDb(String dbName) throws DatabaseException;

	public String dropDb(String dbName) throws DatabaseException;

	public JSONObject executeQuery(String dbName, String command, String queryStr, String keys, String sortBy,
			int limit, int skip) throws DatabaseException, JSONException, InvalidCommandException;

}
