package com.yh.spring.ssm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class datahelp {

	
	public static void main(String[] args) {
		
		
		String Datee = "2019-04-22 17:42:15";
		System.out.println(Datee.toString().trim());;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			java.util.Date date_util = sdf.parse(Datee);
			 long ts = date_util.getTime()/1000;
			System.out.println(ts);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
