package com.hxb.basic_framework.baselib.http;

import com.hxb.basic_framework.baselib.utils.GsonUtil;
import com.hxb.basic_framework.baselib.utils.L;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author hxb
 */
public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;

    //logger
    private static final HttpLoggingInterceptor.Logger httpLogger = new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印网络请求响应日志
            L.i(message);
        }
    };

    //打印日志的拦截器
    private static final HttpLoggingInterceptor loggingIntercept = new HttpLoggingInterceptor(httpLogger)
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //打印日志
            .addInterceptor(loggingIntercept)
            //time out
            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build();

    public static <T> T createApi(Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.get()))
                .build();
        return retrofit.create(clazz);
    }
}
