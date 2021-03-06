package com.qs.bnb.ui

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.qs.bnb.R
import com.qs.bnb.net.ApiService
import com.qs.bnb.net.HttpBaseModel
import com.qs.bnb.net.api.RoomApi
import com.qs.bnb.net.bean.Room
import com.qs.bnb.net.bean.RoomData
import com.qs.bnb.ui.adapter.RoomListAdapter
import com.qs.bnb.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by zenghao on 2017/6/7.
 */
class MainActivity :BaseActivity(){

    var list = ArrayList<Room>()
    var adapter: RoomListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView(){


        rv_room_list.layoutManager = LinearLayoutManager(this)
        rv_room_list.itemAnimator = DefaultItemAnimator()
        adapter = RoomListAdapter(this@MainActivity,list)
        rv_room_list.adapter = adapter

    }

    private fun initData(){
        getRoom()
    }



    private fun getRoom(){
        var api = ApiService.createService(RoomApi::class.java)
        var call = api.getRoomLit(1,20)
        call.enqueue(object :Callback<HttpBaseModel<RoomData>>{
            override fun onResponse(call: Call<HttpBaseModel<RoomData>>, response: Response<HttpBaseModel<RoomData>>) {
                if (response.isSuccessful){
                    var data = response.body()?.data?.room_list
                    if(data!=null){
                        list.addAll(data)
                        adapter?.notifyDataSetChanged()
                    }
                    toast("hahah")

                }
            }

            override fun onFailure(call: Call<HttpBaseModel<RoomData>>?, t: Throwable?) {
                toast("加载失败")
            }
        })

    }
}