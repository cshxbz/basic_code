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
public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_WRITE = 20;
    private static final int TIMEOUT_CONNECTION = 10;

    private static OkHttpClient okHttpClient;

    static {
        initOkHttpClient();
    }

    /**
     * 初始化okHttp
     */
    private static void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder
                //打印日志的拦截器
                .addInterceptor(buildLoggingInterceptor())
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true);

        okHttpClient = customBuildOkHttpClient(builder).build();
    }


    /**
     * 子类可重写此方法对okHttpClient进行自定义配置
     *
     * @param builder
     * @return
     */
    protected static OkHttpClient.Builder customBuildOkHttpClient(OkHttpClient.Builder builder) {
        return builder;
    }

    /**
     * 创建打印日志的拦截器
     */
    private static HttpLoggingInterceptor buildLoggingInterceptor() {
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
