package com.marcosvbras.robomarket.flows.intro.viewmodel

import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.model.UserModel
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable

class SplashViewModel(private val callback: BaseActivityCallback) : BaseViewModel() {

    private var disposable: Disposable? = null
    private val userModel: UserModel = UserModel()

    init {
        authenticate()
    }

    private fun authenticate() {
        cleanupSubscriptions()

        userModel.getAuthenticatedUser()!!
                .subscribe({ next -> App.getInstance().user = next }, { error ->
                    cleanupSubscriptions()
                    callback.openActivity(LoginActivity::class.java, true)
                }, {
                    cleanupSubscriptions()
                    callback.openActivity(HomeActivity::class.java, true)
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