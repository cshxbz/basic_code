package com.hxb.demo

import android.app.Application
import com.hxb.baselib.log.logI

class MyApplication : Application() {

    companion object {
        var instance: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        logI(content = "MyApplication -- onCreate")
        instance = this
    }

}