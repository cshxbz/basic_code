package com.hxb.demo.bean

data class UserInfo(
    var age: Int?,
    var gender: Int?,
//    var gender: Boolean?,
    var mobile: String?,
    var userId: String?,
    var userName: String?
){


    override fun toString(): String {
        return "UserInfo(age=$age, gender=$gender, mobile=$mobile, userId=$userId, userName=$userName)"
    }
}