package org.dreamkite.db.mongo.service.usage.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class PetDetail {
    @Id
    private String id;

    @DBRef
    @Field("petS")
    private List<Pet> pets;
}
