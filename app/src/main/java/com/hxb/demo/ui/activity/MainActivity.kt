package com.hxb.demo.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonSyntaxException
import com.hxb.baselib.utils.LogUtil
import com.hxb.demo.databinding.ActivityMainBinding
import com.hxb.demo.http.CommonApiCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

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

            try {

                val userInfoResp = CommonApiCreator.create().getUserInfo()
                LogUtil.i(msg = "userInfoResp: $userInfoResp")


            } catch (e: Exception) {
                e.printStackTrace()
                when (e) {
                    is HttpException -> {//http响应错误

                    }
                    is JsonSyntaxException -> {//json数据解析错误

                    }
                    else -> {//未知错误


                    }
                }
            }


        }
    }

}