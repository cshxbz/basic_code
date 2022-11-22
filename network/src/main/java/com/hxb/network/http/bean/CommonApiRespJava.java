package com.hxb.network.http.bean;

import androidx.annotation.Nullable;

public class CommonApiRespJava<T> implements IApiResp<T> {

    private T data;
    private Integer code;
    private String message;

    @Nullable
    @Override
    public T getData() {
        return data;
    }

    @Nullable
    @Override
    public Integer getCode() {
        return code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isSuccess() {
        return code != null && code == 0;
    }
}
