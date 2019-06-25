///**
// * 
// */
//package com.yh.spring.ssm.test;
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.yh.spring.ssm.bean.RdsSlowlogItem;
//import com.yh.spring.ssm.bean.RdsWhiteListDb;
//import com.yh.spring.ssm.bean.SlowSqlForMail;
//import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsSlowSqlServiceImpl;
//import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsWhiteListDbServiceImpl;
//import com.yh.spring.ssm.util.DateUtil;
//
//
////@RunWith(SpringJUnit4ClassRunner.class)		
////@ContextConfiguration(locations = {"classpath:ApplicationContext_mybatis_ehcache.xml"})
//
//public class TestMyBatis {
//	private static Logger logger = Logger.getLogger(TestMyBatis.class);
////	private ApplicationContext ac = null;
//
//	public static ClassPathXmlApplicationContext ac ;
//	private static int a = 1;
////	static RdsSlowSqlServiceImpl service;
//	static RdsWhiteListDbServiceImpl service;
//	
//	
//	
//	
//	//----------------------------------------------
//	public static Map<String, List<String>> sessionkeys = new HashMap<String, List<String>>();
//	public static boolean isReadFile = false;
//	public static String sessionPath = "/root/koolbao_apps/sessions/";
////	public static String sessionPath = "/root/koolbao_apps/sessions/";
//
////	@Before
////	public void before() {
////		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext_mybatis_ehcache.xml");
////		userService = (IUserService) ac.getBean("UserService");
////	}
//
//	@Test
//	@Cacheable("yh1")
//	public void test1() {
//System.out.println("---------环境测试" ) ;
//	}
//	
////	//获取insert插入返回值得  
////	@Test
////	public void test2(){
////		final User user = new User();
////		user.setAge(23);
////		user.setPassword("dongjs");
////		user.setUser_name("dong董佳顺js");
////		System.out.println("插入前的自增id是"+user.id);
//////		这个代表着是插入多少行
////		int dongjs = userService.insert(user);
////		//可以获取插入最后的id号
////		System.out.println("插入后端额自增id："+user.id);
////		
////		System.out.println("userService.insert(user);"+"/t"+dongjs);
////		
////	}
//	
//	
//	
//
//	public static void main(String[] args) {
//		List<String> whitedb = null;
//		try {
//			if (isReadFile) {
//				whitedb = FileUtils.readLines(new File(sessionPath ));
//			} else {
//				
//			}
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//
//	}
//	
//	public static void main1(String[] args) {
//
//		try {
//			if ( ac == null ) {
//				 ac = new ClassPathXmlApplicationContext( "ApplicationContext_mybatis_ehcache.xml" ) ;
//				 service = ( RdsWhiteListDbServiceImpl ) ac.getBean( "RdsWhiteListDbServiceImpl" ) ;
//				 
//			}
//			List<RdsWhiteListDb> list = service.rdsWhiteListDb();
////			for (RdsWhiteListDb RdsWhiteListDb : list) {
////				System.out.println("RdsWhiteListDb"+"\t"+RdsWhiteListDb);
////			}
//			list.forEach(rwl ->{ 
//				String dbinstance = rwl.getDbinstance();
//				String[] listname = dbinstance.split("\\.");
//				rwl.setDbinstance(listname[0]);
//				System.out.println(rwl.getDbinstance());
//			});  	
////			for (RdsWhiteListDb rdsWhiteListDb : list) {
////				System.out.println(rdsWhiteListDb);
////			}
//		} catch (Exception e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//	
//	}
//}
