package com.hxb.network.http.bean

class CommonApiResp<T> : IApiResp<T> {

    override val data: T? = null
    override val code: Int? = null
    override val message: String? = null

    override fun isSuccess(): Boolean {
        return code != null && code == 0
    }


}