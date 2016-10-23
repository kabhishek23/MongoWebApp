package com.insfi.mongoui.serviceImpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insfi.mongoui.Application;
import com.insfi.mongoui.db.ConnectionDetails;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.exceptions.DatabaseException;
import com.insfi.mongoui.services.AuthService;
import com.insfi.mongoui.services.DatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class DatabaseServiceImplTest {

	@Autowired
	AuthService auth;

	@Autowired
	DatabaseService databaseService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		ConnectionDetails connectionDetails = new ConnectionDetails("localhost", 27018, "db2user", "password", "db2");

		String connectionId = null;

		try {
			connectionId = auth.authenticate(connectionDetails, null);
		} catch (ApplicationException e) {
			fail("failed :" + e.getMessage());
		}

		databaseService = new DatabaseServiceImpl(connectionId);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDbList() {
		List<String> dbList = new ArrayList<>();
		try {
			dbList = databaseService.getDbList();
		} catch (DatabaseException e) {
			fail("failed" + e.getMessage());
		}

		assertNotNull(dbList);

	}

	@Test
	public void testGetDbStats() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateDb() {
		fail("Not yet implemented");
	}

	@Test
	public void testDropDb() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteQuery() {
		fail("Not yet implemented");
	}

}
