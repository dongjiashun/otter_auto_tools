package com.yh.spring.ssm.bean;


//表大小 bean
public class TableSize {
	private String databasename;
	private String tablename;
	private String tablerow;
	private String tablesize;
	private String fragmentpecent;
	
	public String getTablesize() {
		return tablesize;
	}
	public void setTablesize(String tablesize) {
		this.tablesize = tablesize;
	}
	public String getDatabasename() {
		return databasename;
	}
	public void setDatabasename(String databasename) {
		this.databasename = databasename;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTablerow() {
		return tablerow;
	}
	public void setTablerow(String tablerow) {
		this.tablerow = tablerow;
	}
	public String getFragmentpecent() {
		return fragmentpecent;
	}
	public void setFragmentpecent(String fragmentpecent) {
		this.fragmentpecent = fragmentpecent;
	}
	
	
	

}
