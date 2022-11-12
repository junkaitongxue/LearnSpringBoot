package com.dreamkite.utils.assembler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
public class Assembler {

    public static <T> List<T> convertList(Object srcObj, Class<T> destClazz) throws InstantiationException, IllegalAccessException {
        if (srcObj instanceof List) {
            return doConvertList((List)srcObj, destClazz);
        }
        return doConvertList(Collections.singletonList(srcObj), destClazz);
    }

    private static <T> List<T> doConvertList(List<?> srcObj, Class<T> destClazz) throws InstantiationException, IllegalAccessException {
        List<T> ret = new ArrayList<>();
        for (Object obj : srcObj) {
            ret.add(convertObject(obj, destClazz));
        }
        return ret;
    }

    public static <T> T convertObject(Object srcObj, Class<T> destClazz) throws InstantiationException, IllegalAccessException {
        T destElement = destClazz.newInstance();
        BeanUtils.copyProperties(destElement, srcObj);
        return destElement;
    }
}
