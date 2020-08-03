package com.bgomez.picker.domain.interfaces


import com.bgomez.picker.common.models.PicData


/**
 * Pics repository abstraction
 *
 * Created by bernatgomez on July,2020
 */
interface IPicsRepository {
    suspend fun fetchPics() : List<PicData>

    suspend fun searchPics(query : String, page : Int) : List<PicData>
}