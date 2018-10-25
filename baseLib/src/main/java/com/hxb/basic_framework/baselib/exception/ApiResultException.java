package com.hxb.basic_framework.baselib.exception;

/**
 * 此异常代表 服务端接口返回失败
 * @author hxb
 */
public class ApiResultException extends Exception {

    public ApiResultException(String msg) {
        super(msg);
    }

}
