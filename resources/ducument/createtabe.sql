use dms;
CREATE TABLE `rds_slowsql_detailed` (
   `id`  int(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
   `sqlid` bigint(20) NOT NULL,
   `dbname` varchar(20) NOT NULL COMMENT '数据库名字',
   `sqltext` mediumtext NOT NULL COMMENT '慢sqlcontext',
   `mysqltotalexecutioncounts` int(11) NOT NULL COMMENT '执行总次数',
   `mysqltotalexecutiontimes` bigint(20) NOT NULL COMMENT '执行总时长',
   `maxexecutiontime` bigint(20) NOT NULL COMMENT '执行最大时长',
   `totallocktimes` bigint(20) NOT NULL COMMENT '锁定总时长',
   `maxlocktime` bigint(20) NOT NULL COMMENT '锁定最大时长',
   `parsetotalrowcounts` bigint(20) NOT NULL COMMENT '解析总行数',
   `parsemaxrowcount` bigint(20) NOT NULL COMMENT '解析最大行数',
   `returntotalrowcounts` bigint(20) NOT NULL COMMENT '返回总行数',
   `returnmaxrowcount` bigint(20) NOT NULL COMMENT '返回最大行数',
   `createtime` varchar(20) NOT NULL COMMENT 'sql生成时间',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='RDS检测sql明细表';
 