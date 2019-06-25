### 前言
    aliyun出的otter同步平台，配置过于恶心，如果你要配置线上1000个任务，你是不是要傻了，所以搞个自动化批量配置，替代人肉傻瓜式配置，如果一天配置几百个智商都要打折了，所以我就累了点花了一天 写个了工具，个人已经在使用了，配置了300个爽歪歪。
 ### 环境和开发语言
     开发语言: java
     jdk： 1.8
     运行环境 ：随意只要有java环境都可以
本工具如何批量自动化配置方法如下
-----
### 配置文件介绍：

      1. jdbc.properties ：otter数据库配置文件
            格式如下：
            driver=com.mysql.jdbc.Driver
            url=jdbc:mysql://xxxxxx:3306/otter
            username=xxxxx
            password=xxxxxxxx
            #url=jdbc:mysql://xxxxxxx/xxx
            #username=root
            #password=
            #董佳顺
            #定义初始连接数
            initialSize=0
            #定义最大连接数
            maxActive=20
            #定义最大空闲
            maxIdle=20
            #定义最小空闲
            minIdle=1
            #定义最长等待时间
            maxWait=60000

      2. otter.properties ： otter配置文件
            ##第一步配置添加数据库表信息
            ##-------数据源信息配置-------
            ##---------源数据库配置--------
            source.datasource.id=6
            source.datasource.name=source_all_test
            source.datasource.pwd=oibTbJKhVLSL131g33aM
            source.datasource.url=jdbc:mysql://10.7.12.4:3306
            source.datasource.username=pay
            ##---------目标数据库配置--------
            target.database.name=antiFraudReport
            target.datasource.id=8
            target.datasource.name=target_tidb
            target.datasource.pwd=%UsImBHAKv!#KNJ@
            target.datasource.url=jdbc:mysql://10.77.32.187:4000/antiFraudReport
            target.datasource.username=djs
            ##-------数据源信息配置-------
            
            ##第二步配置pipline id 设置
            pipeline_id=14

      3. ottertable.txt 模板文件：导入 要同步的表和库 格式为 k 空格 v 
            db1 table1
            db2 table2
            
### 配置准备：

1.  配置 jdbc.properties 修改otter所在的数据库，添加相关权限密码，账号权限就是otter库的所有权，其实只要dml权限就可以了

2.  配置 otter.properties otter配置文件，可以看到如果我们要配置同步任务
    a.  目标数据源的信息时要配置仔细的
             
3.  ottertable.txt 这个是导入要配置的表 结构 key 空格 value 懂点开发的人 知道我说的是什么，键值对 中间 加空格
    格式：db1 table1
    
以上步骤差不多1分钟或者几秒钟就配置完毕，需要注意点，ottertable.txt 务必写对，
### 工具运行方式
工具命令
	java -cp dbatools-otterauto.jar com.yh.spring.ssm.Controller.OtterAutoAction

### 细节说明
配置文件参数说明（本来不想带图的，好人做到底！）

可想而知我配置一个数据表的同步要这么多操作，虽然提供了界面操作，但是我相信大多数使用者，应该是 运维或者DBA居多。

目前otter存在的问题有，无辜挂起，配置不人性化，异常无法精确，相关canal监控无法监控

先说说使用方法吧
