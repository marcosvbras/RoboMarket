package com.marcosvbras.robomarket.app

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View

fun Activity.showSnackBar(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(findViewById(android.R.id.content), message, length).show()
}

fun Activity.showSnackBar(message: Int, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(findViewById(android.R.id.content), getString(message), length).show()
}