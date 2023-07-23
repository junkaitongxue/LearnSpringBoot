package com.dreamkite.db.mongo.service.usage.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Person")
@Data
public class Person {

    @Id
    private String id;

    private String name;

    @DBRef
    private List<Pet> pets;

    @Field("DETAIL")
    private PetDetail petDetail;

    private String collectionName;

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", pets=" + pets + "]";
    }

    // standard getters and setters
}
