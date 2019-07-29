package com.hxb.basic_framework.baselib.http;

import com.hxb.basic_framework.baselib.exception.ApiResultException;
import com.hxb.basic_framework.baselib.utils.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 *
 * 处理服务端接口返回数据的Observer
 * @author hxb
 */
public abstract class RespObserver<T> implements Observer<CommonResp<T>> {

    /**
     * 请求成功回调此方法
     */
    protected abstract void onSuccess(CommonResp<T> resp);


    /**
     * 请求结束回调此方法，不管是请求失还是成功都回回调此方法
     */
    protected abstract void onFinish(boolean isSuccess);

    /**
     * 请求失败，回调此方法
     */
    protected abstract void onFail(RespFailSpec failSpec);


    @Override
    public void onSubscribe(Disposable d) {
        Logger.i("onSubscribe");
    }


    @Override
    public void onNext(CommonResp<T> resp) {
        int status = resp.getStatus();
        boolean isSuccess = status == 1;
        if(isSuccess){
            onSuccess(resp);
        }else{
            onFail(new RespFailSpec(RespFailType.API_RESULT,resp.getMessage()));
        }
        onFinish(isSuccess);
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiResultException){//接口返回错误

            onFail(new RespFailSpec(RespFailType.API_RESULT,e.getMessage()));

        }else if(e instanceof org.json.JSONException ||
                 e instanceof com.google.gson.JsonParseException ||
                 e instanceof android.net.ParseException){//数据解析异常

            onFail(new RespFailSpec(RespFailType.DATA_PARSE,"数据解析异常"));

        }else{//其他未知异常
            onFail(new RespFailSpec(RespFailType.UNKNOWN,"未知异常"));
        }
        e.printStackTrace();
        onFinish(false);
    }

    @Override
    public void onComplete() {
        Logger.i("req complete");
    }

}
