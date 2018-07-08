package com.marcosvbras.robomarket.app

import android.app.Activity
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View

fun Any.logd(tag: String = TAG, message: String) {
    Log.d(tag, message)
}

fun Any.logi(tag: String = TAG, message: String) {
    Log.i(tag, message)
}

fun Any.loge(tag: String = TAG, message: String) {
    Log.e(tag, message)
}

fun Any.logv(tag: String = TAG, message: String) {
    Log.v(tag, message)
}

fun Activity.showSnackBar(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(findViewById(android.R.id.content), message, length).show()
}

fun Activity.showSnackBar(message: Int, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(findViewById(android.R.id.content), getString(message), length).show()
}