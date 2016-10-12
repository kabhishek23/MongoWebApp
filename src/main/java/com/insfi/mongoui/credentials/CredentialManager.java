package com.insfi.mongoui.credentials;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.mongodb.MongoCredential;

public interface CredentialManager {
	public MongoCredential createCredentials(String username, String database, String password) throws ApplicationException;
}
