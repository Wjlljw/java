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
	 * �������ݿ⣬�õ�Connection
	 * 
	 */
	
	@Test
	public void fun1() throws ClassNotFoundException, SQLException {
		/*
		 * һ.Connection
		 * 1.׼���Ĵ����
		 * 2.����������
		 * 3.�õ�Connection
		 */
		String driverClassName="com.mysql.jdbc.Driver";
		
		//jdbc��ʽ��jdbc:�������ƣ���Э�飨�г����Լ��涨��������:�˿ں�/���ݿ�����
		String url = "jdbc:mysql://localhost:3306/day01";
		
		String username = "root";
		
		String password = "20011005";
		
		//����������
		Class.forName(driverClassName);
		
		
		//ʹ��DriverManager, �Լ�ʣ�µ�3���������õ�Connection
		Connection con = DriverManager.getConnection(url,username,password);
		
		/*
		 * ���������ݽ�����ɾ�Ĳ�
		 */
		
		//1��ͨ��Connetion�õ�Statemaent����
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
		
		
		//����������
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
		
		//4.�ر���Դ
		rs.close();
		stmt.close();
		con.close();
		
	}
	
	
	//�淶������
	@Test
	public void fun3() throws Exception{
		
		Connection con = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			
			//serverTimezone=GMT%2B8: �ⲿ���������ݿ��������ʱ��ΪGMT+8��
			//useSSL=false: �ⲿ�ֱ�ʾ���ݿ����ӽ���ʹ��SSL����ȫ�׽��ֲ㣩���м��ܡ�
			
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
			
			
			//ѭ����������ӡ��������
//			while(rs.next()) {
//				int id = rs.getInt(1);
//				String name = rs.getString(2);
//				int balance = rs.getInt(3);
//				
//				System.out.println(id+"\t"+name+"\t"+balance);
//			}
			
			//ֻ֪������������ǲ������ľ�������������ʱ��ı�������
			
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
