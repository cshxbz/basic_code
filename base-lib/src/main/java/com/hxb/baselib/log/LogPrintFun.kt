package com.hxb.baselib.log

import android.util.Log


private const val isOpenLog = true
private const val GLOBAL_TAG = "GLOBAL_TAG"

fun logI(tag: String = GLOBAL_TAG, content: String, writeToLocal: Boolean = false) {
    if (isOpenLog) {
        Log.i(tag, content)
    }
    writeToLocal(tag, content, writeToLocal)
}

fun logV(tag: String = GLOBAL_TAG, content: String, writeToLocal: Boolean = false) {
    if (isOpenLog) {
        Log.v(tag, content)
    }
    writeToLocal(tag, content, writeToLocal)
}

fun logE(tag: String = GLOBAL_TAG, content: String, writeToLocal: Boolean = false) {
    if (isOpenLog) {
        Log.e(tag, content)
    }
    writeToLocal(tag, content, writeToLocal)
}

fun logW(tag: String= GLOBAL_TAG, content: String,writeToLocal: Boolean = false) {
    if (isOpenLog) {
        Log.w(tag, content)
    }
    writeToLocal(tag, content, writeToLocal)
}

fun logD(tag: String = GLOBAL_TAG, content: String, writeToLocal: Boolean = false) {
    if (isOpenLog) {
        Log.d(tag, content)
    }
    writeToLocal(tag, content, writeToLocal)
}

private fun writeToLocal(tag: String, content: String, writeToLocal: Boolean) {
    if(writeToLocal){
        //调用工具类将日志写入本地文件
    }
}