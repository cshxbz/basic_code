package com.hxb.basic_framework.baselib.http;


import com.hxb.basic_framework.baselib.exception.ApiResultException;
import com.hxb.basic_framework.baselib.http.parser.CommonRespJsonRawParser;
import com.hxb.basic_framework.baselib.http.parser.RespRawParser;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *
 * 处理服务端接口返回数据的Function,在使用FlatMap操作符进行连续调用服务端接口时使用此Function
 * @author hxb
 */
public abstract class RespFunction implements Function<String, Observable<String>> {

    protected RespRawParser parser=new CommonRespJsonRawParser();
    /**
     * 请求成功回调此方法
     * 返回下一个要进行的请求
     */
    public abstract Observable<String> onSuccess(String data);

    @Override
    public Observable<String> apply(String resp) throws Exception {

        parser.setRootData(resp);
        parser.performParse();

        if(parser.isSuccess()){
            Observable<String> nextObservable = onSuccess(parser.getData());
            if(nextObservable!=null){
                return nextObservable;
            }else{
                throw new ApiResultException(parser.getFailMsg());
            }
        }else{
            throw new ApiResultException(parser.getFailMsg());

        }
    }


    /**
     * 外部通过此方法设置一个parser,如果不设置，将使用默认parser
     * @param parser
     * @return
     */
    public RespFunction parser(RespRawParser parser){
        if(parser!=null){
            this.parser=parser;
        }
        return this;
    }
}
