package com.hxb.basic_framework.baselib.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.hxb.basic_framework.baselib.exception.ApiResultException;
import com.hxb.basic_framework.baselib.http.parser.CommonRespJsonRawParser;
import com.hxb.basic_framework.baselib.http.parser.RespRawParser;

import org.json.JSONException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 *
 * 处理服务端接口返回数据的Observer
 * @author hxb
 */
public abstract class RespObserver implements Observer<String> {

    private RespRawParser parser=new CommonRespJsonRawParser();

    private Context mContext;

    public RespObserver(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 求成功回调此方法
     * @param data
     */
    public abstract void onSuccess(String data);

    /**
     * 求失败，回调此方法
     */
    public void onFail(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onSubscribe(Disposable d) {
        Log.i("TTT", "req start");
    }

    @Override
    public void onNext(String rawData) {
        try {
            //解析接口返回的原始数据，判断接口调用是否成功
            parser.setRootData(rawData);
            parser.performParse();

            if(parser.isSuccess()){
                onSuccess(parser.getData());
            }else{
                onError(new ApiResultException(parser.getFailMsg()));
            }

        } catch (Exception e) {
            onError(e);
        }

    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiResultException){//接口返回错误

            onFail(e.getMessage());

        }else if(e instanceof JSONException ||
                 e instanceof JsonParseException){//数据解析异常

            onFail("数据解析异常");

        }else{//其他未知异常
            onFail(e.getMessage());
        }

        e.printStackTrace();
    }


    /**
     * 外部通过此方法设置一个parser,如果不设置，将使用默认parser
     * @param parser
     * @return
     */
    public RespObserver parser(RespRawParser parser){
        if(parser!=null){
            this.parser=parser;
        }
        return this;
    }


    @Override
    public void onComplete() {
        Log.i("TTT", "req complete");
    }
}
