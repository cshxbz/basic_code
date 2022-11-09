package com.hxb.demo.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hxb.demo.databinding.ActivityMainBinding
import com.hxb.demo.http.CommonApiCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }
    }


    private fun callApi() {
        lifecycleScope.launch(Dispatchers.Main) {
            CommonApiCreator.create().getUserInfo()

        }
    }

}