package com.qs.bnb.ui

import android.content.Intent
import android.os.Bundle
import com.qs.bnb.R
import com.qs.bnb.net.ApiService
import com.qs.bnb.net.HttpBaseModel
import com.qs.bnb.net.api.UserApi
import com.qs.bnb.net.bean.User
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by zenghao on 2017/6/7.
 */
class LoginActivity : BaseActivity(){

    val account = "test001"
    val password = "test001"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        setListener()
    }

    private fun initView(){
        et_login_account.setText(account)
        et_login_pwd.setText(password)
    }

    private fun setListener(){
        btn_login.setOnClickListener {
            val name = et_login_account.text.toString().trim()
            val pwd = et_login_pwd.text.toString().trim()
            login(name,pwd)
        }
    }



    private fun login(account:String,password:String){
        var api = ApiService.createService(UserApi::class.java)
        var call = api.accountLogin(account,password)
        call.enqueue(object :Callback<HttpBaseModel<User>>{
            override fun onResponse(call: Call<HttpBaseModel<User>>?, response: Response<HttpBaseModel<User>>) {
                if(response.isSuccessful && response.body()?.status == 0){
                    var data = response.body()?.data
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<HttpBaseModel<User>>?, t: Throwable?) {
            }
        })
    }



}