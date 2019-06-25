package com.yh.spring.ssm.service.otterautoconfig;

import java.util.Map;

import org.springframework.stereotype.Service;

@ Service
public interface OtterAutoService {

//	第一步导入数据库信息建立，源和目标表
	void create_DATA_MEDIA(String table, String database);
//	第二步，创建channel管道
	void create_CHANNEL();
//	第三步，建立pipeline
	void create_PIPELINE();
//	第四步，建立表的映射关系管理pipeline
	void create_DATA_MEDIA_PAIR();
//	获取表的最大maxid
	int find_DATA_MEDIA_Maxid();
	int find_CHANNEL_Maxid();
	int find_PIPELINE_Maxid();
	int find_DATA_MEDIA_PAIR_Maxid();
	
	int source_insert_DATA_MEDIA(Map date);
	int target_insert_DATA_MEDIA(Map date);
	int find_DATA_MEDIA_PAIR_id(Map date);
	int insert_DATA_MEDIA_PAIR(Map date);
	
	
}
