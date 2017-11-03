package com.qs.bnb.ui.custom

import android.content.Context
import android.support.v4.view.ViewConfigurationCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration

/**
 * Created by zenghao on 2017/7/13.
 */
class CustomViewPager :ViewPager{

    private var isCanScroll = true
    private var minDistance = 0f
    private var mimMove = 0
    private var firstY = 0f
    private var firstX = 0f
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        mimMove = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context))
        minDistance = (mimMove*2).toFloat()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                firstY = ev.y
                firstX = ev.x

            }

            MotionEvent.ACTION_MOVE ->{

                val distanceY = Math.abs(ev.y-firstY)
                val distanceX = Math.abs(ev.x-firstX)


                if( distanceY> minDistance && isCanScroll && Math.abs(distanceY - distanceX)>0){
                    isCanScroll = false
                }
            }

            MotionEvent.ACTION_UP ->{
                firstY = 0f
                isCanScroll = true
            }

            else ->{

            }
        }

        if(!isCanScroll){
            return false
        }
        return super.onInterceptTouchEvent(ev)
    }

}