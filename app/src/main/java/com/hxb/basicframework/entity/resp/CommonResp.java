package com.hxb.basicframework.entity.resp;

import com.hxb.basic_framework.baselib.http.resp.IResp;

/**
 * 服务端响应数据类
 */
public class CommonResp<T> implements IResp {

    private int status;
    private String message;
    private  T data;

    public boolean isSuccess() {
        return status == 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResp{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
