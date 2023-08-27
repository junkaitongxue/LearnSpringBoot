package org.dreamkite.db.mongo.utils;

import org.dreamkite.db.mongo.utils.po.Father;
import org.dreamkite.db.mongo.utils.po.Son;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MongoFieldBuilderTest {

    @Test
    void build() {
        System.out.println(MongoFieldBuilder.builder().append(Father::getSon).append(Son::getName).build());
    }

    @Test
    void build1() {
        System.out.println(MongoFieldBuilder.builder().append(Father::getSon).appendDollarSymbol().append(Son::getName).build());
    }

    @Test
    void build2() {
        System.out.println(MongoFieldBuilder.buildOne(Father::getId));
    }

    @Test
    void build3() {
        Assertions.assertThrows(MongoFieldException.class, () -> {
            MongoFieldBuilder.builder().append(Father::getName).append(Son::getId).build();
        });
    }

    @Test
    void build4() {
        Assertions.assertDoesNotThrow(() -> {
            System.out.println(MongoFieldBuilder.builder(MongoFieldBuilder.VerifyMode.OFF).append(Father::getName).append(Son::getId).build());
        });
    }

    @Test
    void getIndexName() {
        System.out.println(MongoUtils.getIndexName(Father::getName));
    }
}