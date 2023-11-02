package cn.itcast.demo5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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

//��������Ĳ���
//rewritebatchedStatements=true

public class Demo5 {
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
		String sql = "insert into ac values(?,?,?)";
		java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
		
		for(int i = 0 ; i < 10000;  i++) {
			pstmt.setInt(1, i+1);
			pstmt.setString(2, "ac"+i);
			pstmt.setBigDecimal(3, new BigDecimal("10000.0"));
			
			pstmt.addBatch();
		}
		
		long start = System.currentTimeMillis();
		pstmt.executeBatch();
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}