package com.insfi.mongoui.credentials;

import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.ErrorCode;
import com.insfi.mongoui.exceptions.MongoConnectionException;
import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;

public class AuthenticationMechanismFactory implements CredentialManager {

	private static AuthenticationMechanism mechanism;

	@Override
	public MongoCredential createCredentials(String username, String database, String password)
			throws ApplicationException {
		getMechanismName();

		MongoCredential credential = getCredentials(username, database, password);

		if (credential == null) {
			throw new MongoConnectionException(ErrorCode.INVALID_AUTH_MECHANISM, "Invalid Authentication Mechanis");
		}

		return credential;
	}

	/**
	 * Create Credentials based on Authentication Mechanism supported by server
	 * 
	 * @param username
	 * @param database
	 * @param password
	 * @return
	 */
	private MongoCredential getCredentials(String username, String database, String password) {
		MongoCredential credential = null;

		// convert String into CharArray
		char[] passwordChars = password.toCharArray();

		switch (mechanism) {

		case GSSAPI:
			credential = MongoCredential.createGSSAPICredential(username);
			break;

		case MONGODB_CR:
			credential = MongoCredential.createMongoCRCredential(username, database, passwordChars);
			break;

		case MONGODB_X509:
			credential = MongoCredential.createMongoX509Credential(username);
			break;

		case SCRAM_SHA_1:
			credential = MongoCredential.createScramSha1Credential(username, database, passwordChars);
			break;

		case PLAIN:
			credential = MongoCredential.createPlainCredential(username, database, passwordChars);
			break;

		default:
			credential = MongoCredential.createCredential(username, database, passwordChars);
			break;
		}

		return credential;
	}

	/**
	 * Read Authentication Mechanism Setting from configuration and return
	 * AuthenticationMechanism
	 * TODO : Read Configuration file to get supported Authentication Mechanism 
	 * @return
	 * @throws ApplicationException
	 */
	private static void getMechanismName() throws ApplicationException {

		try {
			mechanism = AuthenticationMechanism.fromMechanismName("mechanismName");
		} catch (MongoException me) {
			throw new MongoConnectionException(ErrorCode.INVALID_AUTH_MECHANISM, "Invalid Auth Mechanism");
		}

	}

}
