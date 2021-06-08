package com.icss.base.db;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBBeanBase {

	protected String timestamp;

	/**
	 * 连接计数器
	 */
	protected static int nConCount = 0;

	/**
	 * 数据库连接
	 */
	protected Connection con;

	/**
	 * 当前使用的Statement
	 */
	protected Statement statm;

	/**
	 * 查询结果集
	 */
	protected ResultSet resultset;

	/**
	 * 操作ID
	 */
	protected long tm;

	/**
	 * 是否是事务处理
	 */
	protected boolean isTransaction = false;

	/**
	 * 查询集合的记录数
	 */
	protected int resultrows = 0;

	/**
	 * 查询集合中每记录的列数
	 */
	protected int resultcolumns = 0;

	/**
	 * 正在执行的SQL
	 */
	protected String strSql;

	/**
	 * 记录录连接的数量
	 */
	protected static synchronized void changeCount(int nChange) {
		nConCount += nChange;
	}

	/**
	 * SQL执行结果
	 */
	protected int result;

	/**
	 * SQL执行失败的Error Code
	 */
	protected int errorCode;

	/**
	 * SQL执行失败的SQLState
	 */
	protected String SQLState;

	/**
	 * SQL执行失败的Msg
	 */
	protected String errMsg;

	/**
	 * 构造方法
	 * @param value true——表示要进行事务处理 false——表示不进行事务处理
	 */
	public DBBeanBase(boolean value) {
		try {
			try {
				con = getConnection();
			} catch (Exception e1) {
				System.out.println(e1);
			}
			statm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			isTransaction = value;
			if (isTransaction) {
				con.setAutoCommit(false);
			} else {
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * 构造方法 默认不进行事物处理
	 */
	public DBBeanBase() {
		this(false);
	}

	/**
	 * 构造方法 使用已有的数据库连接
	 * @param theConn 数据库连接
	 */
	public DBBeanBase(Connection theConn) {
		try {
			if (theConn == null) {
				System.out.println("调用了DBBeanBase(Connection theConn)，但是传入的连接是空的。使用默认连接");
				con = getConnection();
			} else {
				con = theConn;
			}
			statm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			isTransaction = !con.getAutoCommit();
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	/**
	 * 构造方法 连接指定的数据库 默认不进行事物处理
	 * @param db
	 */
	public DBBeanBase(String db) {
		this(db, false);
	}

	/**
	 * 构造方法 连接指定的数据库
	 * @param db
	 * @param value true——表示要进行事务处理 false——表示不进行事务处理
	 */
	public DBBeanBase(String db, boolean value) {
		try {
			con = getConnection(db);
			statm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			isTransaction = value;
			if (isTransaction) {
				con.setAutoCommit(false);
			} else {
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取数据库连接
	 */
	protected Connection getConnection() {
		con = ConnFactory.getInstance().getConnection();
		return con;
	}

	/**
	 * 获取指定的数据库连接
	 * @param db
	 */
	protected Connection getConnection(String db) {
		con = ConnFactory.getInstance().getConnection(db);
		return con;
	}

	/**
	 * 查询操作
	 * @param strSql
	 * @return int -1 - failed 0 - successful
	 */
	public int executeQuery(String strSql) {
		result = 0;
		try {
			if (resultset != null) {
				resultset.close();
				resultset = null;
			}
			resultset = statm.executeQuery(strSql);
		} catch (SQLException e) {
			result = -1;
			errorCode = e.getErrorCode();
			SQLState = e.getSQLState();
			errMsg = e.getMessage();
			System.out.println("Catch DataException in executeQuery of "
					+ getClass().getName());
			System.out.println("SQL:" + strSql+" Exception:"+e);
		}
		return result;
	}
	/**
	 * 查询操作
	 * @param strSql
	 * @return int -1 - failed 0 - successful
	 */
	public int executeQueryPd(String strSql) {
		result = 1;
		try {
			if (resultset != null) {
				resultset.close();
				resultset = null;
			}
			resultset = statm.executeQuery(strSql);
			if(!resultset.next()){
				result = 0;
			}
		} catch (SQLException e) {
			result = -1;
			errorCode = e.getErrorCode();
			SQLState = e.getSQLState();
			errMsg = e.getMessage();
			System.out.println("Catch DataException in executeQuery of "
					+ getClass().getName());
			System.out.println("SQL:" + strSql+" Exception:"+e);
		}
		return result;
	}


	public Object executeQuerySingle(String strSql,String voName)throws Exception {
		Object valueObject = null;
		Class c = null;
		Method m;
		String methodName;
		String key;
		Map methodNames = new HashMap();
		try{
			c = Class.forName(voName);
			valueObject = Class.forName(voName).getConstructor().newInstance();
			Method[]methods = valueObject.getClass().getMethods();
	        for (int i = 0; i < methods.length; i++) {
	        	methodName = methods[i].getName();
	        	if (!methodName.startsWith("set")) {
	        		continue;
	        	} else {
	        		key=methodName.substring(3, methodName.length()).toLowerCase();
	        		methodNames.put(key,methodName);
		        }
		    }
		}catch(Exception e){
			System.out.println(voName+"vo映射异常:"+e.getMessage());
			throw new Exception(voName+"vo映射异常:"+e.getMessage());
		}

		try {
			if (resultset != null) {
				resultset.close();
				resultset = null;
			}
			resultset = statm.executeQuery(strSql);
			ResultSetMetaData metadata = resultset.getMetaData();

			if(resultset.next()){
				valueObject = Class.forName(voName).getConstructor().newInstance();
		    	for(int i=1;i<=metadata.getColumnCount();i++){
		    		//log.debug("resultset"+i+":"+resultset.getString(metadata.getColumnName(i)));
		    		if(methodNames.get(metadata.getColumnName(i).toLowerCase())!=null){
		    			m = c.getMethod(methodNames.get(metadata.getColumnName(i).toLowerCase()).toString(),
		    					Class.forName("java.lang.String"));
		    			m.invoke(valueObject, resultset.getString(metadata.getColumnName(i)));
		    		}
		    	}
		    }

		} catch (Exception e) {
			rollback();
			errMsg = e.getMessage();
			System.out.println("Catch DataException in executeQuery of "+ getClass().getName());
			System.out.println("SQL:"+strSql+" Exception:"+e.getMessage());
			throw new Exception("SQL:"+strSql+" Exception:"+e.getMessage());
		}
		return valueObject;
	}

	/**
	 * 查询操作
	 * @param strSql 查询SQL
	 * @param voName vo类的全名
	 * @return vo对象的list
	 * @throws Exception
	 * @author wangyu
	 * @since Sep 10, 2009
	 */
	public List executeQuery(String strSql,String voName)throws Exception {
		List list = new ArrayList();
		Object valueObject = null;
		Class c = null;
		Method m;
		String methodName;
		String key;
		Map methodNames = new HashMap();
		try{
			c = Class.forName(voName);
			valueObject = Class.forName(voName).getConstructor().newInstance();
			Method[]methods = valueObject.getClass().getMethods();
	        for (int i = 0; i < methods.length; i++) {
	        	methodName = methods[i].getName();
	        	if (!methodName.startsWith("set")) {
	        		continue;
	        	} else {
	        		key=methodName.substring(3, methodName.length()).toLowerCase();
	        		methodNames.put(key,methodName);
		        }
		    }
		}catch(Exception e){
			System.out.println(voName+"vo映射异常:"+e.getMessage());
			list=null;
			throw new Exception(voName+"vo映射异常:"+e.getMessage());
		}

		try {
			if (resultset != null) {
				resultset.close();
				resultset = null;
			}
			resultset = statm.executeQuery(strSql);
			ResultSetMetaData metadata = resultset.getMetaData();

			while(resultset.next()){
				valueObject = Class.forName(voName).getConstructor().newInstance();
		    	for(int i=1;i<=metadata.getColumnCount();i++){
		    		//log.debug("resultset"+i+":"+resultset.getString(metadata.getColumnName(i)));
		    		if(methodNames.get(metadata.getColumnName(i).toLowerCase())!=null){
		    			m = c.getMethod(methodNames.get(metadata.getColumnName(i).toLowerCase()).toString(),
		    					Class.forName("java.lang.String"));
		    			m.invoke(valueObject, resultset.getString(metadata.getColumnName(i)));
		    		}
		    	}
		    	list.add(valueObject);
		    }

		} catch (Exception e) {
			list = null;
			rollback();
			errMsg = e.getMessage();
			System.out.println("Catch DataException in executeQuery of "+ getClass().getName());
			System.out.println("SQL:"+strSql+" Exception:"+e.getMessage());
			throw new Exception("SQL:"+strSql+" Exception:"+e.getMessage());
		}
		return list;
	}


	/**
	 * 插入 更新 删除 操作
	 * @param strSql
	 * @return int -1 - failed >=0 - affected rows
	 */
	public int executeUpdate(String strSql) {
		result = 0;
		try {
			if (resultset != null) {
				resultset.close();
				resultset = null;
			}
			result = statm.executeUpdate(strSql);
			if (!isTransaction) commit();
		} catch (SQLException e) {
			result = -1;
			errorCode = e.getErrorCode();
			SQLState = e.getSQLState();
			errMsg = e.getMessage();
			if (!isTransaction) rollback();
			if (!SQLState.equals("23505")) {
				System.out.println("Catch SQLException in executeUpdate of "
						+ getClass().getName());
				System.out.println("error code:" + errorCode);
				System.out.println("SQLState:" + SQLState);
				System.out.println("SQL:" + strSql);
				System.out.println(e);
			}
		}
		return result;
	}

	/**
	 * 获取传入vo的list对象
	 * @param voName vo全名称
	 * @return
	 */
	public List getResult(String voName)throws Exception{
		List list = new ArrayList();
		Object valueObject = null;
		Class c = null;
		Method m;
		String methodName;
		String key;
		Map methodNames = new HashMap();
		try{
			c = Class.forName(voName);
			valueObject = Class.forName(voName).getConstructor().newInstance();
			Method[]methods = valueObject.getClass().getMethods();
	        for (int i = 0; i < methods.length; i++) {
	        	methodName = methods[i].getName();
	        	if (!methodName.startsWith("set")) {
	        		continue;
	        	} else {
	        		key=methodName.substring(3, methodName.length()).toLowerCase();
	        		methodNames.put(key,methodName);
		        }
		    }
		}catch(Exception e){
			System.out.println(voName+"vo映射异常:"+e.getMessage());
			list=null;
			throw new Exception(voName+"vo映射异常:"+e.getMessage());
		}

		try {
			if (resultset != null) {
				resultset.close();
				resultset = null;
			}
			resultset = statm.executeQuery(strSql);
			ResultSetMetaData metadata = resultset.getMetaData();

			while(resultset.next()){
				valueObject = Class.forName(voName).getConstructor().newInstance();
		    	for(int i=1;i<=metadata.getColumnCount();i++){
		    		//log.debug("resultset"+i+":"+resultset.getString(metadata.getColumnName(i)));
		    		if(methodNames.get(metadata.getColumnName(i).toLowerCase())!=null){
		    			m = c.getMethod(methodNames.get(metadata.getColumnName(i).toLowerCase()).toString(),
		    					Class.forName("java.lang.String"));
		    			m.invoke(valueObject, resultset.getString(metadata.getColumnName(i)));
		    		}
		    	}
		    	list.add(valueObject);
		    }

		} catch (Exception e) {
			list = null;
			rollback();
			errMsg = e.getMessage();
			System.out.println("Catch DataException in executeQuery of "+ getClass().getName());
			System.out.println("SQL:"+strSql+" Exception:"+e.getMessage());
			throw new Exception("SQL:"+strSql+" Exception:"+e.getMessage());
		}
		return list;
	}

	/**
	 * 关闭连接释放资源
	 */
	public void close() {
		try {
			if (resultset != null) {
				resultset.close();
				resultset = null;
			}
			if (statm != null) {
				statm.close();
				statm = null;
			}
			if (con != null && !con.isClosed()) {
				try {
					con.commit();
					con.close();
					changeCount(-1);
				} catch (Exception e1) {
					System.out.println(e1);
				}
				con = null;
			}
		} catch (SQLException e) {
			System.out.println("This error maybe cause connection doesn't close.");
			System.out.println("There are " + nConCount + " connections now.");
			try {
				System.out.println("con.getAutoCommit()" + con.getAutoCommit());
				System.out.println("con.getCatalog()" + con.getCatalog());
				System.out.println("con.getTransactionIsolation()"+ con.getTransactionIsolation());
				System.out.println("con.getWarnings()" + con.getWarnings().getMessage());
				System.out.println("con.isClosed()" + con.isClosed());
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			System.out.println(e);
		}
		return;
	}

	/**
	 * 提交数据库中的事务
	 * @return boolean
	 */
	public boolean commit() {
		boolean success = true;
		try {
			con.commit();
		} catch (SQLException e) {
			success = false;
			try {
				System.out.println("con.getAutoCommit()" + con.getAutoCommit());
				System.out.println("con.getCatalog()" + con.getCatalog());
				System.out.println("con.getTransactionIsolation()" + con.getTransactionIsolation());
				System.out.println("con.getWarnings()" + con.getWarnings());
				System.out.println("con.isClosed()" + con.isClosed());
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			System.out.println(e);
		}
		return success;
	}

	/**
	 * 回滚数据库中的事务
	 * @return boolean
	 */
	public boolean rollback() {
		boolean success = true;
		try {
			con.rollback();
		} catch (SQLException e) {
			success = false;
			System.out.println("Catch exception in rollback() in "
					+ getClass().getName());
			try {
				System.out.println("con.getAutoCommit()" + con.getAutoCommit());
				System.out.println("con.getCatalog()" + con.getCatalog());
				System.out.println("con.getTransactionIsolation()"
						+ con.getTransactionIsolation());
				System.out.println("con.getWarnings()" + con.getWarnings().getMessage());
				System.out.println("con.isClosed()" + con.isClosed());
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			System.out.println(e);
		}
		return success;
	}

	/**
	 * 判断数据库连接是否关闭
	 * @return boolean
	 */
	public boolean isClosed() {
		try {
			if (con == null) {
				return true;
			}
			return con.isClosed();
		} catch (Exception e) {
			System.out.println("Error in isClosed()");
			System.out.println(e);
			return false;
		}
	}

	/**
	 * 出错时，返回Error Code
	 * @return int
	 */
	public int getErrorCode() {
		if (result == -1) {
			return errorCode;
		}
		return 0;
	}

	/**
	 * 出错时，返回SQLState
	 * @return String
	 */
	public String getSQLState() {
		if (result == -1) {
			return SQLState;
		}
		return "";
	}

	/**
	 * 获取数据连接
	 * @return 返回 con。
	 */
	public Connection getPrivateCon() {
		return con;
	}

}
