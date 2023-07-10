package com.dreamkite.db.utils.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FieldRaw {
    private String mongoKey;

    private Class<?> fieldClazz;

    private Class<?> categoryClazz;
}
