## 华南理工大学室内定位系统后台

技术栈：
 - web框架：基于spring boot的SpringMVC框架
 - 接口文档：swagger2
 - 数据持久层：MySQL + Mybatis-plus（DAO）
 - 数据缓存层：levelDB（嵌入式数据库）
 
---
### 使用到的一些服务：
 - EMQX MQTT服务

 - gogs + jenkins  CI\CD平台

---

### TODO：
1. ~~解决客户端可能出现mqtt连接不正常关闭，导致后台系统相关线程无法正常回收的问题。~~

2. 定位算法不够准确、效率低的问题。
