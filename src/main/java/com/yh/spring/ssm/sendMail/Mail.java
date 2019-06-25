package com.yh.spring.ssm.sendMail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Mail {

    private String from;//来自
    private String subject;//主题
    private String content; // 普通正文
    private String contentType; // 内容类型
    private String bodyContent; // 带有附件的邮件正文
    private List<String> filePathes; // 附件的文件路径，支持多个附件
    private String to; // 可以用逗号隔开多个收件人
    private String cc; // 可以用逗号隔开多个抄送人
    private String bcc; // 可以用逗号隔开多个密送人

    public InternetAddress getFrom() {
        try {
            return InternetAddress.parse(from)[0];
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 通过指定的bodyContent及contentType组装带有附件的邮件的内容
     * 并且通过指定的filePathes路径添加相应的附件
     * @return
     */
    public Multipart getMultipart() {
        Multipart multipart = new MimeMultipart(); // 用于包含正文和附件 BodyPart
        try {
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(bodyContent, contentType);
            multipart.addBodyPart(contentPart);
            for (String path : filePathes) {
                BodyPart filePart = new MimeBodyPart();
                filePart.setDataHandler(new DataHandler(new FileDataSource(new File(path))));
                String newFileName = path.substring(path.lastIndexOf("/")+1);//截取到path中的文件名
                //防止文件名出现乱码，对文件名进行编码
                String fileName = MimeUtility.encodeText(newFileName,"utf-8",null);
                filePart.setFileName(fileName);
                multipart.addBodyPart(filePart);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return multipart;
    }

    public InternetAddress[] getTo() {
        try {
            return InternetAddress.parse(to);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public InternetAddress[] getCc() {
        try {
            return InternetAddress.parse(cc);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public InternetAddress[] getBcc() {
        try {
            return InternetAddress.parse(bcc);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public List<String> getFilePathes() {
        return filePathes;
    }

    public void setFilePathes(List<String> filePathes) {
        this.filePathes = filePathes;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }
}
