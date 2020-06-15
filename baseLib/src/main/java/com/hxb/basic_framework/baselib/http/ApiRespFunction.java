package com.hxb.basic_framework.baselib.http;


import com.hxb.basic_framework.baselib.exception.ApiResultException;
import com.hxb.basic_framework.baselib.http.resp.IResp;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 处理服务端接口返回数据的Function,在使用FlatMap操作符进行连续调用服务端接口时使用此Function
 * @author hxb
 */
public abstract class ApiRespFunction<T extends IResp, R extends Observable<? extends IResp>> implements Function<T, R> {

    /**
     * 请求成功回调此方法
     * 返回下一个要进行的请求
     */
    public abstract R onSuccess(T resp);


    @Override
    public R apply(T resp) throws Exception {
        if(resp.isSuccess()){
            R nextObservable = onSuccess(resp);
            if(nextObservable!=null){
                return nextObservable;
            }else {
                throw new Exception("next observable is null");
            }
        }else{
            throw new ApiResultException(resp.getMessage());
        }
    }
}