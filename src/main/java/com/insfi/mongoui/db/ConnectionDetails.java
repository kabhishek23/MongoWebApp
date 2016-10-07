package com.insfi.mongoui.db;

import java.util.HashSet;
import java.util.Set;

public class ConnectionDetails {
	private String hostIp;
	private int port;
	private String username;
	private String password;
	private Set<String> dbList = new HashSet<>();

	public ConnectionDetails(String hostIp, int port, String username, String password, Set<String> dbList) {
		super();
		this.hostIp = hostIp;
		this.port = port;
		this.username = username;
		this.password = password;
		this.dbList = dbList;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
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

	public void addToDbList(Set<String> dbList) {
		this.dbList = dbList;
	}

	public boolean isAdminLogin() {
		return dbList.contains("admin");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ConnectionDetails)) {
			return false;
		}

		ConnectionDetails that = (ConnectionDetails) o;

		if (port != that.port) {
			return false;
		}
		if (dbList != null ? !dbList.equals(that.dbList) : that.dbList != null) {
			return false;
		}
		if (hostIp != null ? !hostIp.equals(that.hostIp) : that.hostIp != null) {
			return false;
		}
		if (password != null ? !password.equals(that.password) : that.password != null) {
			return false;
		}
		if (username != null ? !username.equals(that.username) : that.username != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = hostIp != null ? hostIp.hashCode() : 0;
		result = 31 * result + port;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (dbList != null ? dbList.hashCode() : 0);
		if (result == Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		}
		return Math.abs(result);
	}

}
