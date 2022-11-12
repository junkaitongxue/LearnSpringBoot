package com.dreamkite.core.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith({MockitoExtension.class})
@ActiveProfiles({"ut"})
public class CommonSpringTest {

    /**
     * 设置类的static属性
     *
     * @param clazz 类名
     * @param fieldName 字段名
     * @param newValue 值
     */
    @SneakyThrows
    public static void setFinalStatic(Class<?> clazz, String fieldName, Object newValue)  {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    /**
     * 设置类的static属性: 没有带final
     *
     * @param clazz 类名
     * @param fieldName 字段名
     * @param newValue 值
     */
    @SneakyThrows
    public static void setStatic(Class<?> clazz, String fieldName, Object newValue)  {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, newValue);
    }

}
