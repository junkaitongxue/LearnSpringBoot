package org.dreamkite.db.mongo.utils;


public class MongoFieldException extends RuntimeException {
    private final String errorMessage;

    MongoFieldException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
