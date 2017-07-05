package com.qs.bnb.net

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.qs.bnb.application.BnbApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by zenghao on 2017/6/6.
 */
class OkHttpManager private constructor() {

    var okHttpClient: OkHttpClient? = null

    init {
        val builder = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(BnbApplication.instance)))
                .addInterceptor(HttpInterceptor())


        //添加日志拦截器
            var logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logInterceptor)

        okHttpClient = builder.build()

    }

    companion object {
        fun getInstance(): OkHttpManager {
            return Holder.instance
        }

        fun initOkhttpForFresco(): OkHttpClient{
            return OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()
        }
    }

    private object Holder {
        val instance = OkHttpManager()
    }




}