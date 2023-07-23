package com.dreamkite.db.mongo.utils.po;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Document("Son")
public class Son {

    @MongoId
    private String id;

    @Field("name")
    private String name;

}
