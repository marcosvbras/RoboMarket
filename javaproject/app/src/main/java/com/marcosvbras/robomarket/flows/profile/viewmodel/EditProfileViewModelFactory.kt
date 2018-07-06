package com.marcosvbras.robomarket.flows.profile.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcosvbras.robomarket.interfaces.ActivityCallback

@Suppress("UNCHECKED_CAST")
class EditProfileViewModelFactory(private val activityCallback: ActivityCallback) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditProfileViewModel(activityCallback) as T
    }

}