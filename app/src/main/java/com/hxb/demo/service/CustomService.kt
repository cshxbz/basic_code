package com.hxb.demo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.hxb.baselib.log.logI
import com.hxb.demo.Book
import com.hxb.demo.IBookManager
import com.hxb.demo.ICustomService
import com.hxb.demo.ui.activity.BookManagerDemoActivity
import java.util.concurrent.CopyOnWriteArrayList

class CustomService : Service() {

    companion object {
        private const val TAG = "CustomService"
    }


    private val mBinder = object : ICustomService.Stub() {
        override fun sendMessage(msg: String?): String {
            logI(TAG, "sendMessage --  thread: ${Thread.currentThread().name}  msg: $msg")
            Thread.sleep(1000)
            return "$msg-result"
        }
    }


    override fun onCreate() {
        super.onCreate()
        logI(TAG,"onCreate -- ${Thread.currentThread().name}")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logI(TAG,"onStartCommand -- ${Thread.currentThread().name}")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        logI(TAG,"onBind -- ${Thread.currentThread().name}")
        return mBinder
//        return null!!
    }

    override fun onUnbind(intent: Intent?): Boolean {
        logI(TAG,"onUnbind -- ${Thread.currentThread().name}")
        return super.onUnbind(intent)
    }


}