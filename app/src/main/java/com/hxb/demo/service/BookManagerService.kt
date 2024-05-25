package com.hxb.demo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.hxb.baselib.log.logI
import com.hxb.demo.Book
import com.hxb.demo.IBookManager
import com.hxb.demo.ui.activity.BookManagerDemoActivity
import java.util.concurrent.CopyOnWriteArrayList

class BookManagerService : Service() {

    companion object {
        private const val TAG = "BookManagerService"
    }

    val mBookList = CopyOnWriteArrayList<Book>()

    private val mBinder = object : IBookManager.Stub() {
        override fun getBookList(): MutableList<Book> {
            logI(TAG, "getBookList -- ${Thread.currentThread().name}")
            logI(TAG,"service getBookList invoke end !")
            return mBookList
        }

        override fun addBook(book: Book?) {
            logI(
                TAG,
                "addBook -- ${Thread.currentThread().name} ${book?.bookId} , ${book?.bookName}"
            )
            mBookList.add(book)
            logI(TAG,"service addBook invoke end !")
        }
    }


    override fun onCreate() {
        super.onCreate()
        logI(TAG,"onCreate -- ${Thread.currentThread().name}")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logI(TAG,"onStartCommand -- ${Thread.currentThread().name}")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        logI(TAG,"onBind -- ${Thread.currentThread().name}")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        logI(TAG,"onUnbind -- ${Thread.currentThread().name}")

        return super.onUnbind(intent)
    }


}