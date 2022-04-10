package com.hxb.basicframework.global

import android.app.Application

/**
 * @author hxb
 */
class App : Application() {

    lateinit var instance: App
    private set

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}