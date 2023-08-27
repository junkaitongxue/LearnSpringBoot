package org.dreamkite.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespEntity<T> {

    T data;

    boolean isSuccess;

    String errorMessage;

    public static RespEntity<?> error(String errorMessage) {
        RespEntity<?> ret = new RespEntity<>();
        ret.setErrorMessage(errorMessage);
        return ret;
    }

    public static <T> RespEntity<T> success(T data) {
        RespEntity<T> ret = new RespEntity<>();
        ret.setSuccess(true);
        ret.setData(data);
        return ret;
    }
}
