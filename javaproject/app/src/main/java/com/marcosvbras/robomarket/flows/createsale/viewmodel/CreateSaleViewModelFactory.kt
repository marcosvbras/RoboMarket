package com.marcosvbras.robomarket.flows.createsale.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.interfaces.ActivityCallback

class CreateSaleViewModelFactory(private val activityCallback: ActivityCallback) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateSaleViewModel(activityCallback) as T
    }
}