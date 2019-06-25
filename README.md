# otter_auto_tools
aliyun出的otter同步平台，配置过于恶心，如果你要配置线上1000个任务，你是不是要傻了，所以搞个自动化批量配置
### 前言
    aliyun开源的Otter平台

    官方的配置方式为：
Otter QuickStart 如何配置一个任务
-----
操作步骤：
1.  添加数据库
    a.  源库 jdbc:mysql://10.20.144.25:3306
    b.  目标库 jdbc:mysql://10.20.144.29:3306
2.  添加canal
    a.  提供数据库ip信息 
3.  添加同步表信息
    a.  源数据表 test.example
    b.  目标数据表 test.example
4.  添加channel
5.  添加pipeline
    a.  选择node节点
    b.  选择canal
6.  添加同步映射规则
    a.  定义源表和目标表的同步关系
7.  启动
8.  测试数据
可想而知我配置一个数据表的同步要这么多操作，虽然提供了界面操作，但是我相信大多数使用者，应该是 运维或者DBA居多。

目前otter存在的问题有，无辜挂起，配置不人性化，异常无法精确，相关canal监控无法监控

先说说使用方法吧
