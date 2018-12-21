package com.hxb.basicframework.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TestApi {

    @GET("")
    Observable<String> justTest();
}
