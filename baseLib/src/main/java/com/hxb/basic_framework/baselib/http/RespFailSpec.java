package com.hxb.basic_framework.baselib.http;

/**
 * 网络请求接口响应失败的详细信息
 * @author HuangXiaoBo
 * created 2019/4/27 21:30
 */
public class RespFailSpec {
    private String msg;
    private RespFailType type;

    public RespFailSpec(RespFailType type,String msg) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RespFailType getType() {
        return type;
    }

    public void setType(RespFailType type) {
        this.type = type;
    }
}
