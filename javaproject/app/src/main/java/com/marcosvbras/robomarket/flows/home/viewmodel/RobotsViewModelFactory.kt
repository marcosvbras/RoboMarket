package com.marcosvbras.robomarket.flows.home.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.interfaces.ActivityCallback

class RobotsViewModelFactory(private val activityCallback: HomeActivityCallback) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RobotsViewModel(activityCallback) as T
    }

}