package org.dreamkite.db.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 用于过滤掉Mongo目录的加载
 */
@Slf4j
public class MongoFilter implements TypeFilter {
    private static final String PACKAGE = "com.dreamkite.db.mongo";

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {
        return metadataReader.getClassMetadata().getClassName().startsWith(PACKAGE);
    }
}
