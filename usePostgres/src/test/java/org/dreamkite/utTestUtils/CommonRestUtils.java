package org.dreamkite.utTestUtils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

public class CommonRestUtils {

    private static final String restPath = "rest/rest.json";

    /**
     * 获取特定的resp数据
     *
     * @param distinctKey 接口对应的唯一值
     */
    @SneakyThrows
    public static <T> T getDistinctResp(String distinctKey, Class<T> respClassType) {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource classPathResource = new ClassPathResource(restPath);
        File file = classPathResource.getFile();
        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode respData = jsonNode.get(distinctKey);
        return objectMapper.readValue(objectMapper.writeValueAsString(respData), respClassType);
    }
}
