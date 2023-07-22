package com.hxb.demo.http

import com.hxb.baselib.utils.LogUtil
import com.hxb.network.http.RetrofitApiCreator
import okhttp3.Interceptor
import okhttp3.OkHttpClient

object ApiCreator : RetrofitApiCreator() {

    private const val BASE_URL = ""

    fun create(): CommonApiService {
        return super.create(BASE_URL, CommonApiService::class.java)
    }


    override fun customizeOkHttpClient(builder: OkHttpClient.Builder) {
        builder.addInterceptor(Interceptor { chain ->
            val request = chain.request()
            LogUtil.i(msg = "perform custom okhttp interceptor...")
            chain.proceed(request)
        })
    }

}