package cn.itcast.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class Demo1 {
	@Test
	public void fun1() throws ClassNotFoundException, SQLException {
		/*
		 * JDBC四大参数
		 * > driverClassName: com.mysql.jdbc.Driver
		 * > url: jdbc:mysql://localhost:3306/day01
		 * > username: root
		 * > password: 20011005
		 */
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/day01";
		String username = "root";
		String password = "20011005";
		
		Connection con = DriverManager.getConnection(url,username,password);
		System.out.println(con);		
	}
}
