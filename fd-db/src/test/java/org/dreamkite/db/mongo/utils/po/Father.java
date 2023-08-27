package org.dreamkite.db.mongo.utils.po;

import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Document("FATHER")
public class Father {

    @MongoId
    private String id;

    @Field("name")
    @Indexed(name = "Father.name")
    private String name;

    private List<Son> son;
}
