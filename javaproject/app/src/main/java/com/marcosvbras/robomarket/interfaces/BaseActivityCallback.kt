package com.marcosvbras.robomarket.interfaces

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment

interface BaseActivityCallback {

    fun showDialogMessage(message: String)

    fun showDialogMessage(message: Int)

    fun openActivity(activity: Class<*>, finishCurrentActivity: Boolean)

    fun openActivity(activity: Class<*>, bundle: Bundle?, finishCurrentActivity: Boolean)

    fun openActivityForResult(activity: Class<*>, bundle: Bundle?, requestCode: Int)

    fun setToolbar(viewId: Int, displayHomeAsUpEnabled: Boolean)

    fun finishCurrentActivity()

    fun setActivityResult(resultCode: Int)

    fun setActivityResult(resultCode: Int, intent: Intent)

    fun setActivityResult(resultCode: Int, bundle: Bundle)

    fun showCustomAlertDialog(dialogFragment: DialogFragment)

}