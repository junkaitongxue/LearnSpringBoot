package com.dreamkite.db.mongo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Slf4j
public final class MongoUtils {

    public static final String PREFIX_GET = "get";
    public static final String PREFIX_IS = "is";
    public static final String ID = "_id";

    private MongoUtils() {

    }

    public static <T, R> SerializedLambda getSerializedLambda(TypeFunction<T, R> lambda) {
        try {
            Method method = lambda.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(lambda);
            checkIsGetter(serializedLambda);
            return serializedLambda;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Failed to getSerializedLambda", e);
            throw new MongoFieldException("Failed to getSerializedLambda");
        }
    }

    private static void checkIsGetter(SerializedLambda serializedLambda) {
        if (!isGetter(serializedLambda.getImplMethodName())) {
            throw new MongoFieldException("Only support getter");
        }
    }

    private static boolean isGetter(String methodName) {
        return methodName.startsWith(PREFIX_GET) || methodName.startsWith(PREFIX_IS);
    }

    public static String getFieldName(SerializedLambda serializedLambda) {
        Field field = getField(serializedLambda);
        return getMongoKey(field);
    }

    public static FieldRaw getFieldRaw(SerializedLambda serializedLambda) {
        String className = serializedLambda.getImplClass().replace("/", ".");
        try {
            Class<?> clazz = Class.forName(className);
            Field field = getField(serializedLambda);
            Class<?> actualFieldType = getFieldType(field);
            return new FieldRaw(getMongoKey(field), actualFieldType, clazz);
        } catch (ClassNotFoundException e) {
            log.error("Failed to getFieldRaw");
            throw new MongoFieldException("Failed to getFieldRaw");
        }
    }

    private static Class<?> getFieldType(Field field) {
        if (field.getType().isAssignableFrom(List.class)) {
            ParameterizedType type = (ParameterizedType) field.getGenericType();
            return (Class<?>) type.getActualTypeArguments()[0];
        }
        return field.getType();
    }

    private static String getMongoKey(Field field) {
        if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(MongoId.class)) {
            return ID;
        } else if (field.isAnnotationPresent(org.springframework.data.mongodb.core.mapping.Field.class)) {
            String value = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class).value();
            if (StringUtils.isNotEmpty(value)) {
                return value;
            }
        }
        return field.getName();
    }


    private static Field getField(SerializedLambda serializedLambda) {
        String implMethodName = serializedLambda.getImplMethodName();
        String property = methodToProperty(implMethodName);
        String className = serializedLambda.getImplClass().replace("/", ".");
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            return clazz.getDeclaredField(property);
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
            log.error("Failed to getField");
            throw new MongoFieldException("Failed to getField");
        }
    }

    private static String methodToProperty(String method) {
        String property;
        if (method.startsWith(PREFIX_GET)) {
            property = method.substring(PREFIX_GET.length());
        } else {
            property = method.substring(PREFIX_IS.length());
        }
        return uncapitalize(property);
    }

    /**
     * 兼容lombok和jackson/spring关于第二个字母为大写处理方式不同的场景，aB --> getAB(lombok)/getaB(other), 都做首字母小写
     *
     * @param value value
     * @return 首字母小写后的值
     */
    private static String uncapitalize(String value) {
        if (value == null || value.length() == 0 || Character.isLowerCase(value.charAt(0))) {
            return value;
        }
        char[] chars = value.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    public static <T, R> String getIndexName(TypeFunction<T, R> lambda) {
        return getSpecificIndexName(lambda, Indexed.class, "name");
    }

    private static <T, R> String getSpecificIndexName(TypeFunction<T, R> lambda, Class<? extends Annotation> annotationClass, String attributeName) {
        SerializedLambda serializedLambda = getSerializedLambda(lambda);
        checkIsGetter(serializedLambda);
        Field field = getField(serializedLambda);
        if (field.isAnnotationPresent(annotationClass)) {
            String name = (String) AnnotationUtils.getAnnotationAttributes(field.getAnnotation(annotationClass)).get(attributeName);
            if (StringUtils.isNotEmpty(name)) {
                return name;
            }
        }
        return field.getName();
    }

}
