package com.yh.spring.ssm.api;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.yh.spring.ssm.bean.RdsSlowlogItem;
import com.yh.spring.ssm.bean.RdsWhiteListDb;
import com.yh.spring.ssm.bean.Rds_slowsql;
import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsSlowSqlServiceImpl;
import com.yh.spring.ssm.service.rdsfuntion.iservice.RdsWhiteListDbServiceImpl;
import com.yh.spring.ssm.util.SignatureUtils;

public class AliyunApi {


	private Logger log = LoggerFactory.getLogger(AliyunApi.class);
	public static ClassPathXmlApplicationContext ac;
	public static RdsSlowSqlServiceImpl rdsSlowSqlService;
	public static RdsWhiteListDbServiceImpl rdsWhiteListDbservice;
//	private 
	
	// rds接口传参数，验证参数
	{
		Rds_slowsql.commonParam.put("Format", "JSON");
		Rds_slowsql.commonParam.put("Version", "2014-08-15");
		Rds_slowsql.commonParam.put("SignatureMethod", "HMAC-SHA1");
		Rds_slowsql.commonParam.put("SignatureVersion", "1.0");
	}

	// 慢查询日志统计
	// 慢查询请求体
	/*
	 * https://rds.aliyuncs.com/?Action=DescribeSlowLogs
	 * &DBInstanceId=riauvjz6zajfiq6ba1370329449201 &StartTime=2011-06-11Z
	 * &EndTime=2011-12-11Z &SortKey= TotalExecutionCounts &<公共请求参数>
	 */
	public void slowQueryInfo() {
		try {
			if (ac == null) {
				ac = new ClassPathXmlApplicationContext("ApplicationContext_mybatis_ehcache.xml");
				rdsSlowSqlService = (RdsSlowSqlServiceImpl) ac.getBean("RdsSlowSqlServiceImpl");
				rdsWhiteListDbservice = ( RdsWhiteListDbServiceImpl ) ac.getBean( "RdsWhiteListDbServiceImpl" ) ;

			}
			//获取要监控的白名单
			List<RdsWhiteListDb> rdswlist = rdsWhiteListDbservice.rdsWhiteListDb();
			rdswlist.forEach(rwl ->{ 
				String dbinstance = rwl.getDbinstance();
				String[] listname = dbinstance.split("\\.");
				String dbinstance1 = listname[0];
				if(StringUtils.isNotBlank(dbinstance1)){
				rwl.setDbinstance(dbinstance1);
				}
			});  	
//			for (RdsWhiteListDb rdsWhiteListDb : rdswlist) {
//				System.out.println("rdsWhiteListDb"+"\t"+rdsWhiteListDb);
//			}
		for (RdsWhiteListDb rdsWhiteListDb : rdswlist) {
		
			for (int i = 1; i < Rds_slowsql.MAXNUM; i++) {
				
//				 配置请求参数
				String dbinstance = rdsWhiteListDb.getDbinstance();
				String dbname = rdsWhiteListDb.getDbname();
				if(StringUtils.isBlank(dbinstance)&&StringUtils.isBlank(dbname)){
					break;
				}
				int sqlstatus = rdsWhiteListDb.getSlowlog();
				if(!StringUtils.isNotBlank(dbinstance)
						&&StringUtils.isNotBlank(dbname)
						&& sqlstatus !=1){
					break;
				}
				if(StringUtils.isBlank(dbinstance)||StringUtils.isBlank(dbname)||sqlstatus !=1){
					break;
				}
				TreeMap<String, String> param = parseAliyunParams(i,dbinstance,dbname);	
				String paramUrl = StringUtils.join(param.entrySet().stream()
						.map(e -> {
							return e.getKey() + "=" + e.getValue();
						}).collect(Collectors.toList()), "&");
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpGet httpget = new HttpGet(Rds_slowsql.URL + "?" + paramUrl);
				org.apache.http.protocol.HttpContext context = new BasicHttpContext();
				HttpResponse response = httpClient.execute(httpget,
						(org.apache.http.protocol.HttpContext) context);
				// 解析response
				if (HttpStatus.SC_OK == response.getStatusLine()
						.getStatusCode()) {
					String json = EntityUtils.toString(response.getEntity());
					/**
					 * TotalRecordCount Integer 总记录数 PageNumber Integer 页码
					 * PageRecordCount Integer 本页SQL语句个数 Items List<SQLSlowLog>
					 */
					Map<String, Object> map_params = getJSONFromString(json);
					JsonNode node = Rds_slowsql.mapper.readTree(json);
					int page = node.get("PageNumber").asInt();
					int count = node.get("PageRecordCount").asInt();
					if (count == Rds_slowsql.ZERO) {
						break;
					}
					JsonNode itemNode = node.get("Items").get("SQLSlowLog");
					JavaType javaType = getCollectionType(ArrayList.class,
							RdsSlowlogItem.class);
					@SuppressWarnings("unchecked")
					List<RdsSlowlogItem> itemList = (List<RdsSlowlogItem>) Rds_slowsql.mapper
							.readValue(itemNode.toString(), javaType);
					if(itemList.isEmpty()){
						break;
					}
					//特殊条件，正则过滤sql==================
					List<RdsSlowlogItem> itemListnotnull = new ArrayList<RdsSlowlogItem>();
					List<String> whitedblist = FileUtils.readLines(new File(Rds_slowsql.sessionPath ));
//					System.out.println("itemList.size"+"\t"+itemList.size());
					itemList.forEach(item->{
						String sql = item.getSQLText();
						if(StringUtils.isNotBlank(sql)){
							Rds_slowsql.sqlWhiltStatus = checkSqlInWhitelist(sql,whitedblist);
						}
						if(Rds_slowsql.sqlWhiltStatus){
							return;
						}else{
						String sqltxt = Rds_slowsql.sqlWhiltStatus?null:sql;
						if(StringUtils.isNotBlank(sqltxt)&&StringUtils.isNotBlank(dbinstance)){
							item.setSQLText(sqltxt);
							item.setDbinstance(dbinstance);						
						   itemListnotnull.add(item);
						}
					}
					});
					System.out.println("----------"+"itemList.size"+"\t"+itemList.size()+"-----------------itemListnotnull.size()"+"\t"+itemListnotnull.size());
					//===============排除null=============================

					if(itemListnotnull.isEmpty()){
						break;
					}
					rdsSlowSqlService.save_rdsSlowSqlService(itemListnotnull);// 保存数据库
					
					
//					for (RdsSlowlogItem rdsSlowlogItem : itemListnotnull) {
//						System.out.println("itemListnotnull::" + "\t"
//								+ rdsSlowlogItem);
//					}
					// 判断最后一页之后就跳出循环
					if (count < Rds_slowsql.PAGE_SIZE) {
						break;
					}
				} else {
					log.error("response:{},param:{}",
							EntityUtils.toString(response.getEntity()),
							param.toString());
				}
			}
		}
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public Map<String, String> getRequestParam() {
		Map<String, String> param = new HashMap<String, String>();
		String signatureNonce = UUID.randomUUID().toString();
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		param.put("AccessKeyId", Rds_slowsql.ACCESSKEYID);
		param.put("SignatureNonce", signatureNonce);
		param.put("Timestamp", utc.format(Rds_slowsql.UTCFORMATTER));
		return param;
	}

	// 慢查询列表 请求参数配置
	public TreeMap<String, String> parseAliyunParams(int i,String instanceid,String dbname) throws Exception {
		Map<String, String> bizParam = new HashMap<String, String>();
		bizParam.put("Action", Rds_slowsql.APINAME);
		bizParam.put("DBInstanceId", instanceid.trim());
//		bizParam.put("StartTime",
//				LocalDate.now().minusDays(2).format(Rds_slowsql.DATEFORMAT));
		bizParam.put("StartTime",
				LocalDate.now().minusDays(1).format(Rds_slowsql.DATEFORMAT));
		bizParam.put("EndTime",
				LocalDate.now().minusDays(1).format(Rds_slowsql.DATEFORMAT));
		bizParam.put("DBName", dbname.trim());
		// bizParam.put("SortKey", "TotalExecutionCounts");
		bizParam.put("PageSize", Rds_slowsql.PAGE_SIZE + "");
		bizParam.put("PageNumber", i + "");
		TreeMap<String, String> param = new TreeMap<>();
		param.putAll(bizParam);
		param.putAll(getRequestParam());
		param.putAll(Rds_slowsql.commonParam);
		String signature = SignatureUtils.generate("GET", param,
				Rds_slowsql.accessKeySecret);
		param.put("signature", signature);
		return param;
	}

	/**
	 * 将字符串转换成JSON字符串
	 *
	 * @param jsonString
	 *            json字符串
	 * @return 转换成的json对象
	 */
	public static Map<String, Object> getJSONFromString(final String jsonString) {
		return JSON.parseObject(jsonString);
	}

	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return Rds_slowsql.mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}

	/**
	 * 过滤sql 中的
	 * @param sql
	 * @return
	 */
	 public boolean checkSqlInWhitelist(String sql ,List<String> matchWhitelist) {
	        try {
	            Optional<String> optional = matchWhitelist.stream().filter(r -> {
	                return sql.contains(r);
	            }).findFirst();
	            if (optional.isPresent()) {
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            log.error("checkSqlInWhitelist fail", e);
	            return false;
	        }
	    }
	/**
	 * 1.test rds接口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AliyunApi rdsApi = new AliyunApi();
		rdsApi.slowQueryInfo();
//		System.out.println(LocalDate.now().minusDays(2).format(Rds_slowsql.DATEFORMAT));
//		System.out.println(LocalDate.now().minusDays(1).format(Rds_slowsql.DATEFORMAT));
	}
}
