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
 * 批处理
 */

//打开批处理的参数
//rewritebatchedStatements=true

public class Demo5 {
	@Test
	//把mp3格式的数据存入数据库
	public void fun1() throws SQLException, FileNotFoundException, IOException {
		/*
		 * 1.得到connection
		 * 2.给出sql模板，创建pstmt
		 * 3.将file转换为blob
		 * 4.执行SQL语句，将数据存入
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