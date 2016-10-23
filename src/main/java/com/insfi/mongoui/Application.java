package com.insfi.mongoui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *  _            __ _ 
*(_)_ __  ___ / _(_)
*| | '_ \/ __| |_| |
*| | | | \__ \  _| |
*|_|_| |_|___/_| |_|

 * @author abhishek
 *
 */

//@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
