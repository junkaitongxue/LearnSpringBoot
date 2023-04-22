package com.dreamkite.db.service.mongo.usage.repo;

import com.dreamkite.db.service.mongo.usage.po.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 使用过Spring Jpa的都清楚，Repository实际就是用于操作数据库的类。在非关系型数据库MongoDB的整合中，也是一样的。Spring会帮我们实现好对应接口的方法，
 * 开发人员连SQL都不用写，非常省心。代码如下：
 *
 * 注意MongoRepository后面接的泛型<User, String>第一个为实体类，第二个为主键ID。
 */
public interface PetRepository extends MongoRepository<Pet, String> {

}

