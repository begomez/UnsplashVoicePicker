package com.bgomez.picker.common.models


import android.os.Parcel
import android.os.Parcelable


/**
 * Data model for pics
 *
 * Created by bernatgomez on July,2020
 */
data class PicData(
    val id : String,
    val urls : UrlData = UrlData(),
    val user : UserData = UserData()
) : Parcelable {

    constructor(parcel: Parcel) : this(
        id= parcel.readString() ?: "",
        urls= parcel.readParcelable(UrlData::class.java.classLoader) ?: UrlData(),
        user= parcel.readParcelable(UserData::class.java.classLoader) ?: UserData()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.id)
        parcel.writeParcelable(this.urls, flags)
        parcel.writeParcelable(this.user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PicData> {
        override fun createFromParcel(parcel: Parcel): PicData {
            return PicData(parcel)
        }

        override fun newArray(size: Int): Array<PicData?> {
            return arrayOfNulls(size)
        }
    }
}