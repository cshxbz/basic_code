package com.hxb.basic_framework.baselib.http;


import com.hxb.basic_framework.baselib.exception.ApiResultException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 处理服务端接口返回数据的Function,在使用FlatMap操作符进行连续调用服务端接口时使用此Function
 * @author hxb
 */
public abstract class RespFunction<T, R> implements Function<CommonResp<T>, Observable<CommonResp<R>>> {

    /**
     * 请求成功回调此方法
     * 返回下一个要进行的请求
     */
    public abstract Observable<CommonResp<R>> onSuccess(CommonResp<T> resp);


    @Override
    public Observable<CommonResp<R>> apply(CommonResp<T> resp) throws Exception {
        if(resp.isSuccess()){
            Observable<CommonResp<R>> nextObservable = onSuccess(resp);
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
