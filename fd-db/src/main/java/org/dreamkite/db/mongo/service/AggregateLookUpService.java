package org.dreamkite.db.mongo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Service
public class AggregateLookUpService {

    /**
     * 设置集合名称
     */
    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     *  外表联立，判断外表的没有对应的数据
     *
     * @return 聚合结果
     */
    public Object lookupForeignEmpty() {
        LookupOperation lookupOperation = LookupOperation.newLookup().from("users1")
                .localField("_id")   //主表关联字段
                .foreignField("_id")   //从表关联字段
                .as("foreign_db");   //查询结果名
        MatchOperation matchOperation = new MatchOperation(Criteria.where("name").is("lisi").and("foreign_db").size(0));
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, matchOperation);
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        for (Map result : results.getMappedResults()) {
            log.info("{}", result);
        }
        return results.getMappedResults();
    }

    /**
     *  外表联立，增加判断外表的值
     *
     * @return 聚合结果
     */
    public Object lookupForeignWithForeignJudge() {
        LookupOperation lookupOperation = LookupOperation.newLookup().from("users1")
                .localField("_id")   //主表关联字段
                .foreignField("_id")   //从表关联字段
                .as("foreign_db");   //查询结果名
        // 源码中建议使用静态工厂方法Aggregation.lookup(String, String, String, String)而不是直接创建此类的实例
//        LookupOperation lookupOperation = Aggregation.lookup("users1", "_id", "_id", "foreign_db");
        MatchOperation matchOperation = new MatchOperation(Criteria.where("name").is("zhangsansan").and("foreign_db.name1").is("zhangsansan1"));
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, matchOperation);
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        for (Map result : results.getMappedResults()) {
            log.info("{}", result);
        }
        return results.getMappedResults();
    }

    /**
     *  外表联立，增加判断外表的值， 并且一对一的关系使用unwind减少一层
     *  $unwind：将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
     * 啥效果啥作用呢？
     * 看两张截图
     * 没使用unwind前会多嵌套了一层 使用后好比是把里面一层拍扁了 少了一层利于取值
     * 其实在使用对象接收查询结果后就不必使用unwind了 对象取值还是很方便的
     * 而且使用对象接收后 使用unwind会报类型转换异常的错误
     *
     * @return 聚合结果
     */
    public Object lookupForeignWithForeignJudgeOneToOne() {
        LookupOperation lookupOperation = LookupOperation.newLookup().from("users1")
                .localField("_id")   //主表关联字段
                .foreignField("_id")   //从表关联字段
                .as("foreign_db");   //查询结果名
        // 源码中建议使用静态工厂方法Aggregation.lookup(String, String, String, String)而不是直接创建此类的实例
//        LookupOperation lookupOperation = Aggregation.lookup("users1", "_id", "_id", "foreign_db");
        MatchOperation matchOperation = new MatchOperation(Criteria.where("name").is("zhangsansan").and("foreign_db.name1").is("zhangsansan1"));
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, matchOperation, Aggregation.unwind("foreign_db"));
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        for (Map result : results.getMappedResults()) {
            log.info("{}", result);
        }
        return results.getMappedResults();
    }


    /**
     *  外表联立，增加判断外表的值， 并且一对一的关系使用unwind减少一层, 外表数据只获取一部分
     * @return 聚合结果
     */
    public Object lookupForeignWithForeignJudgeOneToOne1() {
        LookupOperation lookupOperation = LookupOperation.newLookup().from("users1")
                .localField("_id")   //主表关联字段
                .foreignField("_id")   //从表关联字段
                .as("foreign_db");   //查询结果名
        // 源码中建议使用静态工厂方法Aggregation.lookup(String, String, String, String)而不是直接创建此类的实例
//        LookupOperation lookupOperation = Aggregation.lookup("users1", "_id", "_id", "foreign_db");
        MatchOperation matchOperation = new MatchOperation(Criteria.where("name").is("zhangsansan").and("foreign_db.name1").is("zhangsansan1"));
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, matchOperation, Aggregation.unwind("foreign_db"), Aggregation.project("foreign_db.name1", "foreign_db.age").andExclude("_id"));
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        for (Map result : results.getMappedResults()) {
            log.info("{}", result);
        }
        return results.getMappedResults();
    }


}