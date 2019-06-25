package com.yh.spring.ssm.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.yh.spring.ssm.bean.otter.DATA_MEDIA_PAIR;
import com.yh.spring.ssm.bean.otter.Otter_DATA_MEDIA;
import com.yh.spring.ssm.bean.otter.Otter_DATA_MEDIA_kid;
import com.yh.spring.ssm.sendMail.ZabbixConfig;
import com.yh.spring.ssm.service.otterautoconfig.iservice.OtterAutoServicelmpl;
import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsSlowSqlServiceImpl;
import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsWhiteListDbServiceImpl;

public class OtterAutoAction {
	public static ClassPathXmlApplicationContext ac;
	static OtterAutoServicelmpl otterimpl = null;
	public static void main(String[] args) {
		OtterAutoAction otter= new OtterAutoAction();
//		第一步导入数据库信息建立，源和目标表
		otter.startConfig();
		Properties properties = otter.read_properties();
		otter.autojobstart(properties);
		otter.autojobstart_table(properties);


	}

	
	
	public Properties read_properties(){
		  Properties properties = ZabbixConfig.build("resources/otter.properties");
		  return properties;
	}
	
//	启动架构配置
	public OtterAutoServicelmpl startConfig(){
		if (ac == null) {
			ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
			otterimpl = (OtterAutoServicelmpl) ac.getBean("OtterAutoServicelmpl");
			return otterimpl;
		}
		
//		int a = otterimpl.find_DATA_MEDIA_Maxid();
//		System.out.println(a);
		return null;
	}
	
	
	public  void create_DATA_MEDIA(String table,String database,Properties properties) {
		//获取DATA_MEDIA表里的最大id
		Otter_DATA_MEDIA_kid source_kid= new Otter_DATA_MEDIA_kid();
		Otter_DATA_MEDIA_kid target_kid= new Otter_DATA_MEDIA_kid();
		Otter_DATA_MEDIA source_datemedia = new Otter_DATA_MEDIA();
		Otter_DATA_MEDIA target_datemedia = new Otter_DATA_MEDIA();
		//----数据配置，自定义配置
		String source_datasource_name = (String) properties.get("source.datasource.name");
		String target_datasource_name = (String)properties.get("target.datasource.name");
		String pwd = (String)properties.get("source.datasource.pwd");
		String url = (String)properties.get("source.datasource.url");
		String username = (String)properties.get("source.datasource.username");
		String sourceid = (String)properties.get("source.datasource.id");
		String targetid = (String)properties.get("target.datasource.id");
		String targetdatabase = (String)properties.get("target.database.name");
		
		//------------------
		Date nowdate = new Date();
		long ts  = (nowdate.getTime()/1000)*1000;
		source_kid.setDriver("com.mysql.jdbc.Driver");
		source_kid.setEncode("UTF8");
		source_kid.setGmtCreate(ts);
		source_kid.setGmtModified(ts);
		source_kid.setId(Long.valueOf(sourceid));
		source_kid.setName(source_datasource_name);
		source_kid.setPassword(pwd);
		source_kid.setType("MYSQL");
		source_kid.setUrl(url);
		source_kid.setUsername(username);
		
		target_kid.setDriver("com.mysql.jdbc.Driver");
		target_kid.setEncode("UTF8");
		target_kid.setGmtCreate(ts);
		target_kid.setGmtModified(ts);
		target_kid.setId(Long.valueOf(targetid));
		target_kid.setName(target_datasource_name);
		target_kid.setPassword(pwd);
		target_kid.setType("MYSQL");
		target_kid.setUrl(url);
		target_kid.setUsername(username);
		//------------source_datemedia-------------
		source_datemedia.setMode("SINGLE");
		//table名称
		source_datemedia.setName(table);
		//数据库名名称
		source_datemedia.setNamespace(database);
		source_datemedia.setSource(source_kid);
		
		// target
		target_datemedia.setMode("SINGLE");
		target_datemedia.setName(table);
		target_datemedia.setNamespace(targetdatabase);
		target_datemedia.setSource(target_kid);
		
		JSONObject json = (JSONObject) JSONObject.toJSON(source_datemedia);
		String sourcejson = json.toString();
		System.out.println("sourcejson"+sourcejson);
		
		JSONObject targetjson = (JSONObject) JSONObject.toJSON(target_datemedia);
		String target_json = targetjson.toString();
		System.out.println("target_json"+target_json);
		int MEDIA_id = otterimpl.find_DATA_MEDIA_Maxid();
		//--------------------源表配置添加------------
		Map<String, Object> source_MEDIAMAP = new HashMap<String, Object>();
		source_MEDIAMAP.put("id", MEDIA_id+2);
		source_MEDIAMAP.put("name", table);
		source_MEDIAMAP.put("namespace", database);
		source_MEDIAMAP.put("properties", sourcejson);
		source_MEDIAMAP.put("sourceid", sourceid);
		otterimpl.source_insert_DATA_MEDIA(source_MEDIAMAP);
		//--------------------目标表配置添加------------
		Map<String, Object> target_MEDIAMAP = new HashMap<String, Object>();
		target_MEDIAMAP.put("id", MEDIA_id+2);
		target_MEDIAMAP.put("name", table);
		target_MEDIAMAP.put("namespace", targetdatabase);
		target_MEDIAMAP.put("properties", target_json);
		target_MEDIAMAP.put("targetid", targetid);
		otterimpl.target_insert_DATA_MEDIA(target_MEDIAMAP);

		
		
	}
	public  void autojobstart( Properties properties){
		try {
			List<String> filecontext = FileUtils.readLines(new File("resources/ottertable.txt"), "UTF-8");
			for (String string : filecontext) {
				String [] arr = string.split("\\s+");
				System.out.println("数据库名字："+arr[0]);
				System.out.println("表名："+arr[1]);
				//根据每个数据名字和表名 ---循环---拼接表配置数据
				create_DATA_MEDIA(arr[1], arr[0], properties);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
	}
	public  void autojobstart_table( Properties properties){
		try {
			List<String> filecontext = FileUtils.readLines(new File("resources/ottertable.txt"), "UTF-8");
			for (String string : filecontext) {
				String [] arr = string.split("\\s+");
				System.out.println("数据库名字："+arr[0]);
				System.out.println("表名："+arr[1]);
				//根据每个数据名字和表名 ---循环---拼接表配置数据
				create_DATA_MEDIA_PAIR(arr[1], arr[0], properties);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
	}
	public void create_DATA_MEDIA_PAIR(String table,String database,Properties properties){
		String sourceid = (String)properties.get("source.datasource.id");
		String targetid = (String)properties.get("target.datasource.id");
		String pipeline_id = (String)properties.get("pipeline_id");
		String targetnamespace = (String)properties.get("target.database.name");
		Map resultmap = new HashMap<String,Object>();
		Map sourcePAIRmap = new HashMap<String,Object>();
		sourcePAIRmap.put("name", table);
		sourcePAIRmap.put("namespace", database);
		sourcePAIRmap.put("pair_sourceid", sourceid);
		int sourceid_t = otterimpl.find_DATA_MEDIA_PAIR_id(sourcePAIRmap);
		Map targetPAIRmap = new HashMap<String,Object>();
		targetPAIRmap.put("name", table);
		targetPAIRmap.put("namespace", targetnamespace);
		targetPAIRmap.put("pair_sourceid", targetid);
		int targetid_t = otterimpl.find_DATA_MEDIA_PAIR_id(targetPAIRmap);
		int pair_id = otterimpl.find_DATA_MEDIA_PAIR_Maxid();
		
		DATA_MEDIA_PAIR pairbean = new DATA_MEDIA_PAIR();
		pairbean.setBlank(true);
		pairbean.setClazzPath(null);
		pairbean.setExtensionDataType("CLAZZ");
		pairbean.setNotBlank(false);
		Date nowdate = new Date();
		long ts  = nowdate.getTime();
		pairbean.setTimestamp(ts);
		
		String targetjson = JSONObject.toJSON(pairbean).toString();
//		(`ID`, `PUSHWEIGHT`, `RESOLVER`, `FILTER`, `SOURCE_DATA_MEDIA_ID`, `TARGET_DATA_MEDIA_ID`, `PIPELINE_ID`, `COLUMN_PAIR_MODE`, `GMT_CREATE`, `GMT_MODIFIED`) 
		resultmap.put("id", pair_id+2);
		resultmap.put("PUSHWEIGHT", 5);
		resultmap.put("RESOLVER", targetjson);
		resultmap.put("FILTER", targetjson);
		resultmap.put("SOURCE_DATA_MEDIA_ID", sourceid_t);
		resultmap.put("TARGET_DATA_MEDIA_ID", targetid_t);
		resultmap.put("PIPELINE_ID", pipeline_id);
		resultmap.put("COLUMN_PAIR_MODE","INCLUDE");
		otterimpl.insert_DATA_MEDIA_PAIR(resultmap);
		
		
		
		
	}
}
