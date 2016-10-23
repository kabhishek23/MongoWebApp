package com.insfi.mongoui.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.insfi.mongoui.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class MongoConfigTest {

	@Autowired
	private MongoConfig config;

	@Test
	public void testGetAuthMechanism() {

		String authMechanism = "SCRAM-SHA-1";

		Assert.isTrue(authMechanism.equals(config.getAuthMechanism()));

	}

}
