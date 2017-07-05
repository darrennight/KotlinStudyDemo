package com.qs.bnb.util

import android.content.Context
import com.qs.bnb.application.BnbApplication
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by zenghao on 2017/6/11.
 */
class Preference<T>() : ReadWriteProperty<Any?, T> {

    var key: String? = null
    var value: T? = null
    var fileName:String = "bnbPreference"

    /**
     * 主构造器和这个次构造器用于删除使用
     */
    constructor(fileName: String):this(){
        this.fileName = fileName
    }

    /**
     * 用于存储读取
     */
    constructor(name: String, default: T):this(){
        key = name
        value = default
    }
    /**
     * 用于存储读取
     */
    constructor(name: String,default: T,fileName:String):this(){
        key = name
        value = default
        this.fileName = fileName
    }

    val prefs by lazy { BnbApplication.instance.getSharedPreferences(fileName, Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(key!!, value!!)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(key!!, value)
    }

    /**
     * 删除
     */
    fun delete(vararg key: String): Unit {
        if (key == null || key.size == 0) {
            prefs.edit().clear().commit()
            return
        }else{
            for (item in key){
                prefs.edit().remove(item).commit()
            }
        }

    }

    private fun <U> findPreference(name: String, default: U): U = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("The data can not be saved")
        }
        res as U
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("The data can not be saved")
        }.apply()
    }
}
