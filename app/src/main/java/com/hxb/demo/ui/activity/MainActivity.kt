package com.hxb.demo.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hxb.demo.databinding.ActivityMainBinding
import com.hxb.demo.http.ApiCreator
import com.hxb.network.http.suspendRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }

        initListener()
    }


    private fun initListener() {
        binding.btnPerform.setOnClickListener {
            callApi()
        }
    }



    private fun callApi() {
        lifecycleScope.launch(Dispatchers.Main) {
            val result = ApiCreator.create().getUserInfo().suspendRequest()


        }

    }




}