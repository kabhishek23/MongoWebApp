package com.insfi.mongoui.db;

import com.mongodb.Mongo;

public class ConnectionProperties {
	private String connectionId;
	private Mongo mongo;
	private ConnectionDetails connectionDetails;

	public ConnectionProperties(String connectionId, Mongo mongo, ConnectionDetails connectionDetails) {
		super();
		this.connectionId = connectionId;
		this.mongo = mongo;
		this.connectionDetails = connectionDetails;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public Mongo getMongo() {
		return mongo;
	}

	public ConnectionDetails getConnectionDetails() {
		return connectionDetails;
	}

}
