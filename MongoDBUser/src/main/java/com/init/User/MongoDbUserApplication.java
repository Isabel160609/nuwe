package com.init.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MongoDbUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbUserApplication.class, args);
	}

}
