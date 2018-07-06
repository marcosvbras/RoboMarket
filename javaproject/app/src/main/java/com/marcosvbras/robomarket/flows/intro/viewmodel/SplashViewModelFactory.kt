package com.marcosvbras.robomarket.flows.intro.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.interfaces.ActivityCallback

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory(private val activityCallback: ActivityCallback) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(activityCallback) as T
    }

}