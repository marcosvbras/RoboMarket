package com.marcosvbras.robomarket.flows.intro.viewmodel

import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.model.UserModel
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity
import com.marcosvbras.robomarket.interfaces.ActivityCallback
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable

class SplashViewModel(private val callback: ActivityCallback) : BaseViewModel() {

    private var disposable: Disposable? = null
    private val userModel: UserModel = UserModel()

    fun authenticate() {
        cleanupSubscriptions()

        userModel.getAuthenticatedUser()!!
                .subscribe({ next -> App.getInstance().user = next }, {
                    cleanupSubscriptions()
                    callback.openActivityWithAnimation(
                            LoginActivity::class.java, null,true, R.anim.zoom_enter, R.anim.zoom_exit
                    )
                }, {
                    cleanupSubscriptions()
                    callback.openActivityWithAnimation(
                            HomeActivity::class.java, null,true, R.anim.zoom_enter, R.anim.zoom_exit
                    )
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