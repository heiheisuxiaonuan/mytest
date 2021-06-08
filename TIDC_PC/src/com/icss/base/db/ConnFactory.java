package com.icss.base.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnFactory {

	//私有的唯一实例成员,在类加载的时候就创建好了单例对象
    private static final ConnFactory instance = new ConnFactory();

    //私有的构造方法,避免外部创建类实例
    private ConnFactory() {
    }

    //静态工厂方法,返回此类的唯一实例
    public static ConnFactory getInstance() {
        return instance;
    }

    private Connection con;
    private Map config;

    /**
     * 获取默认的数据库连接
     * @return
     * @author wangyu
     * @since Jun 16, 2009
     */
    public Connection getConnection() {
		return getConnection("");
    }

    /**
     * 获取指定的数据库连接
     * @param db
     * @return
     * @author wangyu
     * @since Jun 16, 2009
     */
    public Connection getConnection(String db){
    	config=DBConfig.getInstance().getConfig(db);
		return setConnection(config);
    }

    /**
     * 根据配置参数获取数据库连接 获取连接方式 jndi 或 jdbc 若jndi连接失败则使用jdbc
     * @param config 配置参数Map
     * @return
     * @author wangyu
     * @since Jun 16, 2009
     */
    private Connection setConnection(Map config){
    	String conType=(String)config.get("jndiProvider");
    	//获取连接方式 jndi 或 jdbc 若jndi连接失败则使用jdbc
    	if(conType==null){
    		try {
    			con=getJDBCConnection(config);
			} catch (Exception e) {
				System.out.println("数据库jdbc连接异常"+e);
				e.printStackTrace();
			}
    	}else{
    		try {
				con=getJNDIConnection(config);
			} catch (Exception e) {
				System.out.println("数据库jndi连接异常 使用jdbc连接"+e);
				try {
	    			con=getJDBCConnection(config);
				} catch (Exception e2) {
					System.out.println("数据库jdbc连接异常"+e2);
					e2.printStackTrace();
				}
				e.printStackTrace();
			}
    	}
		return con;
    }

    /**
     * JNDI获取数据库连接
     * @param config
     * @return
     * @throws Exception
     * @author wangyu
     * @since Jun 16, 2009
     */
    private Connection getJNDIConnection(Map config) throws Exception{
		String strConnPool = (String)config.get("name");
		DataSource ds = null;
		Context initCtx = null;
		initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup(strConnPool);
		if (ds != null) {
			con = ds.getConnection();
		} else {
			throw new Exception("无法获取数据库连接池");
		}
		return con;
    }

    /**
     * JDBC获取数据库连接
     * @param config
     * @return
     * @throws Exception
     * @author wangyu
     * @since Jun 16, 2009
     */
    private Connection getJDBCConnection(Map config) throws Exception{
		String strDBDriver = (String)config.get("driver");
		String strConn = (String)config.get("serverURL");
		String userName = (String)config.get("username");
		String password = (String)config.get("password");
		Class.forName(strDBDriver);
		con = DriverManager.getConnection(strConn, userName, password);
		return con;
    }

}
