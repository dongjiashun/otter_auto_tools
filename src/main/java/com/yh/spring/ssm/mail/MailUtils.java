package com.yh.spring.ssm.mail;

import sun.security.krb5.Config;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @version :1.0
 * CREATE TIME 2017/12/6 11:41
 * @authro :dongjs
 */
public class MailUtils  {


    public static void sendMail1(Main main) throws  Exception{
        Properties properties = ConfigUtil.build("resources/mail.properties");
        Session session = Session.getInstance(properties,new MailAuthenticator(ConfigUtil.getString("userName"),ConfigUtil.getString("passWord")));
        Message message = new MimeMessage(session);
        main.setFrom(ConfigUtil.getString("userName"));//邮件发送者
        message.setSubject(main.getSubject());//邮件主题
        message.setFrom(main.getFrom());//邮件接收者
        if(main.getContent()!=null){
            message.setContent(main.getContent(),main.getContentType());//邮件正文，第一个参数为内容，第二个为参数
        }else{
            Multipart multipart = new MimeMultipart();
            BodyPart contentBody = new MimeBodyPart();
            contentBody.setContent(main.getBodyContent(),main.getContentType());
            multipart.addBodyPart(contentBody);
            for(String filePath : main.getAttachments()){
                BodyPart fileBody = new MimeBodyPart();
                fileBody.setDataHandler(new DataHandler(new FileDataSource(filePath)));
                multipart.addBodyPart(fileBody);
            }
            message.setContent(multipart);
        }

        message.setRecipient(Message.RecipientType.TO,main.getTo());//发送一个普通邮件To
        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message,message.getAllRecipients());
    }
   
    
    public static void sendMail(String html){

                try {
                    Main mail = new Main();
                    mail.setSubject("慢查询统计日报");
                    mail.setContent(html);
//                    mail.setContentType("text/html;charset=utf-8");

//                    mail.setBodyContent("caonimade  大爷的");
                    mail.setContentType("text/html;charset=utf-8");
                    List<String> attachments = new ArrayList<String>();
                    attachments.add("resources/mail.properties");
                 
                    mail.setAttachments(attachments);
                    //835960655
                    mail.setTo("dba@dianwoba.com");
                    mail.setCc("dba@dianwoba.com");
                    mail.setBcc("dba@dianwoba.com");
                    MailUtils.sendMail1(mail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
    
    
    }
    
//    new Thread(new Runnable() {
//        public void run() {   
//        
//        }
//}).start();
//    public static void main(String[] args)throws Exception {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Main mail = new Main();
//                    mail.setSubject("慢查询统计日报");
//                    mail.setContent("8天<a href='http://baidu.com'>进入网站</a>" +
//                            "<img src='http://b.hiphotos.baidu.com/zhidao/pic/item/a5c27d1ed21b0ef47a3cc0a7dbc451da80cb3e76.jpg' />");
//                    mail.setContentType("text/html;charset=utf-8");
//
//                    mail.setBodyContent("caonimade  大爷的");
//                    mail.setContentType("text/html;charset=utf-8");
//                    List<String> attachments = new ArrayList<String>();
//                    attachments.add("resources/mail.properties");
//                 
//                    mail.setAttachments(attachments);
//                    //835960655
//                    mail.setTo("dongjiashun1993@sina.com");
//                    mail.setCc("dongjiashun1993@sina.com");
//                    mail.setBcc("dongjiashun1993@sina.com");
//                    MailUtils.sendMail1(mail);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
   
}
