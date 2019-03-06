package com.hxb.basicframework.http;

import com.hxb.basic_framework.baselib.http.RetrofitCreateHelper;


public class ApiCreator extends RetrofitCreateHelper {

    private final static String BASE_URL = "https://www.easy-mock.com/mock/5c273d1cd746c431566468a0/android_mock_data/";

    public static <T> T createApi(Class<T> clazz) {
        return createApi(clazz, BASE_URL);
    }

}
