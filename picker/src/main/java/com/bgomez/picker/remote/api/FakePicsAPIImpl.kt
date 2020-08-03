package com.bgomez.picker.remote.api

import com.bgomez.picker.data.interfaces.IPicsAPI
import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.common.models.UrlData
import com.bgomez.picker.remote.request.SearchPicsRequest
import javax.inject.Inject


/**
 * Fake pics API impl
 *
 * Created by bernatgomez on July,2020
 */
class FakePicsAPIImpl @Inject constructor() : IPicsAPI {

    companion object {
        const val FAKE_LENGTH = 10
        const val FAKE_ID = "1L"
        const val FAKE_URL = "https://images.unsplash.com/photo-1561409695-ce8315e7b9a6?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjc3ODQzfQ"
    }

    override suspend fun queryPics(req : SearchPicsRequest) = this.pics()

    override suspend fun retrieveRandomPics() = this.pics()

    private suspend fun pics() =
        ArrayList<PicData>(10).apply {
            repeat(FAKE_LENGTH) {
                add(PicData(
                    id = FAKE_ID,
                    urls = UrlData(
                        raw = FAKE_URL,
                        regular = FAKE_URL,
                        small = FAKE_URL,
                        thumb = FAKE_URL)
                    )
                )
            }
        }
}