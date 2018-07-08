package com.marcosvbras.robomarket.app

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.marcosvbras.robomarket.interfaces.ActivityCallback

abstract class BaseViewModel : ViewModel() {

    val toolbarTitle = ObservableField<String>()
    val isLoading = ObservableBoolean(false)

    abstract fun cleanupSubscriptions()

}