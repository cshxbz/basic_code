package com.hxb.demo.http

import com.hxb.baselib.utils.LogUtil
import com.hxb.demo.BuildConfig
import com.hxb.network.http.RetrofitApiCreator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object CommonApiCreator : RetrofitApiCreator() {

    private const val BASE_URL = BuildConfig.BASE_URL

    fun create(): CommonApiService {
        return super.create(BASE_URL, CommonApiService::class.java)
    }


    override fun customizeOkHttpClient(builder: OkHttpClient.Builder) {
        builder.addInterceptor(object :Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                LogUtil.i(msg = "perform custom okhttp interceptor...")
                return chain.proceed(request)
            }

        })
    }

}