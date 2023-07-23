package com.hxb.baselib.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException


private fun buildGson(): Gson {
    return GsonBuilder()
        .create()
}


fun <T> json2Obj(json: String, classOfT: Class<T>): T? {
    return try {
        buildGson().fromJson(json, classOfT)
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
        null
    }
}


fun obj2Json(obj: Any): String {
    return buildGson().toJson(obj)
}