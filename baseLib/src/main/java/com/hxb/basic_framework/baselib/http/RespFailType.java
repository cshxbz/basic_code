package com.hxb.basic_framework.baselib.http;

/**
 * 定义网络请求接口响应失败的类型
 *
 * @author HuangXiaoBo
 * created 2019/4/27 20:55
 */
public enum RespFailType {

    API_RESULT, //接口返回失败
    NONE_NETWORK,//无有网络
    DATA_PARSE, //数据解析异常
    UNKNOWN //未知异常

}
