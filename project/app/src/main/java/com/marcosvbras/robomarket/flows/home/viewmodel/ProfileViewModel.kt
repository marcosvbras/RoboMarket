package com.marcosvbras.robomarket.flows.home.viewmodel

import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.model.UserModel
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity
import com.marcosvbras.robomarket.flows.profile.ui.EditProfileActivity
import com.marcosvbras.robomarket.app.BaseViewModel
import io.reactivex.disposables.Disposable

class ProfileViewModel(private val callback: HomeActivityCallback) : BaseViewModel() {

    private val userModel: UserModel = UserModel()
    private var disposable: Disposable? = null

    fun edit() {
        callback.openActivity(EditProfileActivity::class.java, null,false)
    }

    fun requestPasswordReset() {
        cleanupSubscriptions()

        userModel.resetPassword(App.instance.user!!)
                ?.subscribe({

                }, { error ->
                    callback.showDialogMessage(error.message!!)
                    cleanupSubscriptions()
                }, {
                    cleanupSubscriptions()
                    callback.showSnackBar(R.string.sucessful_reset_password)
                }, { d ->
                    isLoading.set(true)
                    disposable = d
                })
    }

    fun deleteAccount() {
        cleanupSubscriptions()

        userModel.deleteUser(App.instance.user?.objectId!!)
                ?.subscribe({}, { error ->
                    callback.showDialogMessage(error.message!!)
                    cleanupSubscriptions()
                }, {
                    cleanupSubscriptions()
                    callback.openActivity(LoginActivity::class.java, null,true)
                }, { d ->
                    isLoading.set(true)
                    disposable = d
                })
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }

    override fun cleanupSubscriptions() {
        isLoading.set(false)
        disposable?.dispose()
    }
}