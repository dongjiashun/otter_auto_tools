package com.yh.spring.ssm.service.rdsfuntion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yh.spring.ssm.bean.RdsWhiteListDb;
import com.yh.spring.ssm.bean.SqlwhiteList;
@ Service
public interface RdsWhiteListDbService {

	
	//获取监测的数据库和实例
	List<RdsWhiteListDb> rdsWhiteListDb()throws Exception ;
	
	//获取团队统计sql数目
	List<SqlwhiteList> sqlwhiteList(String time) throws Exception;
	
}
