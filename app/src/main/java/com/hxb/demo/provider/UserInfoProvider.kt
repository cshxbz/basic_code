package com.hxb.demo.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import com.hxb.baselib.log.logI

class UserInfoProvider : ContentProvider() {

    companion object {
        private const val TAG = "UserInfoProvider"
    }

    override fun onCreate(): Boolean {
        logI(TAG, "onCreate -- $this")
        return true
    }


    override fun call(method: String, arg: String?, extras: Bundle?): Bundle? {
        synchronized(this) {
            logI(TAG, "call -- method: $method , thread: ${Thread.currentThread().name}")
            return null
        }
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}