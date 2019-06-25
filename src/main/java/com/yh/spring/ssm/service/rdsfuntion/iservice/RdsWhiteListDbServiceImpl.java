package com.yh.spring.ssm.service.rdsfuntion.iservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yh.spring.ssm.bean.RdsWhiteListDb;
import com.yh.spring.ssm.bean.SqlwhiteList;
import com.yh.spring.ssm.public_dao.DaoSupport;
import com.yh.spring.ssm.service.rdsfuntion.RdsWhiteListDbService;
@ SuppressWarnings( "unchecked" )
@Component(value="RdsWhiteListDbServiceImpl")
public class RdsWhiteListDbServiceImpl implements RdsWhiteListDbService {
	@ Resource( name = "daoSupport" )
	 private DaoSupport dao ;
	
	@Override
	public List<RdsWhiteListDb> rdsWhiteListDb() throws Exception {
		 List<RdsWhiteListDb> list = (List<RdsWhiteListDb>) dao.findForList("rdswhiteListdb.rdsWhiteListDb", null);
		 if(!list.isEmpty()){
			 return list;
		 }else{
		return null;
		 }
	}

	@Override
	public List<SqlwhiteList> sqlwhiteList(String time) throws Exception {
		 List<SqlwhiteList> list = (List<SqlwhiteList>) dao.findForList("rdswhiteListdb.sqlwhiteList", time);
		 if(!list.isEmpty()){
			 return list;
		 }else{
		return null;
		 }
	}
}
