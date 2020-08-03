package com.bgomez.picker.presentation.result


import com.bgomez.picker.common.models.ErrorData


enum class Status {LOADING, OK, KO}


/**
 * Wrapper around operation results containing data|error and status
 *
 * @param T: returned data type by performed operation
 *
 * Created by bernatgomez on August,2020
 */
data class ResourceResult<T>(
    val status : Status,
    val data : T? = null,
    val error : ErrorData? = null
) {
    fun isOK() = this.isTarget(Status.OK)

    fun isKO() = this.isTarget(Status.KO)

    fun isLoading() = this.isTarget(Status.LOADING)

    private fun isTarget(target : Status) = (this.status == target)

    fun hasData() = (this.data != null)

    fun hasError() = (this.error != null)
}