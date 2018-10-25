package com.hxb.basicframework.utils;

import android.util.Log;

/**
 *
 * @author hxb
 */
public class L {

    private static final String GLOBAL_LOG_TAG="GLOBAL_LOG_TAG";

    public static void i(String msg){
        Log.i(GLOBAL_LOG_TAG, msg);
    }
}
