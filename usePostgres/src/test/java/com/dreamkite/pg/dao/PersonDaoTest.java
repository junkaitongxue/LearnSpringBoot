package com.dreamkite.pg.dao;

import com.dreamkite.pg.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"test"})
class PersonDaoTest {

    @Resource
    PersonDao personDao;

    @DisplayName("测试使用useGeneratedKeys进行更新或跳过插入操作会发生数据错位")
    @Test
    void batchInsertPerson() {
        Person zhangSan = new Person("zhang", "san", "chaoyan", "Beijing");
        Person liSi = new Person("li", "si", "chaoyan", "Beijing");
        Person wangWu = new Person("wang", "wu", "chaoyan", "Beijing");
        personDao.insertPersonWithoutId(wangWu.getLastName(), wangWu.getFirstName(), wangWu.getAddress(), wangWu.getCity());
        List<Person> people = Arrays.asList(zhangSan, liSi, wangWu);
        personDao.batchInsertPerson(people);
        System.out.println(people);
    }
}