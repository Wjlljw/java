package cn.itcast.demo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;

public class Demo3 {
	@Test
	public boolean login(String username, String password) throws Exception{
		
		String driverClassName="com.mysql.jdbc.Driver";
		
		String url = "jdbc:mysql://localhost:3306/day01?serverTimezone=GMT%2B8&useSSL=false";
		
		String mysqlusername="root";
		
		String mysqlpassword="20011005";
		
		
		Class.forName(driverClassName);
		
		Connection con = DriverManager.getConnection(url,mysqlusername,mysqlpassword);
		
		Statement stmt = con.createStatement();
		
		String sql = "select * from t_user where username='"+username+"' and password='"+password+"'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs.next();
	}
	
	
	@Test
	public boolean login2(String username, String password) throws Exception{
		
		String driverClassName="com.mysql.jdbc.Driver";
		
		String url = "jdbc:mysql://localhost:3306/day01?serverTimezone=GMT%2B8&useSSL=false";
		
		String mysqlusername="root";
		
		String mysqlpassword="20011005";
		
		
		Class.forName(driverClassName);
		
		Connection con = DriverManager.getConnection(url,mysqlusername,mysqlpassword);
		
		//¸ø³ösqlÄ£°å
		String sql = "select * from t_user where username=? and password=?";
		
		java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		return rs.next();
	}
	
	@Test
	public void fun2() throws Exception {
		String username = "a' or 'a'='a";
		String password = "a' or 'a'='a";
		
		boolean flag = login2(username, password);
		
		System.out.println(flag);
	}
	
	@Test 
	public void fun3() throws SQLException {
		Connection con = JdbcUtils.getConnection();
		System.out.println(con);
		
		
		
	}
}
