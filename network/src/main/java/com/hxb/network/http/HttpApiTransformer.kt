package com.hxb.network.http

import com.google.gson.JsonParseException
import com.hxb.network.http.bean.CommonApiResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 *  这里定义了一个[Call]的扩展函数，可以使用[Call]对象直接调用此函数
 *  此函数内部调用[Call.enqueue]来发起请求，将回调转成了挂起方法同步返回的形式，用来支持在协程内调用
 *  并且对请求响应结果进行的包装
 */
suspend fun <D> Call<CommonApiResp<D?>?>.suspendRequest(): HttpApiResult<D?> {

    return withContext(Dispatchers.IO) {

        return@withContext suspendCoroutine<HttpApiResult<D>> {

            enqueue(object : Callback<CommonApiResp<D?>?> {
                override fun onResponse(
                    call: Call<CommonApiResp<D?>?>,
                    response: Response<CommonApiResp<D?>?>
                ) {

                    val apiResp = response.body()
                    if (apiResp?.isSuccess() == true) {

                        it.resume(
                            HttpApiResult.Success(
                                data = apiResp.data,
                                code = apiResp.code,
                                message = apiResp.message
                            )
                        )

                    } else {

                        it.resume(
                            HttpApiResult.Failure.ApiAbnormalFailure(
                                message = apiResp?.message,
                                errorCode = apiResp?.code
                            )
                        )

                    }

                }

                override fun onFailure(call: Call<CommonApiResp<D?>?>, t: Throwable) {

                    val failedApiResult = when (t) {
                        is HttpException -> {
                            HttpApiResult.Failure.HttpFailure(t.message ?: "", t.code())
                        }
                        is JSONException, is JsonParseException -> {
                            HttpApiResult.Failure.JsonParseFailure(t.message ?: "", t)
                        }
                        is IOException -> {
                            HttpApiResult.Failure.WithoutNetworkFailure(t.message ?: "")
                        }
                        else -> {
                            HttpApiResult.Failure.UnknownFailure("未知异常", t)
                        }
                    }

                    it.resume(failedApiResult)

                }


            })


        }


    }

}



