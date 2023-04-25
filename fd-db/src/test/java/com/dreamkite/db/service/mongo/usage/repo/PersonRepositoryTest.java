package com.dreamkite.db.service.mongo.usage.repo;

import com.dreamkite.db.service.mongo.usage.po.Person;
import com.dreamkite.db.service.mongo.usage.po.Pet;
import com.dreamkite.db.service.mongo.usage.po.PetDetail;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PersonRepositoryTest {

    @Resource
    PersonRepository personRepository;

    @Resource
    PetRepository petRepository;

    @Resource
    MongoTemplate mongoTemplate;

    @Test
    @DisplayName("测试两种方式插入，两条查询出来的数据是否格式不一样？一模一样")
    void findAll() {
        List<Person> allPerson = personRepository.findAll();
        System.out.println(allPerson);
    }

    @Test
    void findOne() {
        Optional<Person> opPerson = personRepository.findById("64437bdb46e7bf2bcbda2ee3");
        System.out.println(opPerson.get());
    }

    @Test
    void existByid00() {
        System.out.println(personRepository.existsById("64437bdb46e7bf2bcbda2ee3"));
    }

    @Test
    @DisplayName("测试数组为嵌套的是否存在判断")
    void existByid01() {
        CriteriaDefinition criteria = Criteria.where("_id").is("64437bdb46e7bf2bcbda2ee3").and("pets.name").is("cat"); // 只要查得出来就行
        Query query = new Query(criteria);
        System.out.println(mongoTemplate.exists(query, "Person"));
    }

    @Test
    @DisplayName("测试数组为引用的是否存在判断")
    void existByid02() {
        CriteriaDefinition criteria = Criteria.where("_id").is("6444159a949cdb38052f842a").and("pets._id").is("6444159a949cdb38052f8429");
        Query query = new Query(criteria);
        System.out.println(mongoTemplate.exists(query, "Person"));//false, 当@DBRef注解pets了的时候也不行！！
        List<Person> people = mongoTemplate.find(query, Person.class);
        System.out.println(people.size());//当@DBRef注解pets了的时候能够查出来
    }

    @Test
    @DisplayName("嵌套的方式插入， 注释掉@DBRef")
    void insert00() {
        Pet pet = new Pet();
//        pet.setId("01");
        pet.setName("cat");
//        petRepository.insert(pet);

        Person person = new Person();
        person.setName("name");
        person.setId("123456");
        person.setPets(Arrays.asList(pet));
        System.out.println(personRepository.insert(person));
    }

    @Test
    @DisplayName("引用的方式插入： 如果插入后把表pet的数据删了之后要同步删除，不然person的pet列表中引用的那个查出来会是个null")
    void insert01() {
        Pet pet = new Pet();
//        pet.setId("01");
        pet.setName("pig");
        petRepository.save(pet);

        Person person = new Person();
        person.setName("dk");
//        person.setId("123456");
        person.setPets(Arrays.asList(pet));
        System.out.println(personRepository.insert(person));
    }

    @Test
    void delete() {
        personRepository.deleteAll();
    }

    @Test
    void deleteAllById() {
        personRepository.deleteAllById(Arrays.asList("6443eece4f5cd734936118f6", "6443eec685b0236ef32f88c9"));
    }

    @Test
    @DisplayName("修改字段，增加字段与修改字段，在语法上是等效的，例如增加’abnormal’字段，初始化为“0”：")
    // todo ("如何更新数组中的元素的键值， 流程还没跑通)
    void testUpdate() {
        CriteriaDefinition criteria = Criteria.where("_id").is("64437bdb46e7bf2bcbda2ee3").and("pets.id").is("64437bdb46e7bf2bcbda2ee2");
        Query query = new Query(criteria);
        List<Person> people = mongoTemplate.find(query, Person.class);
        System.out.println(people.size());

        Update update = new Update().set("pets.$.name", "pig");
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "Person");
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }

    @Test
    @DisplayName("测试数组同时存在两种类型类型的场景是否, 先有docment， 想插入DbRef的记录， 插入的结果为Docment")
    void testUpdateAddToSet() {
        Pet pet = new Pet();
        pet.setId("02");
        pet.setName("cat");
//        Pet pet1 = petRepository.save(pet);

        CriteriaDefinition criteria = Criteria.where("_id").is("64437bdb46e7bf2bcbda2ee3");
        Query query = new Query(criteria);

        Update update = new Update().addToSet("pets", pet);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "Person");
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }


    @Test
    @DisplayName("测试数组同时存在两种类型类型的场景是否, 先有docment， 想插入DbRef的记录， 插入的结果为Docment")
    void testCoexist() {
        Pet pet = new Pet();
//        pet.setId("02");
        pet.setName("dog");
        Pet pet1 = petRepository.save(pet);

        CriteriaDefinition criteria = Criteria.where("_id").is("6443891cfbb39d5eef4e1a68");
        Query query = new Query(criteria);

        Update update = new Update().addToSet("pets", pet);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "Person");
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }

    @Test
    @DisplayName("测试数组同时存在两种类型类型的场景是否, 先有DbRef， 想插入docment的记录， 插入的结果同为docment， 说明了更新的addToSet都是插入docment")
    void testCoexist1() {
        Pet pet = new Pet();
        pet.setId("64437bdb46e7bf2bcbda2ee2");
        pet.setName("dog");
        Pet pet1 = petRepository.save(pet);

        CriteriaDefinition criteria = Criteria.where("_id").is("64437bdb46e7bf2bcbda2ee3");
        Query query = new Query(criteria);

        Update update = new Update().addToSet("pets", pet);

        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "Person");
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }

    @Test
    @DisplayName("删除集合中一个数组的某个值： pull")
    void testPull() {
        Pet pet = new Pet();
        pet.setId("64437bdb46e7bf2bcbda2ee2");// 主要就是要根据id作为唯一性片段
//        pet.setName("dog");
//        Pet pet1 = petRepository.save(pet);

        CriteriaDefinition criteria = Criteria.where("_id").is("64437bdb46e7bf2bcbda2ee3");
        Query query = new Query(criteria);

        Update update = new Update().pull("pets", pet);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "Person");
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }

    @Test
    @DisplayName("删除集合中一个数组的某个元素的某一个键值： upset")
    void testUpset() {
        CriteriaDefinition criteria = Criteria.where("_id").is("6443efc45b8aab1e323d9ea7").and("pets.name").is("cat");// 主要就是要根据要删除的字段作为关键值片段,必须有这个片段("pets.name").is("cat")不然会报错： ‘The positional operator did not find the match needed from the query.’
        Query query = new Query(criteria);
        List<Person> persons = mongoTemplate.find(query, Person.class);
        log.info("persion size: {}", persons.size());

        Update update = new Update().unset("pets.$.name"); //"$" 字符 代表 下标位置索引
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "Person");
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }

    @Test
    @DisplayName("数组中追加引用")
    void testCoexist2() {
        Pet pet = new Pet();
        pet.setName("dog");
        Pet pet1 = petRepository.save(pet);// 注意这里有个坑， 返回值的pet1跟入参其实是同个对象，如果对pet1进行修改入参也会改的

        CriteriaDefinition criteria = Criteria.where("_id").is("64437bdb46e7bf2bcbda2ee3");
        Query query = new Query(criteria);

        Update update = new Update().addToSet("pets", pet);

        // 如果要追加数据引入千万不要怎么写 mongoTemplate.updateMulti(query, update, "Person")，因为感知不到引用关系，则需要传Person.class
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Person.class);//
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }


    @Test
    @DisplayName("【更新】删除集合中一个数组的某个元素： pull， 删除Db引用")
    void testPull2() {
        Pet pet = new Pet();
        pet.setId("64437bdb46e7bf2bcbda2ee2");// 主要就是要根据id作为唯一性片段

        CriteriaDefinition criteria = Criteria.where("_id").is("64437bdb46e7bf2bcbda2ee3");
        Query query = new Query(criteria);

        Update update = new Update().pull("pets", pet);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "Person");
        petRepository.deleteById("64437bdb46e7bf2bcbda2ee2"); // 先删除引入的地方，再删除源头的数据，顺序不能乱
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }

    @Test
    @DisplayName("引用的方式插入： 如果插入后把表pet的数据删了之后要同步删除，不然person的pet列表中引用的那个查出来会是个null， 嵌套层级>1")
    void insert02() {
        Pet pet = new Pet();
        pet.setName("ant");
        petRepository.save(pet);

        Person person = new Person();
        person.setName("jk");

        PetDetail petDetail = new PetDetail();
        petDetail.setPets(Arrays.asList(pet));
        person.setPetDetail(petDetail);

        System.out.println(personRepository.insert(person));
    }

    @Test
    @DisplayName("数组中追加引用， 嵌套层级>1, ")
    void testAddToSet() {
        Pet pet = new Pet();
        pet.setName("ant");
        petRepository.save(pet);// 注意这里有个坑， 返回值的pet1跟入参其实是同个对象，如果对pet1进行修改入参也会改的
        System.out.println(pet.getId());

        CriteriaDefinition criteria = Criteria.where("_id").is("6447f397153efc7858e105bb");
        Query query = new Query(criteria);

        Update update = new Update().addToSet("DETAIL.pets", pet);

        // 如果要追加数据引入千万不要怎么写 mongoTemplate.updateMulti(query, update, "Person")，因为感知不到引用关系，则需要传Person.class
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Person.class);//
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }

    @Test
    @DisplayName("数组中追加引用， 嵌套层级>1, 字段名和Field名一样: detail")
    // 对@DBRef的字段进行多层嵌套的话，函数addToSet的该key值承载的是属性名的索引!!!
    void testAddToSet1() {
        Pet pet = new Pet();
        pet.setName("ant");
        petRepository.save(pet);// 注意这里有个坑， 返回值的pet1跟入参其实是同个对象，如果对pet1进行修改入参也会改的

        CriteriaDefinition criteria = Criteria.where("_id").is("6447f397153efc7858e105bb");
        Query query = new Query(criteria);

        Update update = new Update().addToSet("petDetail.pets", pet);

        // 如果要追加数据引入千万不要怎么写 mongoTemplate.updateMulti(query, update, "Person")，因为感知不到引用关系，则需要传Person.class
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Person.class);//
        log.info("{}-{}-{}", updateResult.getMatchedCount(), updateResult.getModifiedCount(), updateResult.getUpsertedId());
    }
}