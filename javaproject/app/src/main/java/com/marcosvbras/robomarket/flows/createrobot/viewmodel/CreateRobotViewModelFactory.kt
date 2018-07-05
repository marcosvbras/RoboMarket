package com.marcosvbras.robomarket.flows.createrobot.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback

@Suppress("UNCHECKED_CAST")
class CreateRobotViewModelFactory : ViewModelProvider.NewInstanceFactory {

    private val activityCallback: BaseActivityCallback
    private val robot: Robot?

    constructor(activityCallback: BaseActivityCallback, robot: Robot?) : super() {
        this.activityCallback = activityCallback
        this.robot = robot
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateRobotViewModel(activityCallback, robot) as T
    }

}
