package com.hxb.network.http

import com.hxb.baselib.utils.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

open class RetrofitApiCreator {

    private var retrofit: Retrofit? = null

    fun <T> create(baseUrl: String, service: Class<T>): T {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(buildOkHttpClient())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit!!.create(service)
    }


    private fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(buildLoggingInterceptor())
        customizeOkHttpClient(builder)
        return builder.build()
    }

    /**
     *  子类可重写这个方法来配置[OkHttpClient]
     */
    open fun customizeOkHttpClient(builder: OkHttpClient.Builder) {}

    private fun buildLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            LogUtil.i(msg = message)
        }

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


}