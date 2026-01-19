package com.indianpharma.catalog.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfig {

	@Bean
	public Connection connection(
			@Value("${db.url}") String url,
			@Value("${db.username}") String username,
			@Value("${db.password}") String password) {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new IllegalStateException("Error establishing connection to the database", e);
		}
	}
}
