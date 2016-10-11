package com.insfi.mongoui.serviceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.services.AuthService;

import junit.framework.TestCase;

public class AuthServiceImplTest extends TestCase {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAuthenticate() {
		ConnectionDetails connectionDetails = new ConnectionDetails("localhost", 27017, null, null, "sometest");
		AuthService AUTH_SERVICE = AuthServiceImpl.getInstance();
		String connectionId = null;
		try {
			connectionId = AUTH_SERVICE.authenticate(connectionDetails, null);
		} catch (ApplicationException e) {
			fail("failed :" + e.getMessage());
		}

		assertNotNull(connectionId);
		assertNotNull(connectionDetails.getAuthenticatedDbList());
	}

	@Test
	public void testGetConnectionProperties() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMongoInstace() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}

}
