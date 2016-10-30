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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		request = new MockHttpServletRequest();

		loginController = new LoginController();
		databaseController = new DatabaseController();

		loginForm = new LoginFormModel();
		loginForm.setHostIp("127.0.0.1");
		loginForm.setPort(27018);
		loginForm.setDatabase("admin");
		loginForm.setUsername("insfi");
		loginForm.setPassword("insfi");

		/*
		 * 
		 * ConnectionDetails connectionDetails = new
		 * ConnectionDetails("localhost", 27018, "db2user", "password", "db2");
		 * 
		 * String connectionId = null;
		 * 
		 * try { connectionId = auth.authenticate(connectionDetails, null); }
		 * catch (ApplicationException e) { fail("failed :" + e.getMessage()); }
		 * 
		 * databaseService = new DatabaseServiceImpl(connectionId);
		 */
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDbList() throws ApplicationException {

		JSONObject response = loginController.authenticate(loginForm, null, request);

		String connectionId = response.getString("connectionId");

		System.out.println(response.toString());

//		List<String> dbList = new ArrayList<>();
		JSONObject dbList = databaseController.getDatabaseList(connectionId, request);
		System.out.println(dbList.toString());

	}

	// @Test
	// public void testGetDbStats() {
	// fail("Not yet implemented");
	// }
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
