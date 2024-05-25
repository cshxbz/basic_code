package com.hxb.demo.service.accessprovider

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.hxb.baselib.log.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseAccessProviderService : Service() {

    abstract val tag: String

    companion object {
        private const val USER_INFO_PROVIDER_AUTHORITIES = "com.hxb.userInfo.provider"
    }

    override fun onCreate() {
        super.onCreate()
        logI(tag,"onCreate -- ")
    }

    private var accessProviderJob: Job? = null


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        accessProviderJob = GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                val bundle = contentResolver.call(USER_INFO_PROVIDER_AUTHORITIES, "send", null, null)
                logI(tag,"call provider end !")
            }
        }

        return START_NOT_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        logI(tag,"onDestroy -- ")
        accessProviderJob?.cancel()
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}