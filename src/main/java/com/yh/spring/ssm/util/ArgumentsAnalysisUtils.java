package com.yh.spring.ssm.util ;

import java.security.InvalidAlgorithmParameterException ;
import java.security.InvalidParameterException ;
import java.util.ArrayList ;
import java.util.Date ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import org.apache.commons.lang3.StringUtils ;
import org.apache.commons.lang3.time.DateFormatUtils ;
import org.apache.commons.lang3.time.DateUtils ;

/**
 * 程序标准开始类
 * 
 * @author dognjs
 *
 */
public class ArgumentsAnalysisUtils {
	 // 特殊参数说明
	 // start_date 开始时间
	 // end_date 结束时间
	 // forcs 强制执行 单个用户默认为强制模式
	 // shop_id user_id
	 // mode 删除模式
	 // special= 名字修改时间（精确到小时） 特殊执行
	 
	 /**
	  * 程序说明
	  */
	 private String appName ;
	 
	 public ArgumentsAnalysisUtils( String app ) {
		  appName = app ;
		  for ( String hp : helps ) {
				hparams.add( hp ) ;
		  }
	 }
	 
	 private String		 helps[]	= new String[] { "--help (展示所有命令)  " ,
	         // "--start_date=2013-01-31 (统计日的开始时间, 如果不输入end_date 默认和开始日期一致)",
	         // "--end_date=2013-01-31 (统计日的结束时间,如果不输入start_date 默认和结束日期一致)",
	         "--?=?  (程序中的其他参数 格式规范 ?=?)" } ;
	 
	 private List<String> hparams	= new ArrayList<String>( ) ;
	 
	 /**
	  * 添加使用说明
	  * 
	  * @param params
	  */
	 public void add( String params ) {
		  hparams.add( params ) ;
	 }
	 
	 /**
	  * 处理输入的参数
	  * 
	  * @param args
	  * @return
	  * @throws InvalidParameterException
	  */
	 public Map<String , String> parse( String args[] ) throws InvalidAlgorithmParameterException {
		  Map<String , String> params = new HashMap<String , String>( ) ;
		  // if (args == null || args.length == 0)
		  // return params;
		  if ( args != null && args.length == 1 && StringUtils.equals( args[0] , "--help" ) ) {
				System.out.println( print( ) ) ;
				System.exit( 0 ) ;
		  }
		  for ( String arg : args ) {
				
				try {
					 if ( StringUtils.equals( "--help" , arg ) ) {
						  System.out.println( print( ) ) ;
						  System.exit( 0 ) ;
					 }
					 if ( arg.startsWith( "--" ) ) {
						  String values[] = arg.split( "=" ) ;
						  params.put( values[0].substring( 2 ) , values[1] ) ;
					 }
					 else {
						  throw new InvalidAlgorithmParameterException( " 非法参数.. args!" ) ;
					 }
				}
				catch ( Exception e ) {
					 System.out.println( print( ) ) ;
					 throw new InvalidAlgorithmParameterException( " 非法参数.. args!" ) ;
				}
		  }
		  
		  if ( !params.containsKey( "start_date" ) && !params.containsKey( "end_date" ) ) {
				// Date cdate = DateUtils.addDays(new Date(), -1);
				Date cdate = DateUtils.addDays( new Date( ) , 0 ) ;
				
				String sdate = DateFormatUtils.format( cdate , "yyyy-MM-dd" ) ;
				params.put( "start_date" , sdate ) ;
				params.put( "end_date" , sdate ) ;
		  }
		  if ( !params.containsKey( "start_date" ) ) {
				params.put( "start_date" , params.get( "end_date" ) ) ;
		  }
		  System.out.println( "------------------------------------" ) ;
		  for ( String param : params.keySet( ) ) {
				System.setProperty( param , params.get( param ) ) ;
				System.out.println( param + ":" + params.get( param ) ) ;
		  }
		  System.out.println( "------------------------------------" ) ;
		  
		  return params ;
	 }
	 
	 public String print( ) {
		  StringBuilder msg = new StringBuilder( ) ;
		  msg.append( "\n" ) ;
		  msg.append( "\n" ) ;
		  msg.append( "=========================================\n" ) ;
		  msg.append( "------- " + appName + " 参数设置说明     -----------\n" ) ;
		  for ( String hp : hparams ) {
				msg.append( " " + hp + " \n" ) ;
		  }
		  msg.append( "=========================================\n" ) ;
		  msg.append( "\n" ) ;
		  msg.append( "\n" ) ;
		  System.out.println( msg.toString( ) ) ;
		  return msg.toString( ) ;
	 }
	 
}
