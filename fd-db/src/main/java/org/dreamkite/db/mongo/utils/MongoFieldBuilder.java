package org.dreamkite.db.mongo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public final class MongoFieldBuilder {

    private static final String DOLLAR_SYMBOL = "$";

    private final List<Serializable> fieldLambdas = new LinkedList<>();

    private VerifyMode verifyType = VerifyMode.ON;

    private MongoFieldBuilder() {

    }

    private MongoFieldBuilder(VerifyMode verifyType) {
        this.verifyType = verifyType;
    }

    public static MongoFieldBuilder builder() {
        return new MongoFieldBuilder();
    }

    public static MongoFieldBuilder builder(VerifyMode verifyType) {
        return new MongoFieldBuilder(verifyType);
    }

    public static <T, R> String buildOne(TypeFunction<T, R> typeFunction) {
        return MongoUtils.getFieldName(MongoUtils.getSerializedLambda(typeFunction));
    }

    <T, R> MongoFieldBuilder append(TypeFunction<T, R> typeFunction) {
        SerializedLambda serializedLambda = MongoUtils.getSerializedLambda(typeFunction);

        fieldLambdas.add(MongoUtils.getSerializedLambda(typeFunction));
        return this;
    }

    MongoFieldBuilder append(String constName) {
        fieldLambdas.add(constName);
        return this;
    }

    MongoFieldBuilder appendDollarSymbol() {
        fieldLambdas.add(DOLLAR_SYMBOL);
        return this;
    }

    public String build() {
        List<String> filedList = new ArrayList<>();
        Class<?> lastClass = null;
        for (Serializable fieldLambda : fieldLambdas) {
            if (fieldLambda instanceof String) {
                filedList.add((String) fieldLambda);
                continue;
            }
            if (fieldLambda instanceof SerializedLambda) {
                if (verifyType.equals(VerifyMode.OFF)) {
                    filedList.add(MongoUtils.getFieldName((SerializedLambda) fieldLambda));
                    continue;
                }
                FieldRaw fieldRaw = MongoUtils.getFieldRaw((SerializedLambda) fieldLambda);
                if (lastClass == null || fieldRaw.getCategoryClazz().equals(lastClass)) {
                    filedList.add(fieldRaw.getMongoKey());
                    lastClass = fieldRaw.getFieldClazz();
                    continue;
                }
                throw new MongoFieldException("not match");
            }
        }
        return String.join(".", filedList);
    }

    public enum VerifyMode {
        OFF, ON;
    }
}
