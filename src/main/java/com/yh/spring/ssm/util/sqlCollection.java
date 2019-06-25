package com.yh.spring.ssm.util ;

public class sqlCollection {

	 // 线程默认数量是10条
	 public volatile static int	 THREADSIZE					 = 10 ;
	 public volatile static int	 JDBCNUM						 = 10 ;
	 public volatile static String DDYDB_JDBCURL_RW			 = "ddydb_jdbcUrl_rw" ;
	 public volatile static String DDYDB_JDBCURL				 = "ddydb_jdbcUrl" ;
	 public volatile static String DDY_PARTNERPN_USER		 = "SELECT distinct(userId) FROM ddy_partner.pn_user" ;		// 获取合伙人的用户
	 public volatile static String FN_DM_DAY_PROFIT			 = "{?= call  `ddy_partner`.`fn_dm_daily_stat`(?,now())}" ;	// 合伙人每日统计的函数
	 public volatile static String FN_DM_DAY_PROFIT_CONFIG = "{?= call  `ddy_partner`.`fn_dm_daily_stat`(?,?)}" ;		// 合伙人每日统计的函数
	 
}