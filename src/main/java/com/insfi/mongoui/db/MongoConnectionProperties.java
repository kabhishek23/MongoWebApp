package com.insfi.mongoui.db;

import com.mongodb.Mongo;

public class MongoConnectionProperties {
	private String connectionId;
	private Mongo mongo;
	private ConnectionDetails connectionDetails;

	public MongoConnectionProperties(String connectionId, Mongo mongo, ConnectionDetails connectionDetails) {
		super();
		this.connectionId = connectionId;
		this.mongo = mongo;
		this.connectionDetails = connectionDetails;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}

	public ConnectionDetails getConnectionDetails() {
		return connectionDetails;
	}

	public void setConnectionDetails(ConnectionDetails connectionDetails) {
		this.connectionDetails = connectionDetails;
	}

}
