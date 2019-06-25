package com.yh.spring.ssm.sendMail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.yh.spring.ssm.service.templeteMail.EmailDo;
import com.yh.spring.ssm.service.templeteMail.EmailService;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailUtils {

    public static void sendMail(Mail mail,Session session) {

        mail.setFrom(MailConfig.getString("userName"));
        try {
            Transport transport = session.getTransport(); // 获取传输对象
            transport.connect();
            Message message = new MimeMessage(session); // 邮件消息对象
            message.setFrom(mail.getFrom()); // 发送者
            message.setSubject(mail.getSubject());
            if (mail.getContent() != null) {
                message.setContent(mail.getContent(), mail.getContentType());
            } else {
                message.setContent(mail.getMultipart());
            }

            message.setRecipients(Message.RecipientType.TO, mail.getTo());
            message.setRecipients(Message.RecipientType.CC, mail.getCc());
            message.setRecipients(Message.RecipientType.BCC, mail.getBcc());

            transport.sendMessage(message, message.getAllRecipients()); // 发送邮件
            transport.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void prepareSendMail(String html,String send){
//    	判断是否发邮件
    	if(StringUtils.isNotBlank(send)&&send.equals("true")){
        	html = html.replaceAll("告警主机", "<br/>告警主机");
        	html = html.replaceAll("主机IP", "<br/>主机IP");
        	html = html.replaceAll("告警时间", "<br/>告警时间");
        	html = html.replaceAll("告警等级", "<br/>告警等级");
        	html = html.replaceAll("告警信息：", "<br/>告警信息：");
        	html = html.replaceAll("问题详情", "<br/>问题详情");
        	html = html.replaceAll("事件ID", "<br/>事件ID");
        	html = html.replaceAll("产生时间", "<br/>产生时间");
        	html = html.replaceAll("恢复时间", "<br/>恢复时间");
    		MailUtils.sendMail(html);
    		System.out.println("邮件发送成功");
    	}
    }
    
    public static void sendMail(String html){
        Session session = Session.getInstance(MailConfig.build("resources/mail.properties"),
                new MailAuthenticator(MailConfig.getString("userName"), MailConfig.getString("passWord")));
        	String sendWhos = MailConfig.getString("sendWhos");
        	String subject = MailConfig.getString("subject");
    	  Mail mail = new Mail();
          mail.setSubject(subject);
          mail.setContentType("text/html;charset=utf-8");
          mail.setContent(html);
          mail.setTo(sendWhos);//输入邮箱
          mail.setCc(sendWhos);
          mail.setBcc(sendWhos);
          MailUtils.sendMail(mail,session);
    }
//    public static void main(String[] args) {
//      Mail mail = new Mail();
//      mail.setSubject("国庆中秋8天乐");
//      mail.setContentType("text/html;charset=utf-8");
//      mail.setContent("放假啦");
////      mail.setBodyContent("放假啦");
////      List<String> files = new ArrayList<String>();
////      files.add("src/main/resources/mail.properties");//添加附件
////      files.add("src/main/resources/邮件测试.txt");
////      mail.setFilePathes(files);
////      mail.setTo("1115170465@qq.com");//输入邮箱
////      mail.setCc("1115170465@qq.com");
////      mail.setBcc("1115170465@qq.com");
//      mail.setTo("22216@etransfar.comm");//输入邮箱
//      mail.setCc("22216@etransfar.com");
//      mail.setBcc("22216@etransfar.com");
//      MailUtils.sendMail("testttest");
//  }
//  
    
    public static void main(String[] args) {
//    	String txt = "Zabbix商用环境异常告警信息<br/>告警主机：db-tffwalletmysql-1-m1 <br/>主机IP： 10.33.102.120 <br/>告警时间：2019.05.03 18:32:40 \n\r告警等级：Average <br/>告警信息：db-tffwalletmysql-1-m1Mysql Query_timeout_10s <br/>问题详情：mysql_Query_timeout_10s:3 <br/>事件ID： 31754939";
    	String txt = "Zabbix商用环境异常告警信息 告警主机：db-tffwalletmysql-1-m1 主机IP： 10.33.102.120 告警时间：2019.05.03 18:32:40 告警等级：Average 告警信息：db-tffwalletmysql-1-m1Mysql Query_timeout_10s 问题详情：mysql_Query_timeout_10s:3 事件ID： 31754939";
//    	String newString = txt.replaceAll("(\r\n|\r|\n|\n\r)", "<br/>");
    	txt = txt.replaceAll("告警主机", "<br/>告警主机");
    	txt = txt.replaceAll("主机IP", "<br/>主机IP");
    	txt = txt.replaceAll("告警时间", "<br/>告警时间");
    	txt = txt.replaceAll("告警等级", "<br/>告警等级");
    	txt = txt.replaceAll("告警信息：", "<br/>告警信息：");
    	txt = txt.replaceAll("问题详情", "<br/>问题详情");
    	txt = txt.replaceAll("事件ID", "<br/>事件ID");
    	txt = txt.replaceAll("产生时间", "<br/>产生时间");
    	txt = txt.replaceAll("恢复时间", "<br/>恢复时间");
    	System.out.println(txt);
//    	MailUtils.prepareSendMail(txt,"true");
	}
}
