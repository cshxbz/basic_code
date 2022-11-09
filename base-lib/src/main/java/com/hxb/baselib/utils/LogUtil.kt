package com.hxb.baselib.utils

import android.util.Log
import com.hxb.baselib.BuildConfig

object LogUtil {

    private val openLog: Boolean = BuildConfig.DEBUG
//    private val openLog: Boolean = true
    private const val GLOBAL_TAG = "GLOBAL_LOG"

    fun i(tag: String = GLOBAL_TAG, msg: String) {
        if (openLog) {
            Log.i(tag, msg)
        }
    }


    fun v(tag: String = GLOBAL_TAG, msg: String) {
        if (openLog) {
            Log.v(tag, msg)
        }
    }

    fun e(tag: String = GLOBAL_TAG, msg: String) {
        if (openLog) {
            Log.e(tag, msg)
        }
    }

    fun w(tag: String = GLOBAL_TAG, msg: String) {
        if (openLog) {
            Log.w(tag, msg)
        }
    }

}