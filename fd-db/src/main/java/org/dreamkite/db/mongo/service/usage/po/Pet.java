package org.dreamkite.db.mongo.service.usage.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Pet")
@Data
public class Pet {
    @Id
    private String id;

    private String name;

    @Override
    public String toString() {
        return "Pet [id=" + id + ", name=" + name + "]";
    }

    // standard getters and setters
}

