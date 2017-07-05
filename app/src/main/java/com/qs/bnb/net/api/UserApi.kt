package com.qs.bnb.net.api

import com.qs.bnb.config.Constant
import com.qs.bnb.net.HttpBaseModel
import com.qs.bnb.net.bean.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by zenghao on 2017/6/7.
 */
interface UserApi {

    @FormUrlEncoded
    @POST(Constant.LOGIN)
    fun accountLogin(@Field("username")name:String, @Field("password")password:String): Call<HttpBaseModel<User>>



    @FormUrlEncoded
    @POST(Constant.LOGIN)
    fun accountLoginTest(@Field("username")name:String, @Field("password")password:String): Call<Any>

}