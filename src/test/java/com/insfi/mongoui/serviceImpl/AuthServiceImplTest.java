package com.insfi.mongoui.serviceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insfi.mongoui.Application;
import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.services.AuthService;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AuthServiceImplTest extends TestCase {
	
	@Autowired
	AuthService auth;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAuthenticate() {
		ConnectionDetails connectionDetails = new ConnectionDetails("localhost", 27018, "db2user", "password", "db2");
		String connectionId = null;
		try {
			connectionId = auth.authenticate(connectionDetails, null);
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
