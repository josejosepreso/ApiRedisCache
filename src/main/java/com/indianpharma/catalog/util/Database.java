package com.indianpharma.catalog.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Database {

	@Autowired
	private Connection connection;

	public ResultSet executeQuery(String sql) throws SQLException {
		return this.connection.prepareStatement(sql).executeQuery();
	}
}
