package com.yh.spring.ssm.service.rdsfuntion.iservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yh.spring.ssm.bean.RdsSlowlogItem;
import com.yh.spring.ssm.bean.SlowSqlForMail;
import com.yh.spring.ssm.bean.TableSize;
import com.yh.spring.ssm.public_dao.DaoSupport;
import com.yh.spring.ssm.service.rdsfuntion.RdsSlowSqlService;
@ SuppressWarnings( "unchecked" )
@Component(value="RdsSlowSqlServiceImpl")
public class RdsSlowSqlServiceImpl implements RdsSlowSqlService {
@ Resource( name = "daoSupport" )
	 private DaoSupport dao ;


	@Override
	public void save_rdsSlowSqlService(List<RdsSlowlogItem> sqlItem) throws Exception {
		dao.save("rdsslowsql.saveslowsql", sqlItem);

	}


	@Override
	public List<RdsSlowlogItem> selectSlowSql(String  nowtime) throws Exception {
		List<RdsSlowlogItem> list = (List<RdsSlowlogItem>) dao.findForList("rdsslowsql.selectslowsql", nowtime);
		if(list != null){
			return list;
		}
		return null;
	}


	@Override
	public List<SlowSqlForMail> selectSlowForMail(String nowtime)
			throws Exception {
		List<SlowSqlForMail> list = (List<SlowSqlForMail>) dao.findForList("rdsslowsql.selectSlowForMail", nowtime);
		if(list != null){
			return list;
		}
		return null;
	}
	
	
	//获取数据库表的大小
	@Override
	public List<TableSize> selectSlowForTable(String nowtime)
			throws Exception {
		List<TableSize> list = (List<TableSize>) dao.findForList("rdsslowsql.selectSlowForTable", nowtime);
		if(list != null){
			return list;
		}
		return null;
	}
	

}
