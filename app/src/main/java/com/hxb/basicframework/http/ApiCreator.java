package com.hxb.basicframework.http;

import com.hxb.basic_framework.baselib.http.RetrofitApiCreator;
import com.hxb.basic_framework.baselib.utils.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ApiCreator {

    private final static String BASE_URL = "https://www.easy-mock.com/mock/5c273d1cd746c431566468a0/android_mock_data/";

    private static RetrofitApiCreator sParentApiCreator;

    private static final Interceptor customInterceptor = chain -> {
        Response resp = chain.proceed(chain.request());
        Logger.i("customInterceptor intercept");
        return resp;
    };

    public static  <T> T createApi(Class<T> clazz) {
        if(sParentApiCreator ==null){
            sParentApiCreator = RetrofitApiCreator.getInstance();
            configOkHttpClient();
        }
        return sParentApiCreator.createApi(clazz, BASE_URL);
    }


    private static void configOkHttpClient(){
        OkHttpClient.Builder builder = sParentApiCreator.getOkHttpClient().newBuilder();
        builder.addInterceptor(customInterceptor);
        sParentApiCreator.customBuildOkHttpClient(builder);
    }


}
