package com.hxb.demo.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.hxb.baselib.utils.LogUtil
import com.hxb.demo.databinding.ActivityMainBinding
import com.hxb.demo.http.CommonApiCreator
import com.hxb.network.http.HttpApiResult
import com.hxb.network.http.transHttpApiResp
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

            val apiResult = transHttpApiResp {
                CommonApiCreator.create().getUserInfo()
            }

            when (apiResult) {
                is HttpApiResult.Success -> {
                    val data = apiResult.data
                    LogUtil.i(msg = "success -- data: $data")
                }
                is HttpApiResult.Failure -> {
                    LogUtil.i(msg = "failure -- message: ${apiResult.message}")
                }
            }




        }

    }




}