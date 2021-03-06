# 刘磊

## 个人信息<img src="D:\photos\证件照\证件身份照\证件照.jpg" alt="证件照" style="zoom:20%;float:right" />

* 性别：男                                                   年龄：28
* 手机：15600680730                               邮箱：718356979@qq.com
* 专业：软件工程                                        岗位：JAVA后端开发

## 工作及教育经历

* 中国邮政储蓄银行股份有限公司             2020.7~至今
* 中国兵工物资集团有限公司                     2017.7~2020.6
* 北京理工大学                                            2015.7~2017.6                       软件工程专业-研究生
* 北京理工大学                                            2010.7~2014.6                       市场营销专业-本科

## 专业技能

* 熟悉使用$JAVA$，有兴趣学习GO
* 熟悉$Spring$，熟悉$IOC$容器初始化及$AOP$动态代理源码实现
* 熟悉$SpringCloud Alibaba$，掌握$Nacos$、$Apollo$等组件
* 熟悉$Dubbo$，了解其服务治理体系及源码
* 熟悉数据访问框架，掌握$MyBatis$，了解$JPA$
* 掌握基础数据结构，了解$JAVA$集合类等源码
* 掌握网络基础知识，熟悉五层结构
* 掌握并发编程，了解线程池源码及线程复用原理
* 掌握常用的设计模式，并能够应用于实际项目
* 掌握常用算法，如动态规划、分治、贪心等
* 了解$JVM$虚拟机，了解垃圾回收算法及垃圾回收器基本原理
* 了解$Redis$、$RocketMQ$等中间件，熟悉client端的使用及源码

## 项目经历

1. 中国邮政储蓄银行数据迁移系统 - 负责后端开发及代码重构 - 2021.3~至今
   * 负责应用代码优化：
     * 使用Map+Lambda替换了反射，执行相同测试用例，用时缩短为原来的一半
     * 调整了线程池参数设置，避免线程过多导致dubbo无法分配线程报错问题
     * 排除了RocketMQ取消息后不合理打印日志导致的磁盘空间占满，应用异常问题
   * 负责性能测试调优：
     * 通过自定义分组对导出、导入应用进行区分，每组应用连接同一数据源，降低了数据库连接池设置复杂度，增加了单应用对单库的数据库连接数
     * 针对单客户迁移数据库查询次数过多导致的性能瓶颈，提出整合查询条件，一次性查询出单表内所有该客户数据，随后在内存中进行处理，以节省IO开销
   
2. 中国邮政储蓄银行切换过渡系统 - 负责后端开发及代码重构 - 2020.7~2021.2

   * 修改nacos client源码，处理weblogic应用无法注册到nacos问题

   * 负责应用代码优化：
     * 使用工厂+$FactoryBean$替换了反射，提升了执行效率，并提高了可扩展性
     * 使用责任链模式对原应用调用流程进行重新设计，降低了代码的耦合度
     * 规范了输入输出，通过创建DTO实体类避免了方法中随意传递Map导致的隐患

3. 中国兵工物资集团$ERP$系统 - 负责后端开发 - 2017.7~2020.6
   * 负责需求开发及bug修复工作，熟悉了Spring的使用
   * 负责服务器维护和应用部署工作，熟悉了linux系统的基本使用

## 个人账号

* $GitHub$账号：https://github.com/nanaliulei

## 其他信息

* 喜欢钻研技术，工作之外喜欢看技术类书籍巩固基础、扩展思路
* 享受解决问题的过程，LeetCode已解决近700题，周赛最佳排名169，为leetcode-master项目贡献了背包问题部分Java代码
* 自律，坚持每天跑步、俯卧撑等锻炼身体，日均40分钟
* 爱好广泛，喜欢打台球、羽毛球等