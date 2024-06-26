package com.hxb.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.hxb.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }

        initViewsListener()
    }


    private fun initViewsListener() {
        binding.btnGotoIpcTestPage.setOnClickListener {
            startActivity(Intent(this, IPCDemoActivity::class.java))
        }

    }





}