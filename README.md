## 华南理工大学室内定位系统后台

技术栈：
 - web框架：基于spring boot的SpringMVC框架
 - 接口文档：swagger2
 - 数据持久层：MySQL + Mybatis-plus（DAO）
 - 数据缓存层：levelDB（嵌入式数据库）
 
---
### 一些重要的连接：
 - mqtt管理界面(账号密码：admin public) http://39.99.131.85:18083/

 - jenkins http://39.99.131.85:8088/

 - swagger 在线文档 http://39.99.131.85/swagger-ui.html#/

---

### TODO：
1. 解决客户端可能出现mqtt连接不正常关闭，导致后台系统相关线程无法正常回收的问题。

2. 定位算法不够准确、效率低的问题。
