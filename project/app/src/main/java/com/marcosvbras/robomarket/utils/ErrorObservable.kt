package com.marcosvbras.robomarket.utils

import android.databinding.BaseObservable

class ErrorObservable() : BaseObservable() {

    constructor(errorString: String?) : this() {
        this.errorString = errorString
        notifyChange()
    }

    constructor(errorResource: Int?) : this() {
        this.errorString = errorString
        notifyChange()
    }

    var errorString: String? = null
        set(value) {
            field = value
            notifyChange()
        }

    var errorResource: Int? = null
        set(value) {
            field = value
            notifyChange()
        }

    fun clear() {
        this.errorString = null
        this.errorResource = null
        notifyChange()
    }

    fun hasError() : Boolean = this.errorResource != null || this.errorString != null

}