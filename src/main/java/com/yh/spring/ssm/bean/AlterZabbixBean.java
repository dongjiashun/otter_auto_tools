package com.yh.spring.ssm.bean;
import java.util.List;

/**
 * Auto-generated: 2019-05-15 10:33:43
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AlterZabbixBean {

    private String alertid;
    private String clock;
    private String message;
    private String sendto;
    private String subject;
    public void setAlertid(String alertid) {
         this.alertid = alertid;
     }
     public String getAlertid() {
         return alertid;
     }

    public void setClock(String clock) {
         this.clock = clock;
     }
     public String getClock() {
         return clock;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setSendto(String sendto) {
         this.sendto = sendto;
     }
     public String getSendto() {
         return sendto;
     }

    public void setSubject(String subject) {
         this.subject = subject;
     }
     public String getSubject() {
         return subject;
     }
	@Override
	public String toString() {
		return "AlterZabbixBean [alertid=" + alertid + ", clock=" + clock
				+ ", message=" + message + ", sendto=" + sendto + ", subject="
				+ subject + "]";
	}


}