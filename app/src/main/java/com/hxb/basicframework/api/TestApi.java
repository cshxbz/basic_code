package com.hxb.basicframework.api;

import com.hxb.basic_framework.baselib.http.CommonResp;
import com.hxb.basicframework.entity.resp.DataA;
import com.hxb.basicframework.entity.resp.DataB;
import com.hxb.basicframework.ui.activity.MainActivity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestApi {

    @POST("test1")
    Observable<CommonResp<DataA>> justTest(@Body MainActivity.TestParams params);

    @POST("test2")
    Observable<CommonResp<DataB>> justTest2();
}
