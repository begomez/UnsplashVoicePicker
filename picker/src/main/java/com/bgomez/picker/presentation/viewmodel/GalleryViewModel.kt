package com.bgomez.picker.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgomez.picker.data.impl.PicsRepositoryImpl
import com.bgomez.picker.domain.interfaces.IPicsRepository
import com.bgomez.picker.common.models.ErrorCodes
import com.bgomez.picker.common.models.ErrorData
import com.bgomez.picker.presentation.result.ResourceResult
import com.bgomez.picker.presentation.result.Status
import com.bgomez.picker.common.utils.LogWrapper
import kotlinx.coroutines.launch
import javax.inject.Inject


val TAG  = GalleryViewModel::class.simpleName!!


/**
 * Gallery viewmodel
 *
 * @param T: data type for information retrieved and stored
 * @param P: data type for params
 *
 * Created by bernatgomez on August,2020
 */
class GalleryViewModel<T, in P> @Inject constructor(
        private val repo : IPicsRepository = PicsRepositoryImpl()
    ) : ViewModel() {

    private val publisher : Publisher = Publisher()

    private var privData : MutableLiveData<ResourceResult<T>> = MutableLiveData()



    fun searchPics(query : String) {
        try {

            this.viewModelScope.launch {
                publisher.loading()

                val data = (if (query.isEmpty()) repo.fetchPics() else repo.searchPics(query, 1)) as T

                publisher.result(data)
            }

        } catch (t : Throwable) {
            LogWrapper.e(TAG, "searchPics()", t)

            this.publisher.error(ErrorCodes.FETCH_PICS)

        } catch (e : Exception) {
            LogWrapper.e(TAG, "searchPics()", e)

            this.publisher.error(ErrorCodes.FETCH_PICS)
        }
    }

    /**
     * Class for param encapsulation
     */
    data class Params private constructor(val query: String) {
        companion object Builder {
            fun build(query: String) =
                Params(
                    query = query
                )
        }
    }

    /**
     * Helper class that exposes results
     */
    inner class Publisher() {
        val pubData : MutableLiveData<ResourceResult<T>>
            get() = this@GalleryViewModel.privData

        fun loading() {
            this.pubData.postValue(
                ResourceResult(
                    status = Status.LOADING,
                    data = null,
                    error = null
                )
            )
        }

        fun result(data : T) {
            this.pubData.postValue(
                ResourceResult(
                    status = Status.OK,
                    data = data,
                    error = null
                )
            )
        }

        fun error(code : Int) {
            this.pubData.postValue(
                ResourceResult(
                    status = Status.KO,
                    data = null,
                    error = ErrorData(code = code, msg = "")
                )
            )
        }
    }
}