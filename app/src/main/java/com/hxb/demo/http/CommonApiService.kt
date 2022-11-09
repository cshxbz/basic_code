package com.hxb.demo.http

import retrofit2.http.GET

interface CommonApiService {

    @GET("user/getUserInfo")
    suspend fun getUserInfo()

}