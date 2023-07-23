package com.dreamkite.utils.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtils {
    private JsonUtils() {

    }

    private static final ObjectMapper OBJECT_MAPPER = SpringUtils.getBean(ObjectMapper.class);

    public static String jsonToStr(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Failed to json to str", e);
        }
        return null;
    }

}
