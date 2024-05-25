package com.hxb.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.hxb.demo.databinding.ActivityIpcDemoBinding
import com.hxb.demo.databinding.ActivityMainBinding

class IPCDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIpcDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIpcDemoBinding.inflate(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }

        initViewsListener()
    }


    private fun initViewsListener() {
        binding.btnBookManagerDemo.setOnClickListener {
            startActivity(Intent(this, BookManagerDemoActivity::class.java))
        }

    }

}