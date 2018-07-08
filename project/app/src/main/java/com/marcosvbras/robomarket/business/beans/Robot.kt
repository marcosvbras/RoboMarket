package com.marcosvbras.robomarket.business.beans

import android.os.Parcel
import android.os.Parcelable

class Robot : Parcelable {

    var objectId: String? = null
    var updatedAt: String? = null
    var createdAt: String? = null
    var model: String? = null
    var color: String? = null
    var year: Int? = null
    var price: Int? = null
    var manufacturer: String? = null
    var quantity: Int? = null
    var imageUrl: String? = null
    var userId: String? = null

    constructor()

    constructor(parcel: Parcel) {
        objectId = parcel.readString()
        updatedAt = parcel.readString()
        createdAt = parcel.readString()
        model = parcel.readString()
        color = parcel.readString()
        year = parcel.readInt()
        price = parcel.readInt()
        manufacturer = parcel.readString()
        quantity = parcel.readInt()
        imageUrl = parcel.readString()
        userId = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(objectId)
        parcel.writeString(updatedAt)
        parcel.writeString(createdAt)
        parcel.writeString(model)
        parcel.writeString(color)
        parcel.writeInt(year!!)
        parcel.writeInt(price!!)
        parcel.writeString(manufacturer)
        parcel.writeInt(quantity!!)
        parcel.writeString(imageUrl)
        parcel.writeString(userId)
    }

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Robot> {
            override fun createFromParcel(parcel: Parcel): Robot {
                return Robot(parcel)
            }

            override fun newArray(size: Int): Array<Robot?> {
                return arrayOfNulls(size)
            }
        }
    }
}