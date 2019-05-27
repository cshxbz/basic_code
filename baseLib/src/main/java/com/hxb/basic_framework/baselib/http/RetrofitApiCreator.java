package com.hxb.basic_framework.baselib.http;

import com.hxb.basic_framework.baselib.utils.GsonUtil;
import com.hxb.basic_framework.baselib.utils.Logger;

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
public class RetrofitApiCreator {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_WRITE = 20;
    private static final int TIMEOUT_CONNECTION = 10;

    private static RetrofitApiCreator sInstance;

    private OkHttpClient mOkHttpClient;

    private RetrofitApiCreator() {
        initOkHttpClient();
    }

    public static RetrofitApiCreator getInstance() {
        synchronized (RetrofitApiCreator.class) {
            if (sInstance == null) {
                sInstance = new RetrofitApiCreator();
            }
            return sInstance;
        }
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 初始化okHttpClient
     */
    private void initOkHttpClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                //打印日志的拦截器
                .addInterceptor(buildLoggingInterceptor())
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                .build();
    }


    /**
     * 外部可调用此方法对okHttpClient进行自定义配置
     * @param builder
     */
    public void customBuildOkHttpClient(OkHttpClient.Builder builder){
        if(builder!=null){
            mOkHttpClient = builder.build();
        }
    }


    /**
     * 创建打印日志的拦截器
     */
    private HttpLoggingInterceptor buildLoggingInterceptor() {
        HttpLoggingInterceptor.Logger httpLogger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印网络请求响应数据日志
                Logger.i(message);
            }
        };

        return new HttpLoggingInterceptor(httpLogger)
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public <T> T createApi(Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.get()))
                .build();
        return retrofit.create(clazz);
    }
}
