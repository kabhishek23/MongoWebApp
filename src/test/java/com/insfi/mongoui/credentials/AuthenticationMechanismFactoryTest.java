package com.insfi.mongoui.credentials;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insfi.mongoui.Application;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.mongodb.MongoCredential;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AuthenticationMechanismFactoryTest extends TestCase {

	@Autowired
	AuthenticationMechanismFactory authManager;

	@Test
	public void testCreateCredentials() {

		String username = "db2user";
		String password = "password";
		String database = "db2";

		MongoCredential expected = MongoCredential.createScramSha1Credential(username, database,
				password.toCharArray());

		MongoCredential credentials = null;
		try {
			credentials = authManager.createCredentials(username, database, password);
		} catch (ApplicationException e) {

		}

		assertEquals(expected, credentials);
	}

}
