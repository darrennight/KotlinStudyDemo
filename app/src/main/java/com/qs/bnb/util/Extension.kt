package com.qs.bnb.util

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import com.qs.bnb.application.BnbApplication

/**
 * Created by zenghao on 2017/6/5.
 */


 fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Any.getPackageName():String{
    return BnbApplication.instance.packageName
}

fun Any.getResource():Resources{
    return BnbApplication.instance.getResource()
}

