package com.yh.spring.ssm.bean;

/**
 * 
 * @author dognjs
 *数据库白名单表
 */
public class RdsWhiteListDb {
	
	private String dbname; //数据库名
	private String dbinstance;//RDS域名
	private String team;//所属业务团队
	private int slowlog;//是否启用慢查日报统计：0 .否 1 是
	
	
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getDbinstance() {
		return dbinstance;
	}
	public void setDbinstance(String dbinstance) {
		this.dbinstance = dbinstance;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getSlowlog() {
		return slowlog;
	}
	public void setSlowlog(int slowlog) {
		this.slowlog = slowlog;
	}
	@Override
	public String toString() {
		return "Rds_whiteList [dbname=" + dbname + ", dbinstance=" + dbinstance
				+ ", team=" + team + ", slowlog=" + slowlog + "]";
	}
	
	
	
	

}
