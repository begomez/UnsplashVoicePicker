package com.bgomez.picker.remote.request


/**
 * Data model for search requests
 *
 * Created by bernatgomez on July,2020
 */
data class SearchPicsRequest(val query: String = "", val page : Int = 1, val numResults : Int = 50)