# MongoDB创建密码的用户和db
首先看看mongodb内置角色：
```
    1. 数据库用户角色：read、readWrite;
    2. 数据库管理角色：dbAdmin、dbOwner、userAdmin；
    3. 集群管理角色：clusterAdmin、clusterManager、clusterMonitor、hostManager；
    4. 备份恢复角色：backup、restore；
    5. 所有数据库角色：readAnyDatabase、readWriteAnyDatabase、userAdminAnyDatabase、dbAdminAnyDatabase
    6. 超级用户角色：root  
    7. 内部角色：__system
```
show dbs
use test
db.test.insert({"key":"value"})
use admin
db.createUser({user:"testAdmin",pwd:"123456",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})
//重启服务器,使用mongoSeverCtlWithAuth.bat
use admin
db.auth("testAdmin", "123456")
db.createUser({user:'testUser',pwd:'123456',roles:[{role:'readWrite',db:'test'}]})

然后就使用testUser进行对test的读写操作了

参考：
https://blog.csdn.net/qq_41992943/article/details/115546623



# MongoDB之视图脚本操作以及Java程序代码操作：
说到数据库经典场景就是视图了，视图是一个虚拟表，具有实时更新性，和重新创建一个表，
并通过程序控制更新的成本比起来，视图的成本是真的底很多，在mongo中是否有视图呢，答案是肯定的。
https://blog.csdn.net/xtho62/article/details/125216935


# 聚合管道
java实现mongo查询使用总结
https://blog.csdn.net/Java_glory_/article/details/129300950

# java实操
https://blog.csdn.net/baidu_37366055/article/details/129709327

聚合管道操作符:

$project： 可以从文档中选择想要的字段，和不想要的字段（指定的字段可以是来自输入文档或新计算字段的现有字段 ，也可以通过管道表达式进行一些复杂的操作，例如数学操作，日期操作，字符串操作，逻辑操作。
$match： 用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。
$limit： 用来限制MongoDB聚合管道返回的文档数。
$skip： 在聚合管道中跳过指定数量的文档，并返回余下的文档。
$unwind： 将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
$group： 将集合中的文档分组，可用于统计结果。
$sort： 将输入文档排序后输出。

# 事务
复制集？
