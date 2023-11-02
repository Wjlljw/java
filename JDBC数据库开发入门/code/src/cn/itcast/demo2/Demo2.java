package cn.itcast.demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

public class Demo2 {
	private static final String ResultSet = null;

	/*
	 * 连接数据库，得到Connection
	 * 
	 */
	
	@Test
	public void fun1() throws ClassNotFoundException, SQLException {
		/*
		 * 一.Connection
		 * 1.准备四大参数
		 * 2.加载驱动类
		 * 3.得到Connection
		 */
		String driverClassName="com.mysql.jdbc.Driver";
		
		//jdbc格式：jdbc:厂商名称：子协议（有厂商自己规定）主机号:端口号/数据库名称
		String url = "jdbc:mysql://localhost:3306/day01";
		
		String username = "root";
		
		String password = "20011005";
		
		//加载驱动类
		Class.forName(driverClassName);
		
		
		//使用DriverManager, 以及剩下的3个参数，得到Connection
		Connection con = DriverManager.getConnection(url,username,password);
		
		/*
		 * 二、对数据进行增删改查
		 */
		
		//1。通过Connetion得到Statemaent对象
		Statement stmt = con.createStatement();
		
		String sql = "insert into ac(id,name,balance) values('5','wjw','1000000')";
		
		int r = stmt.executeUpdate(sql);
		
		System.out.println(r);
	}
	
	
	
	
	@Test
	public void fun2() throws ClassNotFoundException, SQLException {
		
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/day01?serverTimezone=GMT%2B8&useSSL=false";
		String username = "root";
		String password = "20011005";
		
		
		//加载驱动类
		Class.forName(driverClassName);
		
		Connection con = DriverManager.getConnection(url,username,password);
		
		Statement stmt = con.createStatement();
		
		String sql = "select * from ac";
				
		ResultSet rs = stmt.executeQuery(sql);
		
		System.out.println(rs);
		
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString("name");
			int balance = rs.getInt("balance");
			
			System.out.println(id+"\t"+name+"\t"+balance);
		}
		
		//4.关闭资源
		rs.close();
		stmt.close();
		con.close();
		
	}
	
	
	//规范化代码
	@Test
	public void fun3() throws Exception{
		
		Connection con = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			
			//serverTimezone=GMT%2B8: 这部分设置数据库服务器的时区为GMT+8。
			//useSSL=false: 这部分表示数据库连接将不使用SSL（安全套接字层）进行加密。
			
			String url = "jdbc:mysql://localhost:3306/day01?serverTimezone=GMT%2B8&useSSL=false";
			String username="root";
			String password="20011005";
			
			
			Class.forName(driverClassName);
			
			con = DriverManager.getConnection(url,username,password);
			
			String sql = "select * from ac";
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			rs.last();
			System.out.println(rs.getRow());
			rs.beforeFirst();
			
			
			//循环遍历，打印其中数据
//			while(rs.next()) {
//				int id = rs.getInt(1);
//				String name = rs.getString(2);
//				int balance = rs.getInt(3);
//				
//				System.out.println(id+"\t"+name+"\t"+balance);
//			}
			
			//只知道结果集，但是不清楚表的具体行数和列数时候的遍历方法
			
			int count = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				for(int i = 1; i<=count;i++) {
					System.out.print(rs.getString(i));
					if(i < count) {
						System.out.print("\t");
					}
				}
				System.out.println();
				
			}
			
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}
	}
	
}
