package com.marcosvbras.robomarket.flows.login.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.interfaces.ActivityCallback

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val activityCallback: ActivityCallback) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(activityCallback) as T
    }

}