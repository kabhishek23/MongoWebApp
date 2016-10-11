package com.insfi.mongoui.db;

import com.mongodb.MongoClient;

public class MongoConnectionDetails {
	private String connectionId;
	private MongoClient mongoClient;
	private ConnectionDetails connectionDetails;

	public MongoConnectionDetails(String connectionId, MongoClient mongoClient, ConnectionDetails connectionDetails) {
		super();
		this.connectionId = connectionId;
		this.mongoClient = mongoClient;
		this.connectionDetails = connectionDetails;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public ConnectionDetails getConnectionDetails() {
		return connectionDetails;
	}

}
