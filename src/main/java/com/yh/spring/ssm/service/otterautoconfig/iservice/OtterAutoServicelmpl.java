package com.yh.spring.ssm.service.otterautoconfig.iservice;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yh.spring.ssm.bean.otter.Otter_DATA_MEDIA;
import com.yh.spring.ssm.bean.otter.Otter_DATA_MEDIA_kid;
import com.yh.spring.ssm.public_dao.DaoSupport;
import com.yh.spring.ssm.service.otterautoconfig.OtterAutoService;
@ SuppressWarnings( "unchecked" )
@Component(value="OtterAutoServicelmpl")
public class OtterAutoServicelmpl implements OtterAutoService {
@ Resource( name = "daoSupport" )
	 private DaoSupport dao ;


//	@Override
//	public void save_rdsSlowSqlService(List<RdsSlowlogItem> sqlItem) throws Exception {
//		dao.save("rdsslowsql.saveslowsql", sqlItem);
//
//	}
//
//
//	@Override
//	public List<RdsSlowlogItem> selectSlowSql(String  nowtime) throws Exception {
//		List<RdsSlowlogItem> list = (List<RdsSlowlogItem>) dao.findForList("rdsslowsql.selectslowsql", nowtime);
//		if(list != null){
//			return list;
//		}
//		return null;
//	}
//
//
//	@Override
//	public List<SlowSqlForMail> selectSlowForMail(String nowtime)
//			throws Exception {
//		List<SlowSqlForMail> list = (List<SlowSqlForMail>) dao.findForList("rdsslowsql.selectSlowForMail", nowtime);
//		if(list != null){
//			return list;
//		}
//		return null;
//	}
//	
//	
//	//获取数据库表的大小
//	@Override
//	public List<TableSize> selectSlowForTable(String nowtime)
//			throws Exception {
//		List<TableSize> list = (List<TableSize>) dao.findForList("rdsslowsql.selectSlowForTable", nowtime);
//		if(list != null){
//			return list;
//		}
//		return null;
//	}


	@Override
	public void create_DATA_MEDIA(String table,String database) {
		//获取DATA_MEDIA表里的最大id
		Otter_DATA_MEDIA_kid kid= new Otter_DATA_MEDIA_kid();
		Otter_DATA_MEDIA datemedia = new Otter_DATA_MEDIA();
		Date nowdate = new Date();
		long ts  = nowdate.getTime()/1000;
		kid.setDriver("com.mysql.jdbc.Driver");
		kid.setEncode("UTF8");
		kid.setGmtCreate(ts);
		kid.setGmtModified(ts);
		kid.setId(2);
		kid.setName("xxxxx");
		kid.setPassword("xxxxxxxx");
		kid.setType("MYSQL");
		kid.setUrl("jdbc:mysql://xxxxxx:3306");
		kid.setUsername("xxxxxx");
		datemedia.setMode("SINGLE");
		//table名称
		datemedia.setName("Account");
		//数据库名名称
		datemedia.setNamespace("account");
		datemedia.setSource(kid);
		//表数据中的json格式数据
		JSONObject json = (JSONObject) JSONObject.toJSON(datemedia);
		
		String datemediajson = json.toString();
		
	}


	@Override
	public void create_CHANNEL() {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void create_PIPELINE() {
		// TODO 自动生成的方法存根
		
	}
	public static void main(String[] args) {
		OtterAutoServicelmpl test = new OtterAutoServicelmpl();
	}


	@Override
	public void create_DATA_MEDIA_PAIR() {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public int find_DATA_MEDIA_Maxid() {
		// TODO 自动生成的方法存根s
		int a = 0;
		try {
			a = (int) dao.findForObject("otterauto.find_DATA_MEDIA_Maxid", null);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return a;
	}
	
	@Override
	public int find_DATA_MEDIA_PAIR_Maxid() {
		// TODO 自动生成的方法存根s
		int a = 0;
		try {
			a = (int) dao.findForObject("otterauto.find_DATA_MEDIA_PAIR_Maxid", null);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return a;
	}


	@Override
	public int find_CHANNEL_Maxid() {
		// TODO 自动生成的方法存根
		return 0;
	}


	@Override
	public int find_PIPELINE_Maxid() {
		// TODO 自动生成的方法存根
		return 0;
	}




	@Override
	public int source_insert_DATA_MEDIA(Map date) {
		int a = 0;
		try {
			a = (int) dao.save("otterauto.source_insert_DATA_MEDIA", date);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public int target_insert_DATA_MEDIA(Map date) {
		int a = 0;
		try {
			a = (int) dao.save("otterauto.target_insert_DATA_MEDIA", date);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return a;
	}
	
	@Override
	public int insert_DATA_MEDIA_PAIR(Map date) {
		int a = 0;
		try {
			a = (int) dao.save("otterauto.insert_DATA_MEDIA_PAIR", date);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return a;
	}
	@Override
	public int find_DATA_MEDIA_PAIR_id(Map date) {
		int a = 0;
		try {
			a = (int) dao.findForObject("otterauto.find_DATA_MEDIA_PAIR_id", date);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return a;
	}
	

}
