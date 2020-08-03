package com.bgomez.picker.common.models


/**
 * Error code constants
 */
interface ErrorCodes {
    companion object {
        const val FETCH_PICS = 1
    }
}


/**
 * Data model for errors
 *
 * Created by bernatgomez on August,2020
 */
data class ErrorData(val code : Int, val msg : String)