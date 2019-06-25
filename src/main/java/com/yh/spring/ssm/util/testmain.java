package com.yh.spring.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testmain {

	
	public static void main(String[] args) {

			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  Date now = new Date();
			  System.out.println("当前时间：" + sdf.format(now));
			  long time = 60*1000;//30分钟
//			  Date afterDate = new Date(now .getTime() + time);//30分钟后的时间
			  Date beforeDate = new Date(now .getTime() - time);//30分钟前的时间
			  Long timeend = beforeDate.getTime()/1000;
			 System.out.println( Long.toString(timeend));
			  System.out.println(sdf.format(beforeDate));
		}
	}

