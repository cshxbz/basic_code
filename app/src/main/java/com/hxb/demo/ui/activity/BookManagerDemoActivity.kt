package com.hxb.demo.ui.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hxb.baselib.log.logI
import com.hxb.demo.Book
import com.hxb.demo.IBookManager
import com.hxb.demo.databinding.ActivityBookManagerDemoBinding
import com.hxb.demo.service.BookManagerService
import java.util.UUID

class BookManagerDemoActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "BookManagerDemoActivity"
    }

    private lateinit var binding: ActivityBookManagerDemoBinding
    private var mService: IBookManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookManagerDemoBinding.inflate(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }

        initViewsListener()
    }


    private fun initViewsListener() {
        binding.btnBindBookManagerService.setOnClickListener {
            bindBookManagerService()
        }

        binding.btnAddBook.setOnClickListener {
            val inputBookName = binding.etBookName.text.toString()
            if (inputBookName.isBlank()) return@setOnClickListener

            val book = Book().apply {
                bookId = UUID.randomUUID().toString()
                bookName = inputBookName
            }
            mService?.addBook(book)
            logI(TAG,"client addBook invoke end !")
        }

        binding.btnGetBookList.setOnClickListener {
            val bookList = mService?.bookList
            if (bookList.isNullOrEmpty()) return@setOnClickListener

            val sb = StringBuilder()
            bookList.forEach {
                sb.append("name: ${it.bookName} -- id: ${it.bookId}\n")
            }

            logI(TAG, "client getBookList: \n $sb")
        }


    }


    private val serviceConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            logI(TAG, "onServiceConnected -- name: $name , binder: $service")
            mService = IBookManager.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            logI(TAG, "onServiceDisconnected -- name: $name")
        }


    }

    private fun bindBookManagerService() {
        val intent = Intent(this, BookManagerService::class.java)
        bindService(intent, serviceConn, BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConn)
    }


}