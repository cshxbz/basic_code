package com.hxb.basic_framework.baselib.http;

import com.hxb.basic_framework.baselib.exception.ApiResultException;
import com.hxb.basic_framework.baselib.utils.L;

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
     * 请求失败，回调此方法
     */
    protected void onFail(String msg){

    }


    @Override
    public void onSubscribe(Disposable d) {
        L.i("onSubscribe");
    }


    @Override
    public void onNext(CommonResp<T> resp) {
        int status = resp.getStatus();
        if(status==1){
            onSuccess(resp);
        }else{
            onFail(resp.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiResultException){//接口返回错误

            onFail(e.getMessage());

        }else if(e instanceof org.json.JSONException ||
                 e instanceof com.google.gson.JsonParseException ||
                 e instanceof android.net.ParseException){//数据解析异常

            onFail("数据解析异常");

        }else{//其他未知异常
            onFail(e.getMessage());
        }

        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        L.i("req complete");
    }
}
