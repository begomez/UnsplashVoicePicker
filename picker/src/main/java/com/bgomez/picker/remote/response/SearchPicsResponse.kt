package com.bgomez.picker.remote.response


import com.bgomez.picker.common.models.PicData
import com.google.gson.annotations.SerializedName


/**
 * Data model for search responses
 *
 * Created by bernatgomez on July,2020
 */
data class SearchPicsResponse(

    @SerializedName("results")
    val results : List<PicData>
)