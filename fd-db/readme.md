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
事物只支持复制集， 单机部署的mongo是没法使用事务的
默认的@Transactional是对数据库的写操作进行锁定的，如果一个事务对表1写入10M数据，再对表2写入10M数据，就超过16M了，就会导致报错~~


# springboot整合mongodb MongoTemplate和MongoRepository的用法
除了直接使用MongoTemplate，我们通常会写一个接口继承MongoRepository接口，并且不需要实现该接口就可操作数据库。
Repository 提供了最基本的数据访问功能，其几个子接口则扩展了一些功能。它们的继承关系如下：
```
Repository： 仅仅是一个标识，表明任何继承它的均为仓库接口类
CrudRepository： 继承 Repository，实现了一组 CRUD 相关的方法
PagingAndSortingRepository： 继承 CrudRepository，实现了一组分页排序相关的方法
MongoRepository： 继承 PagingAndSortingRepository，实现一组 mongodb规范相关的方法
```
MongoRepository_SPRING操作MONGODB之SPRING黑科技,采用继承MONGOREPOSITORY接口实现

MongoTemplate


https://blog.csdn.net/u010797364/article/details/120484002


# Spring boot MongoDB多数据源，MongoRepository实现
https://www.cnblogs.com/kelelipeng/p/13329765.html


# 删除MongoDB列表某项/数组某项
pull是删除内嵌数组项，upset是删除内嵌键值
关键命令一: $pull、update.pull
The $pull operator removes from an existing array all instances of a value or values that match a specified condition.
它用于删除数组所匹配的项，如果数组[1,1,2,1] 执行pull 1 后，只剩下[2]

关键命令二: $upset、update.upset
($unset运算符删除特定字段。请考虑以下语法：)
The $unset operator deletes a particular field. Consider the following syntax: copycopied
{ $unset: { <“field1”>: “”, … } }
原文链接：https://blog.csdn.net/zhan107876/article/details/106257981


# DBREF和手动引用（MANUAL REFERENCES
https://www.freesion.com/article/25851190359/

# 关联 $lookup
https://juejin.cn/post/6970242759714144264
https://blog.csdn.net/H900302/article/details/119237044

# mongo批量插入问题
设置了唯一键 唯一键异常 批量插入中间出现异常 后面数据会发生什么？？？
异常发生, 后面数据就不再插入,唯一键异常下面可以捕获MongoBulkWriteException

如果我想抛出异常 不影响后面数据执行 可不可以 mongo这么吊,当然可以 BulkMode.UNORDERED:表示并行处理 有异常就不会影响其他
```
    BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, "scrm_clues_pool");
    ops.insert(poolList);
    // 执行操作
    // ops.execute();
```

# 一些报错
## Invalid path reference pets.name! Associations can only be pointed to directly or via their id property!
使用了@DBRef时，不能根据其里面的属性进行查询，
https://blog.csdn.net/fenyuduanchangren/article/details/77688842

# 关键的手册型链接
- [MongoDB中文手册|官方文档中文版](https://docs.mongoing.com/)
- [Spring Data MongoDB 中文文档](https://springdoc.cn/spring-data-mongodb/#preface)
- 




