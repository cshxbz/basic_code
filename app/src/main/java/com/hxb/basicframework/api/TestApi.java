package com.hxb.basicframework.api;

import com.hxb.basicframework.ui.activity.MainActivity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestApi {

    @POST("test1")
    Observable<String> justTest(@Body MainActivity.TestParams params);
}
