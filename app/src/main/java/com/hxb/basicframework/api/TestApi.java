package com.hxb.basicframework.api;

import com.hxb.basicframework.entity.resp.CommonResp;
import com.hxb.basicframework.entity.resp.DataA;
import com.hxb.basicframework.entity.resp.DataB;
import com.hxb.basicframework.ui.activity.ApiReqTestActivity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestApi {

    @POST("test1")
    Observable<CommonResp<DataA>> getDataA(@Body ApiReqTestActivity.TestParams params);

    @POST("test2")
    Observable<CommonResp<DataB>> getDataB();
}
