package com.hxb.network.http

import com.hxb.baselib.utils.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

open class RetrofitApiCreator {

    protected lateinit var okhttpClient: OkHttpClient

    fun build(baseUrl: String) {
        val build = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(buildOkhttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    open fun buildOkhttpClient(): OkHttpClient {
        okhttpClient = OkHttpClient.Builder()
                .addInterceptor(buildLoggingInterceptor())
                .build()
        return okhttpClient
    }


    private fun buildLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                LogUtil.i(msg = message)
            }
        })

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


}