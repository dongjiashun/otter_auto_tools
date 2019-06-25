package com.yh.spring.ssm.util ;

import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Integer getTimestamp(Date date){
		if (date == null ) return null;
		else{
			return Long.valueOf(date.getTime()/1000).intValue();
		}
	}
	public static int todayCompare(Date date){
	    Calendar todayCal = Calendar.getInstance();
	    todayCal.set(Calendar.HOUR_OF_DAY, 0);
	    todayCal.set(Calendar.MINUTE, 0);
	    Calendar dateCal = Calendar.getInstance();
	    dateCal.setTime(date);
	    dateCal.set(Calendar.HOUR_OF_DAY, 23);
	    return todayCal.compareTo(dateCal);
	}
	
	 public static String stampToDate( ) {
		  Long time = System.currentTimeMillis( ) ;
		  String s = time.toString( ) ;
		  String res ;
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) ;
		  long lt = new Long( s ) ;
		  Date date = new Date( lt ) ;
		  res = simpleDateFormat.format( date ) ;
		  return res ;
	 }
	 
	 public static String NowDay( ) {
		  Long time = System.currentTimeMillis( ) ;
		  String s = time.toString( ) ;
		  String res ;
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" ) ;
		  long lt = new Long( s ) ;
		  Date date = new Date( lt ) ;
		  res = simpleDateFormat.format( date ) ;
		  return res ;
	 }
	public static Date getDeadLine(Integer validMonths, Integer validDays,Date createTime) {
		if (validMonths==null) {
			validMonths = 0;
		}
		if (validDays  == null) {
			validDays = 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(createTime);
		cal.add(Calendar.MONTH, validMonths);
		cal.add(Calendar.DATE, validDays);
		return cal.getTime();
	}
	

	  public static Date dayBegin()
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.set(11, 0);
	    c.set(12, 0);
	    c.set(13, 0);
	    c.set(14, 0);
	    return c.getTime();
	  }

	  public static Date dayEnd()
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.set(11, 23);
	    c.set(12, 59);
	    c.set(13, 59);
	    c.set(14, 999);
	    return c.getTime();
	  }

	  public static boolean isSameDay(Date date1, Date date2) {
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(date1);
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(date2);
	    if ((c1.get(6) == c2.get(6)) && 
	      (c1.get(1) == c2.get(1)))
	    {
	      return true;
	    }
	    return false;
	  }

	  public static int getDayOfYear(Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(6);
	  }

	  public static Date beforeDays(Date date, int days) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(6, -days);
	    return c.getTime();
	  }
	  public static Date beforeHours(int hours) {
	    Calendar c = Calendar.getInstance();
	    c.add(11, -hours);
	    return c.getTime();
	  }

	  public static Date afterWorkDays(int days) {
	    Calendar c = Calendar.getInstance();
	    c.add(6, days);
	    int dayOfWeek = c.get(7);
	    if (dayOfWeek == 1)
	      c.add(6, 1);
	    else if (dayOfWeek == 7) {
	      c.add(6, 2);
	    }
	    return c.getTime();
	  }

	  public static Date afterNaturalDays(int days) {
	    Calendar c = Calendar.getInstance();
	    c.add(6, days);
	    c.set(11, 23);
	    c.set(12, 59);
	    c.set(13, 59);
	    c.set(14, 999);
	    return c.getTime();
	  }
	  public static Date nextDate(Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(6, 1);
	    return c.getTime();
	  }

	  public static Date nextDateBegin(Date date)
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(6, 1);
	    c.set(11, 0);
	    c.set(12, 0);
	    c.set(13, 0);
	    c.set(14, 0);
	    return c.getTime();
	  }

	  public static Date afterNaturalMonthes(int monthes) {
	    Calendar c = Calendar.getInstance();
	    c.add(2, monthes);
	    c.set(11, 23);
	    c.set(12, 59);
	    c.set(13, 59);
	    c.set(14, 999);
	    return c.getTime();
	  }
	  public static Date afterNaturalMonthes(Date startDate, int monthes) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(startDate);
	    c.add(2, monthes);
	    c.set(11, 23);
	    c.set(12, 59);
	    c.set(13, 59);
	    c.set(14, 999);
	    return c.getTime();
	  }

	  public static boolean isWeekend(Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int dayOfweek = c.get(7);
	    if ((dayOfweek == 1) || (dayOfweek == 7)) {
	      return true;
	    }
	    return false;
	  }

	  public static String formatDate(Date date) {
	    if (date == null) {
	      return null;
	    }
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
	  }
	  public static String formatDateTime(Date date) {
	    if (date == null) {
	      return null;
	    }
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.format(date);
	  }

	  public static void main(String[] args) {
	    Date date = beforeHours(24);
	    System.out.println(formatDateTime(date));
	  }
	  
	//获取当天几点钟
		public static Date getTodayTime(int time){
			 Calendar c = Calendar.getInstance();
		        c.set(Calendar.HOUR_OF_DAY,time);
		        c.set(Calendar.MINUTE, 0);
		        c.set(Calendar.SECOND, 0);
		        Date date = c.getTime();
		        return date;
		}
		/**
		 * 日期比较
		 */
		public static boolean compareDate(Date date){
			return date.getTime() <=new Date().getTime();
		}
		
		
		public static long getNowTime(Date date){
				java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
				String string = format.format(date);
				try {
					date = format.parse(string);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return date.getTime()/1000;
			}

}
