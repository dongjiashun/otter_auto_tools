package com.yh.spring.ssm.service.sendmail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yh.spring.ssm.bean.SlowSqlForMail;
import com.yh.spring.ssm.bean.SqlwhiteList;
import com.yh.spring.ssm.bean.TableSize;
import com.yh.spring.ssm.sendMail.MailUtils;
import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsSlowSqlServiceImpl;
import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsWhiteListDbServiceImpl;
import com.yh.spring.ssm.util.DateUtil;

/**
 * 用户发送邮件
 * @author dongjs
 *
 */
@SuppressWarnings({ "unused", "unchecked" })
public class RdsSendMailService {
	private static Logger logger = Logger.getLogger(RdsSendMailService.class);
//	private ApplicationContext ac = null;
 
	public static ClassPathXmlApplicationContext ac ;
	private static int a = 1;
	private static RdsSlowSqlServiceImpl service;
	private static RdsWhiteListDbServiceImpl whiteservice;
	private String nowtime =DateUtil.formatDate(new Date());
//	private Map<String, String> depCodeNameMap = new HashMap<>();

	public  void sendSqlMail() {
		//1.从mysql获取mail所需的信息
		try {
			if ( ac == null ) {
				 ac = new ClassPathXmlApplicationContext( "ApplicationContext_mybatis_ehcache.xml" ) ;
				 service = ( RdsSlowSqlServiceImpl ) ac.getBean( "RdsSlowSqlServiceImpl" ) ;
			}
//			List<RdsSlowlogItem> list = service.selectSlowSql(DateUtil. formatDate(new Date()));;
			List<TableSize>list = service.selectSlowForTable(nowtime);
			infoAddHtml(list);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			logger.error(e);
		}
		
		//3.调用邮箱发送的接口发送短息
		//3.短信成功记录日志
	}
	//2.添加到html模板里
	
	private void infoAddHtml(List<TableSize> Rds100msList) {

        try {
//---------------------------html 抬头-----------------------------------------------
            long endOfYesterday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
            long startOfYesterday = endOfYesterday - 24 * 60 * 60 * 1000;

            StringBuilder htmlContent = new StringBuilder("<html><body>");
            htmlContent.append("<style>\n" +
                "table {\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "\n" +
                "table, td, th {\n" +
                "    border: 1px solid black;\n" +
                "}\n" +
                "td {\n" +
                "   padding-left:10px;\n" +
                "   word-wrap:break-word;word-break:break-all;\n" +
                "}\n" +
                "th{\n" +
                "   align:center;\n" +
                "}\n" +
                "</style>");
            htmlContent.append("<div style=\"padding-left:30px\">");
            htmlContent.append("<h2>数据库表空间大小统计日报-" + LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "</h2>");

            htmlContent.append("<h3>主要内容</h3>");
            htmlContent.append("<ul>");
            htmlContent.append("<li>统计汇总</li>");
            htmlContent.append("<li>大于0.5G表大小数据库 Top 50</li>");
            htmlContent.append("</ul>");
            htmlContent.append("<h3><strong>数据库空间大小排序汇总</strong></h3>\n" +
                "<table style=\"border-color: 1px;width:350px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
//                "<th width=\"150px\">表空间</th>\n" +
//                "<th  width=\"200px\">单位G/w</th>\n" +
                "</tr>");
//            TreeMap<Long, String> depMap = getSqlSumByDep(startOfYesterday, endOfYesterday);



//--------------------------------表空间统计明细------------------------------------------------
               String nowtime= DateUtil. formatDate(new Date());
               //获取mail所需的数据
            htmlContent.append("<h3><strong>超过1G表的大小 Top 50(按照表大小排序)</strong></h3>\n" +
                "<table style=\"border-color: 1px;max-width:1400px\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<th width=\"150px\">数据库名</th>\n" +
                "<th  width=\"800px\">表名</th>\n" +
                "<th  width=\"80px\">表的行数（单位为w）</th>\n" +
                "<th  width=\"80px\">表的大小（单位为G）</th>\n" +
                "<th  width=\"80px\">表的碎片率</th>\n" +
                "</tr>");
            if (CollectionUtils.isNotEmpty(Rds100msList)) {
                for (TableSize rdsSlowSqlVO : Rds100msList) {
                    htmlContent.append("<tr>\n")
                        .append("<td>" + rdsSlowSqlVO.getDatabasename() + "</td>\n")
                        .append("<td>" + rdsSlowSqlVO.getTablename() + "</td>\n")
                        .append("<td>" + rdsSlowSqlVO.getTablerow() + "</td>\n")
                        .append("<td>" + rdsSlowSqlVO.getTablesize() + "</td>\n")
                        .append("<td>" + rdsSlowSqlVO.getFragmentpecent() + "</td>\n")
                        .append("</tr>");
                }

                htmlContent.append("</tbody></table>");
            } else {
            	logger.info("no slow table found");
                return;
            }
            htmlContent.append("</div>");
            htmlContent.append("</body></html>");
           
            MailUtils.sendMail( htmlContent.toString());
            //发送邮件
            logger.info("send  slowtable mail done");
        } catch (Exception e) {
        	logger.error("fail to send slowtable mail", e);
        }
	}
	

	 
	 public static void main(String[] args) {
		 new RdsSendMailService().sendSqlMail();
	}
}
