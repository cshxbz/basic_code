package com.hxb.baselib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 */
public class GsonUtil {

    public static Gson get(){
        return new GsonBuilder().create();
    }
}
