package com.bgomez.picker.ui


/**
 * Constant values on UI layer
 *
 * Created by bernatgomez on August,2020
 */
interface Constants {

    interface RequestCodes {
        companion object {
            const val REQUEST_PICKER = 1234
        }
    }

    interface ParamNames {
        companion object {
            const val PARAM_QUERY = "query"
            const val PARAM_IMAGE_SELECTED = "image_selected"
        }
    }
}