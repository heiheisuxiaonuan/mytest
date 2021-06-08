package com.icss.base.db.dbbean;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.icss.base.db.dbbean.vo.SessionVO;

public class SessionFactory {
	
	public static Connection getConn(String dbType) throws SQLException, IOException, ClassNotFoundException {
		SessionVO svo = getInfo(dbType);
		// 1.注册数据库驱动
		Class.forName(svo.getDriverClassName());
		// 2.创建数据库连接
		String url = svo.getDriverUrl();
		String username = svo.getDbUserName();
		String password = svo.getDbUserPwd();
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	
	public static SessionVO getInfo(String dbType){
		SessionVO svo = new SessionVO();
		if("db2_dw".equals(dbType)){
			svo.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
			//svo.setDriverUrl("jdbc:db2://10.1.12.61:50000/db2_dw");
			//svo.setDbUserName("db2inst1");
			//svo.setDbUserPwd("db2inst1");
			svo.setDriverUrl("jdbc:db2://192.168.1.203:50000/db2_dw");
			svo.setDbUserName("db2admin");
			svo.setDbUserPwd("db2admin");
		}
		return svo;
	}

	public static void main(String[] args) throws SQLException, IOException {
		
	}
}
