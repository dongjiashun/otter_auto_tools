package com.yh.spring.ssm.util ;

import java.io.FileInputStream ;
import java.io.IOException ;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.util.ArrayList ;
import java.util.Map ;
import java.util.Properties ;

/**
 * dongjs专用连接池
 *
 */
public class DataBaseConnection {
	 // rm-2zet6too661lc9bn5rw.mysql.rds.aliyuncs.com
	 // rm-2zet6too661lc9bn5rw.mysql.rds.aliyuncs.com
	 // private String MYSQLURL="rm-2zet6too661lc9bn5rw.mysql.rds.aliyuncs.com";
	 // 负载均衡
	 @ SuppressWarnings( "unused" )
	 private static String					  MYSQLURL				= "rm-2zet6too661lc9bn5rw.mysql.rds.aliyuncs.com" ;
	 // 主实例
	 @ SuppressWarnings( "unused" )
	 private static String					  MYSQLURL_master		= "rm-2zet6too661lc9bn5o.mysql.rds.aliyuncs.com" ;
	 @ SuppressWarnings( "unused" )
	 private static String					  MYSQLURL_master_me	= "rds9t173kxh38h4yk213o.mysql.rds.aliyuncs.com" ;
	 
	 // private static int maxConnectNum = 50;
	 private static int						  a ;
	 
	 private static java.sql.Connection	  conn[]					= new Connection[sqlCollection.JDBCNUM] ;
	 
	 @ SuppressWarnings( { "rawtypes" , "unchecked" } )
	 private static ArrayList<Connection> connectPool			= new ArrayList( ) ;
	 
	 private static Map<String , String>  systemMap				= System.getenv( ) ;
	 private static int						  flag					= 0 ;
	 // 加载属性文件，读取数据库连接配置信息
	 private static Properties				  pro						= new Properties( ) ;
	 
	 public DataBaseConnection( ) {
		  if ( DataBaseConnection.flag == 0 ) {
				init( ) ;
		  }
	 }
	 
	 public void readProperties( ) {
		  Map<String , String> map = System.getenv( ) ;
		  if ( map.keySet( ).contains( "DDY_RESOURCES_DIR" ) ) {
				System.out.println( map.get( "DDY_RESOURCES_DIR" ) ) ;
		  }
	 }
	 
	 // private static Connection getConnectionFromDatabase() {
	 // Connection trueConn = null;
	 // try {
	 // System.out.println("负载均衡=====实例");
	 // // Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	 // Class.forName("com.mysql.jdbc.Driver").newInstance();
	 // trueConn = DriverManager.getConnection(
	 // "jdbc:mysql://" + MYSQLURL_master_me
	 // + ":3306?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
	 // "dev_dongjs", "lo6*!l@PSn$^xZ1y");
	 // System.out.println("数据库连接成功" + "\t" + a++);
	 // } catch (Exception ex) {
	 // System.out.println("数据连接出错了:" + ex.toString());
	 // }
	 // return trueConn;
	 // }
	 private static Connection getConnectionFromDatabase( ) {
		  Connection connection = null ;
		  String filePath = "" ;
		  
		  try {
				try {
					 if ( systemMap.keySet( ).contains( "DDY_RESOURCES_DIR" ) ) {
						  if ( System.getProperty( "os.name" ).contains( "Linux" ) ) {
								filePath = systemMap.get( "DDY_RESOURCES_DIR" ) + "/jdbc.properties" ;
								System.out.println( filePath ) ;
						  }
						  else if ( System.getProperty( "os.name" ).contains( "Windows" ) ) {
								filePath = systemMap.get( "DDY_RESOURCES_DIR" ) + "/jdbc.properties" ;
								System.out.println( filePath ) ;
						  }
					 }
					 System.out.println( "--正式--地址--" + filePath ) ;
					 FileInputStream is = new FileInputStream( filePath ) ;
					 pro.load( is ) ;
				}
				catch ( IOException e ) {
					 System.out.println( "---error---未找到配置文件！！！" + e ) ;
				}
				String url = pro.getProperty( sqlCollection.DDYDB_JDBCURL ) ;
				String user = pro.getProperty( "ddy_partner_user" ) ;
				String password = pro.getProperty( "ddy_partner_password" ) ;
				connection = DriverManager.getConnection(
				        url + "?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull" , user , password ) ;
				System.out.println( "数据库连接成功" + "\t" + a++ ) ;
		  }
		  catch ( SQLException e ) {
				System.out.println( "数据连接出错了:" + e.toString( ) ) ;
		  }
		  return connection ;
	 }
	 
	 // 这里建立所有的连接;
	 private static void init( ) {
		  for ( int i = 0 ; i < sqlCollection.JDBCNUM ; i++ ) {
				conn[i] = getConnectionFromDatabase( ) ;
				DataBaseConnection.connectPool.add( i , conn[i] ) ;
		  }
		  DataBaseConnection.flag = 1 ;
	 }
	 
	 // 从连接池中取得一个可用的连接
	 public static Connection getConnection( ) {
		  Connection conn = null ;
		  if ( DataBaseConnection.connectPool.size( ) == 0 ) {
				try {
					 java.lang.Thread.sleep( 1000 ) ;
					 getConnection( ) ;
				}
				catch ( InterruptedException ex ) {
					 System.out.println( "连接全部用光,这里sleep出错了." ) ;
				}
		  }
		  else {
				conn = ( Connection ) DataBaseConnection.connectPool.remove( 0 ) ;
		  }
		  return conn ;
	 }
	 
	 // 提供给外部程序调用,不用的连接放回连接池当中...
	 public static boolean release( Connection conn ) {
		  return DataBaseConnection.connectPool.add( conn ) ;
	 }
	 
	 @ SuppressWarnings( "static-access" )
	 public static void main( String[] args ) {
		  System.out.println( System.getProperty( "os.name" ) ) ;
		  ;
		  DataBaseConnection test = new DataBaseConnection( ) ;
		  Connection con = test.getConnection( ) ;
		  test.release( con ) ;
	 }
	 
	 // 关闭所有连接对象
	 public static void closeAllConnection( ) {
		  if ( DataBaseConnection.connectPool.size( ) > 0 ) {
				for ( Connection connection : DataBaseConnection.connectPool ) {
					 try {
						  connection.close( ) ;
					 }
					 catch ( SQLException e ) {
						  e.printStackTrace( ) ;
					 }
				}
		  }
		  DataBaseConnection.connectPool.clear( ) ;
		  System.out.println( "---------数据库连接池--连接对象00以关闭" + "！！！" ) ;
		  
	 }
}