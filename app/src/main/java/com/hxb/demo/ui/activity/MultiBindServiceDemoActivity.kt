package com.hxb.demo.ui.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.hxb.baselib.log.logI
import com.hxb.demo.ICustomService
import com.hxb.demo.databinding.ActivityMultiBindServiceBinding
import com.hxb.demo.service.CustomService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MultiBindServiceDemoActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MultiBindServiceDemoActivity"
    }

    private lateinit var binding: ActivityMultiBindServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiBindServiceBinding.inflate(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }

        initViewsListener()
    }


    private fun initViewsListener() {
        binding.btnBindCustomServiceConn1.setOnClickListener {
            bindCustomService("conn_1")
        }
        binding.btnBindCustomServiceConn2.setOnClickListener {
            bindCustomService("conn_2")

        }
        binding.btnBindCustomServiceConn3.setOnClickListener {
            bindCustomService("conn_3")

        }
    }


    private fun bindCustomService(connName: String) {
        val intent = Intent(this, CustomService::class.java)
        val conn = CustomServiceConn(connName, lifecycleScope)
        bindService(intent, conn, BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        super.onDestroy()

    }


    class CustomServiceConn(val name: String, private val coroutineScope: LifecycleCoroutineScope) : ServiceConnection {
        private var mService: ICustomService? = null

        companion object {
            private const val TAG = "CustomServiceConn"
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            logI(
                TAG,
                "onServiceConnected -- thread: ${Thread.currentThread().name} ,  name: $name , binder: $service"
            )
            mService = ICustomService.Stub.asInterface(service)
            startContinueSendMsgJob()
        }

        private fun startContinueSendMsgJob() {
            coroutineScope.launch(Dispatchers.Default) {
                while (true) {
                    mService?.sendMessage("this is $name message ! thread: ${Thread.currentThread().name}")
                }
            }
        }


        override fun onServiceDisconnected(name: ComponentName?) {
            logI(
                TAG,
                "onServiceDisconnected -- thread: ${Thread.currentThread().name} ,  name: $name"
            )
        }

    }


}