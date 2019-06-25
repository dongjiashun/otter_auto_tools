package com.yh.ssm;

import java.util.ArrayList;
import java.util.List;

import com.yh.spring.ssm.util.ZabbixUtil;

public class zabbixgetHostGroupList{
//	zabbix.url=http://10.33.119.29/api_jsonrpc.php
//		zabbix.userName=22216
//		zabbix.passWord=1212Ming

	private static String name = "22216";
	private static String pwd = "1212Ming";
	private static String url = "http://10.33.119.29/api_jsonrpc.php";
	
	public static void main(String[] args) {
		try {
			ZabbixUtil zabbix = new ZabbixUtil(name, pwd, url);
			System.out.println(zabbix.getHostGroupList());;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void listtext() {
			
	}
}
