package com.marcosvbras.robomarket.flows.home.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val activityCallback: HomeActivityCallback) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(activityCallback) as T
    }

}