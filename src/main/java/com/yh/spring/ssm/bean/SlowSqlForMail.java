package com.yh.spring.ssm.bean;

public class SlowSqlForMail {
	private String dbName; //dbname
	private String sqltxt;
	private long avgParseRowCount;//平均解析行数
	private long parseMaxRowCount;// 最大解析行数
	private long avgReturnRows;//平均返回行数 
	private long maxReturnRows;//最大返回行数
	private long execCounts;//执行次数
	private String  depCode;//所属部门
	
	
	
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getSqltxt() {
		return sqltxt;
	}
	public void setSqltxt(String sqltxt) {
		this.sqltxt = sqltxt;
	}
	public long getAvgParseRowCount() {
		return avgParseRowCount;
	}
	public void setAvgParseRowCount(long avgParseRowCount) {
		this.avgParseRowCount = avgParseRowCount;
	}
	public long getParseMaxRowCount() {
		return parseMaxRowCount;
	}
	public void setParseMaxRowCount(long parseMaxRowCount) {
		this.parseMaxRowCount = parseMaxRowCount;
	}
	public long getAvgReturnRows() {
		return avgReturnRows;
	}
	public void setAvgReturnRows(long avgReturnRows) {
		this.avgReturnRows = avgReturnRows;
	}
	public long getMaxReturnRows() {
		return maxReturnRows;
	}
	public void setMaxReturnRows(long maxReturnRows) {
		this.maxReturnRows = maxReturnRows;
	}
	public long getExecCounts() {
		return execCounts;
	}
	public void setExecCounts(long execCounts) {
		this.execCounts = execCounts;
	}

	public String getDepCode() {
		return depCode;
	}
	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
	@Override
	public String toString() {
		return "SlowSqlForMail [dbName=" + dbName + ", sqltxt=" + sqltxt
				+ ", avgParseRowCount=" + avgParseRowCount
				+ ", parseMaxRowCount=" + parseMaxRowCount + ", avgReturnRows="
				+ avgReturnRows + ", maxReturnRows=" + maxReturnRows
				+ ", execCounts=" + execCounts + ", depCode=" + depCode + "]";
	}
	
	
	
	
	
	
}
