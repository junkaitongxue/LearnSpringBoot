package org.dreamkite.db.mongo.utils;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface TypeFunction<T,R> extends Serializable, Function<T, R> {

}
