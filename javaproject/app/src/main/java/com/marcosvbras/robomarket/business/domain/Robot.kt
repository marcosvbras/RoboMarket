package com.marcosvbras.robomarket.business.domain

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

    constructor(`in`: Parcel) {
        objectId = `in`.readString()
        updatedAt = `in`.readString()
        createdAt = `in`.readString()
        model = `in`.readString()
        color = `in`.readString()
        year = `in`.readInt()
        price = `in`.readInt()
        manufacturer = `in`.readString()
        quantity = `in`.readInt()
        imageUrl = `in`.readString()
        userId = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(objectId)
        dest.writeString(updatedAt)
        dest.writeString(createdAt)
        dest.writeString(model)
        dest.writeString(color)
        dest.writeInt(year!!)
        dest.writeInt(price!!)
        dest.writeString(manufacturer)
        dest.writeInt(quantity!!)
        dest.writeString(imageUrl)
        dest.writeString(userId)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Robot> = object : Parcelable.Creator<Robot> {
            override fun createFromParcel(`in`: Parcel): Robot {
                return Robot(`in`)
            }

            override fun newArray(size: Int): Array<Robot?> {
                return arrayOfNulls(size)
            }
        }
    }
}