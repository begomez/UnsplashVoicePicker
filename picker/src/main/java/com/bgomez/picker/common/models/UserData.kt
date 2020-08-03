package com.bgomez.picker.common.models


import android.os.Parcel
import android.os.Parcelable


/**
 * Data model for unsplash users
 *
 * Created by bernatgomez on August,2020
 */
data class UserData(val id : String = "", val username : String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
        id=parcel.readString() ?: "",
        username= parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.id)
        parcel.writeString(this.username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }

}