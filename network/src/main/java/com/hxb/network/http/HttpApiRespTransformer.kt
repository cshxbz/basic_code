package com.hxb.network.http

import com.blankj.utilcode.util.NetworkUtils
import com.google.gson.JsonParseException
import com.hxb.network.http.bean.CommonApiResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException


/**
 *  在协程中使用retrofit调用接口时，可以通过此方法进行转换响应结果，这个方法中会处理
 *  http请求异常，接口返回错误等情况，最终会返回 [HttpApiResult.Success] 或  [HttpApiResult.Failure]
 *  上层代码根据这个返回的类型做后续处理
 *  @param block 在传入的block中调用retrofit接口
 */
suspend fun <T> transHttpApiResp(block: suspend () -> CommonApiResp<T>): HttpApiResult<T?> {
    //判断网络是否已经连接
    val isNetworkConnected = withContext(Dispatchers.IO) {
        return@withContext NetworkUtils.isConnected()
    }
    if (!isNetworkConnected) {
        return HttpApiResult.Failure.WithoutNetworkFailure("没有连接网络")
    }
    try {
        val apiResp = block.invoke()
        return if (apiResp.isSuccess()) {
            HttpApiResult.Success(apiResp.data, apiResp.code, apiResp.message)
        } else {
            HttpApiResult.Failure.ApiRespFailure(apiResp.message, apiResp.code)
        }

    } catch (e: Exception) {

        return when (e) {
            is HttpException -> {
                HttpApiResult.Failure.HttpFailure(e.message ?: "", e.code())
            }
            is JSONException, is JsonParseException -> {
                HttpApiResult.Failure.JsonParseFailure(e.message ?: "", e)
            }
            else -> {
                HttpApiResult.Failure.UnknownFailure("未知异常", e)
            }

        }

    }

}