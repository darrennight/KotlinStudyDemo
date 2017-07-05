package com.qs.bnb.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.qs.bnb.application.BnbApplication

/**
 * Created by zenghao on 2017/6/29.
 */
class BnbDBHelper (context: Context = BnbApplication.instance) : SQLiteOpenHelper(context,name,null, version){

    companion object{
        //数据库名字
        val name = "first.db"
        //版本
        val version = 1
        val instance by lazy { BnbDBHelper() }
        //表名
        val TABLE_NAME by lazy { "user" }
        val sql by lazy {
            //kotlin字符串模版方式
            "create table if not exists $TABLE_NAME (Id integer primary key, CustomName text, OrderPrice integer, Country text)"
        }

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion){
//                <=newversion
            for (i in oldVersion..newVersion){
                updateByVersion(i)
            }
        }
    }

    private fun updateByVersion(version:Int){

        when (version){
            1->{
                print("升级")
                print("升级")
                print("升级")
                print("升级")
            }

            2-> print("升级")
            3-> print("升级")
            4-> print("升级")
            else-> print("不做操作")
        }
    }
}