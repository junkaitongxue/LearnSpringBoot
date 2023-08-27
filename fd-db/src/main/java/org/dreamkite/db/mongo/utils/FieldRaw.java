package org.dreamkite.db.mongo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FieldRaw {
    private String mongoKey;

    private Class<?> fieldClazz;

    private Class<?> categoryClazz;
}
