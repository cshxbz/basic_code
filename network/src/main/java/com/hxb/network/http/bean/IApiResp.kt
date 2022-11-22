package com.hxb.network.http.bean

/**
 * 用来表示服务端接口响应数据实体类都要实现这个interface
 */
interface IApiResp<out T> {
    val data: T?
    val code: Int?
    val message: String?
    fun isSuccess(): Boolean

}