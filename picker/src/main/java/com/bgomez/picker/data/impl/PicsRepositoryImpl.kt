package com.bgomez.picker.data.impl


import com.bgomez.picker.data.interfaces.IPicsAPI
import com.bgomez.picker.domain.interfaces.IPicsRepository
import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.remote.api.PicsAPIImpl
import com.bgomez.picker.remote.request.SearchPicsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Pics repository impl
 *
 * Created by bernatgomez on July,2020
 */
class PicsRepositoryImpl @Inject constructor(
    private val api : IPicsAPI = PicsAPIImpl()) : IPicsRepository {

    override suspend fun fetchPics(): List<PicData> {
        return withContext(Dispatchers.IO) {
            api.retrieveRandomPics()
        }
    }

    override suspend fun searchPics(query: String, page : Int): List<PicData> {
        return withContext(Dispatchers.IO) {
            api.queryPics(SearchPicsRequest(query= query))
        }
    }
}