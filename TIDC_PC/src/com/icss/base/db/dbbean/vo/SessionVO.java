package com.icss.base.db.dbbean.vo;

public class SessionVO {

	
	private String sql ;
	private String dbType;
	private String dbUrl;
	private String dbPort;
	private String dbName;
	
	private String driverClassName;
	private String driverUrl;
	private String dbUserName;
	private String dbUserPwd;
	public SessionVO() {
		super();
	}
	public SessionVO(String sql, String dbType, String dbUrl, String dbPort,
			String dbName, String driverClassName, String driverUrl,
			String dbUserName, String dbUserPwd) {
		super();
		this.sql = sql;
		this.dbType = dbType;
		this.dbUrl = dbUrl;
		this.dbPort = dbPort;
		this.dbName = dbName;
		this.driverClassName = driverClassName;
		this.driverUrl = driverUrl;
		this.dbUserName = dbUserName;
		this.dbUserPwd = dbUserPwd;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbPort() {
		return dbPort;
	}
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getDriverUrl() {
		return driverUrl;
	}
	public void setDriverUrl(String driverUrl) {
		this.driverUrl = driverUrl;
	}
	public String getDbUserName() {
		return dbUserName;
	}
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}
	public String getDbUserPwd() {
		return dbUserPwd;
	}
	public void setDbUserPwd(String dbUserPwd) {
		this.dbUserPwd = dbUserPwd;
	}
	@Override
	public String toString() {
		return "SessionVO [sql=" + sql + ", dbType=" + dbType + ", dbUrl="
				+ dbUrl + ", dbPort=" + dbPort + ", dbName=" + dbName
				+ ", driverClassName=" + driverClassName + ", driverUrl="
				+ driverUrl + ", dbUserName=" + dbUserName + ", dbUserPwd="
				+ dbUserPwd + ", getSql()=" + getSql() + ", getDbType()="
				+ getDbType() + ", getDbUrl()=" + getDbUrl() + ", getDbPort()="
				+ getDbPort() + ", getDbName()=" + getDbName()
				+ ", getDriverClassName()=" + getDriverClassName()
				+ ", getDriverUrl()=" + getDriverUrl() + ", getDbUserName()="
				+ getDbUserName() + ", getDbUserPwd()=" + getDbUserPwd()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
