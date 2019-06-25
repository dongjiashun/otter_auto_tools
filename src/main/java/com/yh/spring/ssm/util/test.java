package com.yh.spring.ssm.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.yh.spring.ssm.service.templeteMail.EmailDo;
import com.yh.spring.ssm.service.templeteMail.EmailService;

public class test {

public static void main(String[] args) {
//	String txt = "Zabbix商用环境异常告警信息 告警主机：db-tffwalletmysql-1-m1 主机IP： 10.33.102.120 告警时间：2019.05.03 18:32:40 告警等级：Average 告警信息：db-tffwalletmysql-1-m1Mysql Query_timeout_10s 问题详情：mysql_Query_timeout_10s:3 事件ID： 31754939";
////	String newString = txt.replaceAll("(\r\n|\r|\n|\n\r)", "<br/>");
//	txt = txt.replaceAll("告警主机", "<br/>告警主机");
//	txt = txt.replaceAll("主机IP", "<br/>主机IP");
//	txt = txt.replaceAll("告警时间", "<br/>告警时间");
//	txt = txt.replaceAll("告警等级", "<br/>告警等级");
//	txt = txt.replaceAll("告警信息", "<br/>告警信息");
//	txt = txt.replaceAll("问题详情", "<br/>问题详情");
//	txt = txt.replaceAll("事件ID", "<br/>事件ID");
//	System.out.println(txt);
	test.filetest();
}

public static List<String> getString(String s) {
	 
	   List<String> strs = new ArrayList<String>();
	   Pattern p = Pattern.compile("Zabbix.*");
	   Matcher m = p.matcher(s);
	   while(m.find()) {
	     strs.add(m.group());
	 
	   }
	   return strs;
	}


public static void filetest(){
	try {
//		System.out.println(FileUtils.readFileToString(new File("resources/ottertable.txt"), "UTF-8"));
		List<String> filecontext = FileUtils.readLines(new File("resources/ottertable.txt"), "UTF-8");
		for (String string : filecontext) {
			System.out.println(string);
		}
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}  
}
}
