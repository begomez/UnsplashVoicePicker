package com.bgomez.picker.common.utils


import android.util.Log


/**
 * Android logger wrapper
 *
 * Created by bernatgomez on July,2020
 */
object LogWrapper {

    fun e(tag : String, msg : String, e : Throwable?) {
        Log.e(tag, msg, e)
    }

    fun d(tag : String, msg : String) {
        Log.d(tag, msg)
    }
}