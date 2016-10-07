package com.insfi.mongoui.db;

import java.util.HashSet;
import java.util.Set;

public class ConnectionDetails {
	private String host;
	private int port;
	private String username;
	private String password;
	private Set<String> dbList = new HashSet<>();

	public ConnectionDetails(String host, int port, String username, String password, Set<String> dbList) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.dbList = dbList;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getDbList() {
		return dbList;
	}

	public void setDbList(Set<String> dbList) {
		this.dbList = dbList;
	}

}
