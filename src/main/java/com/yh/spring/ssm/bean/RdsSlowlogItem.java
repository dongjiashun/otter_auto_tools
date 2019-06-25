package com.yh.spring.ssm.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSlowlogItem {


    /**
     * 解析最大行数
     */
	@JsonProperty(value = "ParseMaxRowCount")  
    private long ParseMaxRowCount;
    /**
     * 执行总次数
     */
	@JsonProperty(value = "MySQLTotalExecutionCounts")  
    private long MySQLTotalExecutionCounts;
    
    /**
     * 查询语句
     */
	@JsonProperty(value = "SQLText")  
    private String SQLText;
	@JsonProperty(value = "SQLId")  
    private String SQLId;
	@JsonProperty(value = "DBName")  
    private String DBName;

    /**
     * 执行总时长，单位：秒
     */
	@JsonProperty(value = "MySQLTotalExecutionTimes")  
    private long MySQLTotalExecutionTimes;

    /**
     * 执行最大时长，单位：秒
     */
	@JsonProperty(value = "MaxExecutionTime")
    private long MaxExecutionTime;

    /**
     * 锁定总时长，单位：秒
     */
	@JsonProperty(value = "TotalLockTimes")
    private long TotalLockTimes;

    /**
     * 锁定最大时长，单位：秒。
     */
	@JsonProperty(value = "MaxLockTime")
    private long MaxLockTime;

    /**
     * 解析总行数
     */
	@JsonProperty(value = "ParseTotalRowCounts")
    private long ParseTotalRowCounts;


    /**
     * 返回总行数
     */
	@JsonProperty(value = "ReturnTotalRowCounts")
    private long ReturnTotalRowCounts;

    /**
     * 返回最大行数
     */
	@JsonProperty(value = "ReturnMaxRowCount")
    private long ReturnMaxRowCount;
	@JsonProperty(value = "CreateTime")
    private String CreateTime;
	
	private String dbinstance; //实例id
	
	
 
	
	

	public String getDbinstance() {
		return dbinstance;
	}

	public void setDbinstance(String dbinstance) {
		this.dbinstance = dbinstance;
	}

	public String getSQLId() {
		return SQLId;
	}

	public void setSQLId(String sQLId) {
		SQLId = sQLId;
	}

	public String getDBName() {
		return DBName;
	}

	public void setDBName(String dBName) {
		DBName = dBName;
	}

	public String getSQLText() {
		return SQLText;
	}

	public void setSQLText(String sQLText) {
		SQLText = sQLText;
	}

	public long getMySQLTotalExecutionCounts() {
		return MySQLTotalExecutionCounts;
	}

	public void setMySQLTotalExecutionCounts(long mySQLTotalExecutionCounts) {
		MySQLTotalExecutionCounts = mySQLTotalExecutionCounts;
	}

	public long getMySQLTotalExecutionTimes() {
		return MySQLTotalExecutionTimes;
	}

	public void setMySQLTotalExecutionTimes(long mySQLTotalExecutionTimes) {
		MySQLTotalExecutionTimes = mySQLTotalExecutionTimes;
	}

	public long getMaxExecutionTime() {
		return MaxExecutionTime;
	}

	public void setMaxExecutionTime(long maxExecutionTime) {
		MaxExecutionTime = maxExecutionTime;
	}

	public long getTotalLockTimes() {
		return TotalLockTimes;
	}

	public void setTotalLockTimes(long totalLockTimes) {
		TotalLockTimes = totalLockTimes;
	}

	public long getMaxLockTime() {
		return MaxLockTime;
	}

	public void setMaxLockTime(long maxLockTime) {
		MaxLockTime = maxLockTime;
	}

	public long getParseTotalRowCounts() {
		return ParseTotalRowCounts;
	}

	public void setParseTotalRowCounts(long parseTotalRowCounts) {
		ParseTotalRowCounts = parseTotalRowCounts;
	}

	public long getParseMaxRowCount() {
		return ParseMaxRowCount;
	}

	public void setParseMaxRowCount(long parseMaxRowCount) {
		ParseMaxRowCount = parseMaxRowCount;
	}

	public long getReturnTotalRowCounts() {
		return ReturnTotalRowCounts;
	}

	public void setReturnTotalRowCounts(long returnTotalRowCounts) {
		ReturnTotalRowCounts = returnTotalRowCounts;
	}

	public long getReturnMaxRowCount() {
		return ReturnMaxRowCount;
	}

	public void setReturnMaxRowCount(long returnMaxRowCount) {
		ReturnMaxRowCount = returnMaxRowCount;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	@Override
	public String toString() {
		return "RdsSlowlogItem [DBName=" + DBName + ", dbinstance="
				+ dbinstance + "]";
	}

    
    
    
}