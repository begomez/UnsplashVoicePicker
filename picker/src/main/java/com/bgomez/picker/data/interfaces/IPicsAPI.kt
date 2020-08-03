package com.bgomez.picker.data.interfaces


import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.remote.request.SearchPicsRequest


/**
 * Pics API abstraction
 *
 * Created by bernatgomez on July,2020
 */
interface IPicsAPI {
    suspend fun retrieveRandomPics() : List<PicData>

    suspend fun queryPics(req : SearchPicsRequest) : List<PicData>
}