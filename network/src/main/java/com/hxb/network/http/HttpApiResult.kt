package com.hxb.network.http

/**
 * 这个密封类表示 http接口的调用结果
 * [HttpApiResult.Success] 表示调用成功
 *  [HttpApiResult.Failure] 表示失败
 *  [HttpApiResult.Failure] 还有一系列子类，子类是对错误情况的具体细分
 */
sealed class HttpApiResult<out T> {

    data class Success<out T>(val data: T?, val code: Int, val message: String) : HttpApiResult<T>()

    sealed class Failure(val message: String) : HttpApiResult<Nothing>() {
        /**
         * 这个错误类型表示：http请求成功，但是接口响应值返回的code表示错误
         */
        class ApiRespFailure(message: String, val errorCode: Int) : Failure(message)
        /**
         * 这个错误类型表示：当前没有连接网络
         */
        class WithoutNetworkFailure(message: String) : Failure(message)
        /**
         * 这个错误类型表示：http请求失败
         * @param httpStateCode http状态码
         */
        class HttpFailure(message: String, val httpStateCode: Int) : Failure(message)

        /**
         * 这个错误类型表示：Json数据解析失败
         */
        class JsonParseFailure(message: String, val e: Throwable) : Failure(message)

        /**
         * 未知错误
         */
        class UnknownFailure(message: String, val e: Throwable) : Failure(message)
    }
}