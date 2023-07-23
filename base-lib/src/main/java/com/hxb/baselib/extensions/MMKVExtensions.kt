package com.hxb.baselib.extensions

import com.hxb.baselib.log.logI
import com.hxb.baselib.utils.json2Obj
import com.hxb.baselib.utils.obj2Json
import com.tencent.mmkv.MMKV

private const val TAG = "MMKVExtensions"

fun <T> MMKV?.getObject(key: String, classOfT: Class<T>): T? {
    val json = this?.getString(key, "")
    if (json.isNullOrBlank()) {
        logI(TAG, "getObject -- key:$key  json.isNullOrBlank()")
        return null
    }
    logI(TAG, "getObject -- key:$key  json: $json")
    return json2Obj(json, classOfT)
}

fun MMKV?.putObject(key: String, objData: Any) {
    val json = obj2Json(objData)
    logI(TAG, "putObject -- key:$key json: $json")
    this?.putString(key, json)
}
