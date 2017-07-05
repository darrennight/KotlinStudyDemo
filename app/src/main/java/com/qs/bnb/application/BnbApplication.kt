package com.qs.bnb.application

import android.app.Application
import android.content.Context
import com.qs.bnb.net.FrescoManager
import kotlin.properties.Delegates

/**
 * Created by zenghao on 2017/6/27.
 */
class BnbApplication : Application(){


    companion object{
        var instance: BnbApplication by Delegates.notNull()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        FrescoManager.initFrescoWithOkHttpPieline(instance)
    }
}