package com.hxb.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.hxb.demo.databinding.ActivityMultiProcessAccessProviderBinding
import com.hxb.demo.service.accessprovider.AccessProviderService01
import com.hxb.demo.service.accessprovider.AccessProviderService02
import com.hxb.demo.service.accessprovider.AccessProviderService03

class MultiProcessAccessProviderActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MultiProcessAccessProviderActivity"
    }

    private lateinit var binding: ActivityMultiProcessAccessProviderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiProcessAccessProviderBinding.inflate(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }

        initViewsListener()
    }


    private fun initViewsListener() {
        binding.btnStartAccessProviderService01.setOnClickListener {
            startService(Intent(this, AccessProviderService01::class.java))
        }
        binding.btnStartAccessProviderService02.setOnClickListener {
            startService(Intent(this, AccessProviderService02::class.java))
        }
        binding.btnStartAccessProviderService03.setOnClickListener {
            startService(Intent(this, AccessProviderService03::class.java))
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, AccessProviderService01::class.java))
        stopService(Intent(this, AccessProviderService02::class.java))
        stopService(Intent(this, AccessProviderService03::class.java))
    }


}