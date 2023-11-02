package cn.itcast.demo4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;

import cn.itcast.demo3.JdbcUtils;

/*
 * ������
 */

public class Demo4 {
	@Test
	//��mp3��ʽ�����ݴ������ݿ�
	public void fun1() throws SQLException, FileNotFoundException, IOException {
		/*
		 * 1.�õ�connection
		 * 2.����sqlģ�壬����pstmt
		 * 3.��fileת��Ϊblob
		 * 4.ִ��SQL��䣬�����ݴ���
		 */
		Connection con = JdbcUtils.getConnection();
		String sql = "insert into tab_bin values(?,?,?)";
		java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
		
		
		pstmt.setInt(1, 1);
		pstmt.setString(2, "Hello����Adele.mp3");
		
		/*
		 * ��Ҫ�õ�Blob
		 * 1.���ļ�ת��Ϊbyte[]
		 * 2.ͨ��byte[]����Blob
		 */
		byte[] bytes = IOUtils.toByteArray(new FileInputStream("E:\\music\\Hello����Adele.mp3"));
		SerialBlob blob = new SerialBlob(bytes);
		pstmt.setBlob(3, blob);
		
		pstmt.executeUpdate();
	
		
	}
	
	
	
	
	//�����ݿ��ж�ȡmp3
	@Test
	public void fun2() throws Exception{
		
//		Connection con = JdbcUtils.getConnection();
//		
//		String sql = "select * from tab_bin";
//		
//		
//		java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
//		
//		ResultSet rs = pstmt.executeQuery();
//		
//		if(rs.next()) {
//			Blob blob = (Blob) rs.getBlob("data");
//			
//			
//			InputStream in = blob.getBinaryStream();
//			OutputStream out = new FileOutputStream("E:\\music\\1.mp3");
//			IOUtils.copy(in,out);
//		}
		
		Connection conn = null;  
		PreparedStatement pstmt = null;  
		ResultSet rs = null;

		try {  
		    conn = JdbcUtils.getConnection();  
		    String sql = "SELECT * FROM tab_bin WHERE id = 1";  
		    pstmt = (PreparedStatement) conn.prepareStatement(sql);  
		    rs = pstmt.executeQuery();

		   if(rs.next()) {
			   Blob blob = (Blob) rs.getBlob("data");
			   InputStream in = blob.getBinaryStream();
			   OutputStream out = new FileOutputStream("E:\\music\\2.mp3");
			   IOUtils.copy(in,out);
		   }
		} catch (SQLException e) {  
		    e.printStackTrace();  
		} finally {  
		    // �ر���Դ  
		    if (rs != null) {  
		        try {  
		            rs.close();  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        }  
		    }  
		    if (pstmt != null) {  
		        try {  
		            pstmt.close();  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        }  
		    }  
		    if (conn != null) {  
		        try {  
		            conn.close();  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        }  
		    }  
		}
	}
}
