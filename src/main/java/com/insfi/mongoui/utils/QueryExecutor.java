package com.insfi.mongoui.utils;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.InvalidCommandException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class QueryExecutor {

	public static JSONObject executeQuery(MongoDatabase dbName, MongoCollection<Document> dbCollection, String command,
			String query, String projection, String sortBy, int limit, int skip) throws InvalidCommandException {

		if (command.equals("find")) {
			return executeFind(dbCollection, command, query, projection, sortBy, limit, skip);
		}
		if (command.equals("findOne")) {
			return executeFindOne(dbCollection, command, query, projection, sortBy, skip);
		}

		throw new InvalidCommandException(ErrorCode.INVALID_MONGO_COMMAND_EXCEPTION,
				"Command [ " + command + " ] not supported yet.");
	}

	private static JSONObject executeFind(MongoCollection<Document> dbCollection, String command, String query,
			String projection, String sortBy, int limit, int skip) {

		Document filter = new Document();
		Document project = new Document();
		Document sortObj = new Document();

		if (query != null) {
			filter = Document.parse(query);
		}

		if (projection != null) {
			project = Document.parse(projection);
		}

		if (sortBy != null) {
			sortObj = Document.parse(sortBy);
		}

		FindIterable<Document> findIterable = null;

		if (project != null) {
			findIterable = dbCollection.find(filter).projection(project);
		} else {
			findIterable = dbCollection.find(filter);
		}

		findIterable.sort(sortObj).skip(skip).limit(limit);

		List<Document> documents = processResultSet(findIterable);

		return AppUtils.constructResponse(documents);
	}

	private static List<Document> processResultSet(FindIterable<Document> findIterable) {
		MongoCursor<Document> itr = findIterable.iterator();
		List<Document> documents = new ArrayList<Document>();
		while (itr.hasNext()) {
			Document nextDoc = itr.next();

			String objectId = String.format("ObjectId(\"%s\")", new Object[] { nextDoc.get("_id").toString() });

			nextDoc.replace("_id", objectId);

			documents.add(nextDoc);
		}

		return documents;
	}

	private static JSONObject executeFindOne(MongoCollection<Document> dbCollection, String command, String query,
			String projection, String sortBy, int skip) {
		return executeFind(dbCollection, command, query, projection, sortBy, 1, skip);
	}

}
