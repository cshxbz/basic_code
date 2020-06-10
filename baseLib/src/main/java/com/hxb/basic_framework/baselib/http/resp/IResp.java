package com.hxb.basic_framework.baselib.http.resp;

/**
 * 接口响应数据实体类要实现这个接口
 * @author hxb
 */
public interface IResp {

    boolean isSuccess();
    String getMessage();

}
