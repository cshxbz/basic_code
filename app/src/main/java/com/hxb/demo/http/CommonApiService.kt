package com.hxb.demo.http

import com.hxb.demo.bean.UserInfo
import com.hxb.network.http.bean.CommonApiResp
import retrofit2.Call
import retrofit2.http.GET

interface CommonApiService {

    @GET("m1/1971182-0-default/getUserInfo")
    fun getUserInfo(): Call<CommonApiResp<UserInfo?>?>


}