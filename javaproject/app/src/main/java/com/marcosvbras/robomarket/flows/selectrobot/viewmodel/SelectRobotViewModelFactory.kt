package com.marcosvbras.robomarket.flows.selectrobot.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback

class SelectRobotViewModelFactory(private val baseActivityCallback: BaseActivityCallback) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectRobotViewModel(baseActivityCallback) as T
    }

}