package com.bgomez.picker.common.models


import android.os.Parcel
import android.os.Parcelable


/**
 * Data model for URLs
 *
 * Created by bernatgomez on July,2020
 */
data class UrlData(
    val raw : String = "",
    val regular : String = "",
    val small : String = "",
    val thumb : String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
        raw= parcel.readString() ?: "",
        regular= parcel.readString() ?: "",
        small= parcel.readString() ?: "",
        thumb= parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.raw)
        parcel.writeString(this.regular)
        parcel.writeString(this.small)
        parcel.writeString(this.thumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UrlData> {
        override fun createFromParcel(parcel: Parcel): UrlData {
            return UrlData(parcel)
        }

        override fun newArray(size: Int): Array<UrlData?> {
            return arrayOfNulls(size)
        }
    }
}