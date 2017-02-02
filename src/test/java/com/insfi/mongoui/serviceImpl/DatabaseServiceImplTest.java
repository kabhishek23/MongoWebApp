package com.insfi.mongoui.serviceImpl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insfi.mongoui.Application;
import com.insfi.mongoui.controller.DatabaseController;
import com.insfi.mongoui.controller.LoginController;
import com.insfi.mongoui.exceptions.ApplicationException;
import com.insfi.mongoui.models.LoginFormModel;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class DatabaseServiceImplTest {

	private LoginFormModel loginForm;

	private LoginController loginController;

	private HttpServletRequest request;

	private DatabaseController databaseController;

	private String connectionId;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() {
		try {
			request = new MockHttpServletRequest();

			loginController = new LoginController();
			databaseController = new DatabaseController();

			loginForm = new LoginFormModel();
			loginForm.setHost("127.0.0.1");
			loginForm.setPort(27017);
			loginForm.setDatabase("admin");
			loginForm.setUsername("");
			loginForm.setPassword("");

			JSONObject response = new JSONObject(loginController.authenticate(loginForm, null, request));

			connectionId = response.getJSONObject("payload").getString("connectionId");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDbList() throws ApplicationException {

		String dbList = databaseController.getDatabaseList(connectionId, request);
		System.out.println(dbList);

	}

	// @Test
	public void testGetDbStats() {
		try {
			String dbStats = databaseController.getDatabaseStats("db2", connectionId, request);
			System.out.println(dbStats.toString());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	//
	// @Test
	// public void testCreateDb() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testDropDb() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testExecuteQuery() {
	// fail("Not yet implemented");
	// }

}
