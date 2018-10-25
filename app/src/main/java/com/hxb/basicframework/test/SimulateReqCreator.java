package com.hxb.basicframework.test;


import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 模拟请求
 * @author hxb
 */
public class SimulateReqCreator {


    public static Observable<String> getReqA(){

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                JSONObject rootJo = new JSONObject();
                rootJo.put("status", 1);
                rootJo.put("message", "");


                JSONObject dataJo = new JSONObject();
                dataJo.put("name", "hxb");
                dataJo.put("age", 29);
                dataJo.put("gender", "male");

                rootJo.put("data", dataJo);

                e.onNext(rootJo.toString());
            }
        });

    }



    public static Observable<String> getReqB(){

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                JSONObject rootJo = new JSONObject();
                rootJo.put("status", 1);
                rootJo.put("message", "");


                JSONObject dataJo = new JSONObject();
                rootJo.put("data", dataJo);

                e.onNext(rootJo.toString());
            }
        });

    }


    public static Observable<String> getReqC(){

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {

                e.onNext("这是模拟格式错误的json");
            }
        });

    }




}
