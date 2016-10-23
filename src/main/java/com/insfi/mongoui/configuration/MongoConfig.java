package com.insfi.mongoui.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mongoconfig")
public class MongoConfig {
	private String authMechanism;

	public String getAuthMechanism() {
		return authMechanism;
	}

	public void setAuthMechanism(String authMechanism) {
		this.authMechanism = authMechanism;
	}

}
