package com.hxb.basic_framework.baselib.http.parser;


/**
 * 服务端接口原始数据解析器的基类
 * 这个基类定义了 用来判断服务器接口调用是否成功的方法
 * @author hxb
 */
public abstract class RespRawParser {

    /**
     * 在执行解析前，应该把原数据赋值到rootData字段
     */
    protected String rootData;
    /**
     * 在解析过程中，应该把实际的业务数据复制到data字段
     * 举例：假设返回的数据是json格式，rootData 为 {"status":1,"message","data":{"f1":"abc","f2":"def"}}
     * 那么 {"f1":"abc","f2":"def"} 应该赋值到data字段
     */
    protected String data;

    protected String failMsg="加载失败";//失败原因
    protected boolean success; //是否调用成功的标识属性

    /**
     * 子类实现这个方法来解析具体的接口返回数据
     * 判断接口是否调用成功（赋值到{@link #success}属性），并且获取到业务数据（赋值到{@link #data 属性）
     * 如果判断结果是调用失败，还需要为{@link #failMsg } 赋值
     */
    public abstract void performParse() throws Exception;

    public boolean isSuccess() {
        return success;
    }

    public void setRootData(String rootData) {
        this.rootData = rootData;
    }

    public String getData(){
        return data;
    }

    public String getFailMsg() {
        return failMsg;
    }
}
