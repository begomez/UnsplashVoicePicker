package com.bgomez.picker.remote.service


import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.remote.metadata.UrlAnnotation
import com.bgomez.picker.remote.response.SearchPicsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Abstraction for Unsplash API
 *
 * Created by bernatgomez on July,2020
 */
@UrlAnnotation(url= "https://api.unsplash.com")
interface UnsplashService {

    @GET("/search/photos")
    fun searchPics(@Query("query") query : String, @Query("page") page : Int = 1, @Query("per_page") numResults : Int = 50) : Deferred<SearchPicsResponse>

    @GET("/photos/")
    fun fetchPics() : Deferred<List<PicData>>
}