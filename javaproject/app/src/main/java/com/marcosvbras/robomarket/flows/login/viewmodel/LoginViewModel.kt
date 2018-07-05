package com.marcosvbras.robomarket.flows.login.viewmodel

import android.databinding.ObservableField
import android.text.TextUtils
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.model.UserModel
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity
import com.marcosvbras.robomarket.flows.register.ui.RegisterActivity
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.utils.ErrorObservable
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable

class LoginViewModel(private val callback: BaseActivityCallback) : BaseViewModel() {

    private var disposable: Disposable? = null
    private val userModel: UserModel = UserModel()
    var username = ObservableField<String>()
    var password = ObservableField<String>()
    var usernameFieldError = ErrorObservable()
    var passwordFieldError = ErrorObservable()

    private val isFormValid: Boolean
        get() {
            if (TextUtils.isEmpty(username.get())) {
                usernameFieldError.errorResource = R.string.required_field
                return false
            }

            if (TextUtils.isEmpty(password.get())) {
                passwordFieldError.errorResource = R.string.required_field
                return false
            }

            return true
        }

    fun signIn() {
        if (isFormValid) {
            cleanupSubscriptions()

            userModel.login(username.get()!!, password.get()!!)!!
                    .subscribe({ next ->
                        App.getInstance().user = next
                        App.getInstance().writeUserCredentials(next.objectId, next.sessionToken)
                    }, { error ->
                        cleanupSubscriptions()
                        callback.showDialogMessage(error.message!!)
                    }, {
                        cleanupSubscriptions()
                        callback.openActivity(HomeActivity::class.java, null,true)
                    }, { d ->
                        isLoading.set(true)
                        disposable = d
                    })
        }
    }

    fun register() {
        // callback.openActivityForResult(RegisterActivity.class, null, Constants.Other.FINISH_LOGIN_ACTIVITY);
        callback.openActivity(RegisterActivity::class.java, null,false)
    }

    override fun cleanupSubscriptions() {
        isLoading.set(false)
        disposable?.dispose()
    }

    override fun onCleared() {
        cleanupSubscriptions()
        super.onCleared()
    }
}