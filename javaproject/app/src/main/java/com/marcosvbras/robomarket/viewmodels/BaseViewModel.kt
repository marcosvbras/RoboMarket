package com.marcosvbras.robomarket.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback

abstract class BaseViewModel : ViewModel() {

    val toolbarTitle = ObservableField<String>()
    val isLoading = ObservableBoolean(false)
    val activityCallback: BaseActivityCallback? = null

    abstract fun cleanupSubscriptions()

}