package com.insfi.mongoui.utils;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class QueryExecutor {

	public static JSONObject executeQuery(MongoDatabase dbName, MongoCollection<Document> dbCollection, String command,
			String query, String projection, String sortBy, int limit, int skip) {

		if (command.equals("find")) {
			return executeFind(dbCollection, command, query, projection, sortBy, limit, skip);
		}
		if (command.equals("findOne")) {
			return executeFindOne(dbCollection, command, query, projection, sortBy, skip);
		}
		return null;
	}

	private static JSONObject executeFind(MongoCollection<Document> dbCollection, String command, String query,
			String projection, String sortBy, int limit, int skip) {

		Document filter = new Document();
		Document project = new Document();
		Document sortObj = new Document();

		if (!query.isEmpty()) {
			filter = Document.parse(query);
		}

		if (!projection.isEmpty()) {
			project = Document.parse(projection);
		}

		if (!sortBy.isEmpty()) {
			sortObj = Document.parse(sortBy);
		}

		FindIterable<Document> findIterable = null;

		if (!project.isEmpty()) {
			findIterable = dbCollection.find(filter).projection(project);
		} else {
			findIterable = dbCollection.find(filter);
		}

		findIterable.sort(sortObj).skip(skip).limit(limit);

		List<Document> documents = new ArrayList<Document>();

		findIterable.into(documents);

		return AppUtils.constructResponse(documents);
	}

	private static JSONObject executeFindOne(MongoCollection<Document> dbCollection, String command, String query,
			String projection, String sortBy, int skip) {
		return executeFind(dbCollection, command, query, projection, sortBy, 1, skip);
	}

}
