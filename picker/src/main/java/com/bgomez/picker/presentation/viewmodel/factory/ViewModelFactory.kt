package com.bgomez.picker.presentation.viewmodel.factory


import androidx.lifecycle.ViewModelProvider
import com.bgomez.picker.presentation.viewmodel.GalleryViewModel


/**
 * Factory for viewmodel presentation objects
 *
 * Created by bernatgomez on August,2020
 */
object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    /**
     * Returns a viewmodel for pics gallery
     *
     * @param T: data type for information retrieved
     * @param P: data type for params
     */
    fun<T, P> createGalleryVM() = GalleryViewModel<T, P>()
}