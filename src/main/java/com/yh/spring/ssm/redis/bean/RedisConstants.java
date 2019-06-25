package com.yh.spring.ssm.redis.bean;

/**
 * Created by zhangjia
 * Date:2017/7/13
 * Comment:
 */
public class RedisConstants {

    public static final String ROBERT_COLLETING_KEY = "config:task.robertrelease.collecting_timestamp";

    public static final String KEY_CONFIG_HOST_IP = "config:host_ip";

    public static final String KEY_CONFIG_APPLICATION_NAME = "config:pinpoint.application_name";

    public static final String KEY_CONFIG_APPLICATION_MYSQL_USER = "config:application_mysql_user";

    public static final String KEY_CONFIG_SQL_COLLECTION_STARTTIME = "config:slow.sql.collect.start_timestamp";

    public static final String KEY_CONFIG_METRICS_SCORE_SLOWSQL = "config:mectrics.score.slow_sql";

    public static final String KEY_CONFIG_ERROR_THREHOLD = "config:error_threshold";

    public static final String KEY_CONFIG_EVENT_LEVEL = "config:scale-eventlevel:";

    public static final String KEY_CONFIG_METRICS_WEIGHT = "config:metrics-weight:";

    public static final String KEY_CONFIG_DEFAULT_METRICS_WEIGHT = "config:metrics-weight:default";

    //public static final String KEY_SLOW_LOG_COLLECTING_TIMESTAMP = "config:task.slowsql.collecting_timestamp";

    public static final String KEY_CPULOAD_COLLECTING_TIMESTAMP = "config:task.cpuload.collecting_timestamp";

    public static final String KEY_DUBBO_MONITOR_AGGREGATION_TIMESTAMP = "config:task.dubbo-monitor.aggregation_timestamp";

    public static final String KEY_DUBBO_MONITOR_HOURLY_AGGREGATION_TIMESTAMP = "config:task.dubbo-monitor.hourly.aggregation_timestamp";

    public static final String KEY_HEALTH_AGGREGATION_TIMESTAMP = "config:task.health.aggregation_timestamp";

    public static final String KEY_ERROR_LOG_STATASTIC = "statistics:%s.%s";//metrics.service

    public static final String KEY_JVMMEMORY_COLLECTING_TIMESTAMP = "config:task.jvmmemory.collecting_timestamp";

    public static final String KEY_METHOD_PERFORMANCE_SCAN_TIMESTAMP = "config:task.method.performance.scan_timestamp";


    /**
     *错误日志聚合
     */
    public static final String KEY_ERROR_LOG_AGGREGATION_TIMESTAMP = "config:task.errorlog.aggregation_timestamp";

    public static final String KEY_ERRORLOG_CLEANING_TIMESTAMP = "config:task.errorlog.cleaning_timestamp";


    public static final String KEY_SERVICE_METRICS = "config:service_metrics";

    /**
     * 事件收集统计
     */
    public static final String KEY_STATISTICS_EVENT="statistics:event.%s";

    /**
     * 错误日志收集
     */
    public static final String KEY_ERROR_LOG_LIST_KEY = "data:error_log";

    /**
     * 错误日志统计
     */
    public static final String KEY_STATISTICS_ERROR_LOG = "statistics:error-log.%s";



    /**
     * RDS DB实例
     */
    public static final String KEY_RDS_DBINSTANCE="config:rds.db.instance";

    /**
     * RDS数据库和部门映射关系
     */
    public static final String KEY_RDS_DB_DEP_MAPPING="config:rds.db.dep.mapping";

    public static final String KEY_SLOWSQL_HASH_MAP="data:slowsql.hash.mapping";

    public static final String KEY_OWNER_GROUPNAME_MAPPING="config:owner.group.name.mapping";


    /**
     * 慢查询数据库白名单
     */
    public static final String KEY_SLOWSQL_DB_WHITELIST ="config:whitelist:slowsql.db";

    /**
     * 慢查询匹配白名单
     */
    public static final String KEY_SLOWSQL_SQL_MATCH_WHITELIST ="config:whitelist:slowsql.sql.match";
    /**
     * 慢查询字符串比较白名单
     */
    public static final String KEY_SLOWSQL_SQL_EQUAL_WHITELIST ="config:whitelist:slowsql.sql.equal";


    /**
     * ========================== 邮件组收件人 ==========================
     */

    /**
     *慢查询邮件收件人
     */
    public static final String KEY_SLOWSQL_DAILY_MAIL_RECEIVER ="config:mail:slowsql.daily.receiver";

    /**
     * 微服务健康日报收件人
     */
    public static final String KEY_SERVICE_HEALTH_DAILY_MAIL_RECEIVER="config:mail:service.health.daily.receiver";

    /**
     * 方法统计日报收件人
     */
    public static final String KEY_METHOD_PERFORMANCE_DAILY_MAIL_RECEIVER ="config:mail:service.method.performance.daily.receiver";


}
