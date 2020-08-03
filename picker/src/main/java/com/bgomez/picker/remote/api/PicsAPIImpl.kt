package com.bgomez.picker.remote.api


import com.bgomez.picker.data.interfaces.IPicsAPI
import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.remote.request.SearchPicsRequest
import com.bgomez.picker.remote.service.UnsplashService
import com.bgomez.picker.remote.service.factory.RetrofitServFactory
import javax.inject.Inject


/**
 * Pics API impl
 *
 * Created by bernatgomez on July,2020
 */
class PicsAPIImpl @Inject constructor(
    private val service : UnsplashService = RetrofitServFactory.createUnsplashService()) : IPicsAPI {

    override suspend fun retrieveRandomPics() : List<PicData> {
        return this.service.fetchPics().await()
    }

    override suspend fun queryPics(req: SearchPicsRequest): List<PicData> {
        return this.service.searchPics(
            query= req.query,
            page = req.page,
            numResults = req.numResults
        ).await().results
    }

}