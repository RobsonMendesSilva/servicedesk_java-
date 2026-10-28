package br.com.service_desk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL	= "jdbc:mysql://localhost:3306/service_desk?useTimezone=true&serverTimezone=UTC";
	
	private static final String USER = "admin";
	private static final String PASS = "coti123"; // ajuste para a SUA senha
	
	
	
	
	public static Connection getConnection() throws SQLException {
	return DriverManager.getConnection(URL, USER, PASS);
	}

}
