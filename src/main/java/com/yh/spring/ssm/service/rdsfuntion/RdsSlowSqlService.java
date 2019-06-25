package com.yh.spring.ssm.service.rdsfuntion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yh.spring.ssm.bean.RdsSlowlogItem;
import com.yh.spring.ssm.bean.SlowSqlForMail;
import com.yh.spring.ssm.bean.TableSize;
@ Service
public interface RdsSlowSqlService {
	
	//保持慢sql
	void save_rdsSlowSqlService(List<RdsSlowlogItem>  sqlItem) throws Exception;
	//查询
	List<RdsSlowlogItem> selectSlowSql(String  nowtime) throws Exception;
	//查询mail所需的数据
	List<SlowSqlForMail> selectSlowForMail(String nowtime) throws Exception;
//	过去数据库表的大小
	List<TableSize> selectSlowForTable(String nowtime) throws Exception;
}
