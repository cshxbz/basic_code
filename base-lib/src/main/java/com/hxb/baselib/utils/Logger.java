package com.hxb.baselib.utils;

import android.util.Log;

import com.hxb.basic_framework.baselib.BuildConfig;

/**
 *
 * @author hxb
 */
public class Logger {

    private static final String GLOBAL_LOG_TAG = "GLOBAL_LOG_TAG";

    public static void i(String msg){
        if(BuildConfig.DEBUG){
            Log.i(GLOBAL_LOG_TAG, msg);
        }
    }


    public static void e(String msg){
        if(BuildConfig.DEBUG){
            Log.e(GLOBAL_LOG_TAG, msg);
        }
    }


    public static void d(String msg){
        if(BuildConfig.DEBUG){
            Log.d(GLOBAL_LOG_TAG, msg);
        }
    }
}
