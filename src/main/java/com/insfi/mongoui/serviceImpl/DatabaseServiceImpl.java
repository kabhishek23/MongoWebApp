package com.insfi.mongoui.serviceImpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.exceptions.InvalidCommandException;
import com.insfi.mongoui.services.DatabaseService;

/**
 * 
 * @author abhishek
 *
 */
public class DatabaseServiceImpl implements DatabaseService {

	@Override
	public List<String> getDbList() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getDbStats(String dbName) throws DatabaseException, JSONException {
		// TODO Auto-generated method stub
		return null;
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
