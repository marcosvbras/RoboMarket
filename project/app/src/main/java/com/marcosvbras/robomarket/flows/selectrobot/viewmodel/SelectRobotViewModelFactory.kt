package com.marcosvbras.robomarket.flows.selectrobot.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.interfaces.ActivityCallback

@Suppress("UNCHECKED_CAST")
class SelectRobotViewModelFactory(private val activityCallback: ActivityCallback) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectRobotViewModel(activityCallback) as T
    }

}