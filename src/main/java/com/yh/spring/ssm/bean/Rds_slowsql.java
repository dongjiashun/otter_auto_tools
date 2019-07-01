package com.yh.spring.ssm.bean;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Rds_slowsql {
	public final static String URL = "https://rds.aliyuncs.com";
	public final static String ACCESSKEYID = "*********";
	public final static String accessKeySecret = "************";
	public final static DateTimeFormatter UTCFORMATTER = DateTimeFormatter
			.ofPattern("YYYY-MM-dd'T'HH:mm:ss'Z'");
	public final static DateTimeFormatter DATEFORMAT = DateTimeFormatter
			.ofPattern("yyyy-MM-dd'Z'");
	public final static int PAGE_SIZE = 30; // 分頁
	public final static int ZERO = 0;
	public final static Map<String, String> commonParam = new HashMap<String, String>();
	public final static String APINAME = "DescribeSlowLogs"; // rds--api名字

	public static ObjectMapper mapper = new ObjectMapper();
	public static boolean sqlWhiltStatus = false;
	public static final int MAXNUM = 100;
	public static Map<String, List<String>> sessionkeys = new HashMap<String, List<String>>();
	// public static String sessionPath = "/root/koolbao_apps/sessions/";
	public static String sessionPath = "C:\\Users\\Administrator\\Desktop\\sqlw.txt";
}
