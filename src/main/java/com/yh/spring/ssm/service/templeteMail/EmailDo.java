package com.yh.spring.ssm.service.templeteMail;


import java.util.List;
import java.util.Map;

/**
 * Email Do
 *
 * @author dongjs
 * @since 16/1/20
 */

public class EmailDo {
    private String template;
    private List<String> to;
    private List<String> cc;

    private String subject;
    private Map<String, Object> context;
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Map<String, Object> getContext() {
		return context;
	}
	public void setContext(Map<String, Object> context) {
		this.context = context;
	}
    
}
