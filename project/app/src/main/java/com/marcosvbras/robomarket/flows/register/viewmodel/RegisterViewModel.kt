package com.marcosvbras.robomarket.flows.register.viewmodel

import android.databinding.ObservableField
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.app.ROBOHASH_API
import com.marcosvbras.robomarket.app.ROBOHASH_SET_2_PARAM
import com.marcosvbras.robomarket.business.beans.User
import com.marcosvbras.robomarket.business.model.UserModel
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity
import com.marcosvbras.robomarket.interfaces.ActivityCallback
import com.marcosvbras.robomarket.utils.ErrorObservable
import com.marcosvbras.robomarket.utils.MaskWatcher
import com.marcosvbras.robomarket.app.BaseViewModel
import io.reactivex.disposables.Disposable
import java.util.*

class RegisterViewModel(private val callback: ActivityCallback) : BaseViewModel() {

    private var disposable: Disposable? = null
    private val userModel: UserModel = UserModel()
    var email = ObservableField<String>()
    var username = ObservableField<String>()
    var password = ObservableField<String>()
    var confirmPassword = ObservableField<String>()
    var name = ObservableField<String>()
    var phone = ObservableField<String>()
    var genre = ObservableField<String>()
    var address = ObservableField<String>()
    var avatarUrl = ObservableField<String>()
    var emailFieldError = ErrorObservable()
    var passwordFieldError = ErrorObservable()
    var confirmPasswordFieldError = ErrorObservable()
    var usernameFieldError = ErrorObservable()
    var nameFieldError = ErrorObservable()
    val phoneNumberWatcher = MaskWatcher.nineDigitPhone

    private val isFormValid: Boolean
        get() {
            if (TextUtils.isEmpty(name.get())) {
                nameFieldError.errorResource = R.string.required_field
                return false
            }

            if (TextUtils.isEmpty(email.get())) {
                emailFieldError.errorResource = R.string.required_field
                return false
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email.get()!!).matches()) {
                emailFieldError.errorResource = R.string.invalid_email_format
                return false
            }

            if (TextUtils.isEmpty(username.get())) {
                usernameFieldError.errorResource = R.string.required_field
                return false
            }

            if (TextUtils.isEmpty(password.get())) {
                passwordFieldError.errorResource = R.string.required_field
                return false
            }

            if (TextUtils.isEmpty(confirmPassword.get())) {
                confirmPasswordFieldError.errorResource = R.string.required_field
                return false
            }

            if (password.get() != confirmPassword.get()) {
                confirmPasswordFieldError.errorResource = R.string.different_passwords
                return false
            }

            return true
        }

    init {
        changeAvatarImg()
    }

    fun signUp() {
        if (isFormValid) {
            cleanupSubscriptions()

            val newUser = User(
                    address = address.get(), avatarUrl = avatarUrl.get(), email = email.get(),
                    genre = genre.get(), name = name.get(), password = password.get(),
                    phone = phone.get(), username = username.get()
            )

            userModel.signUp(newUser)!!
                    .subscribe({ next -> App.instance.user = next }, { error ->
                        callback.showDialogMessage(error.message!!)
                        cleanupSubscriptions()
                    }, {
                        cleanupSubscriptions()
                        callback.openActivity(HomeActivity::class.java, null,true)
                    }, { d ->
                        isLoading.set(true)
                        disposable = d
                    })
        }
    }

    fun signIn() {
        callback.finishCurrentActivity()
    }

    fun onGenreSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        genre.set(parent.selectedItem as String)
    }

    fun changeAvatarImg() {
        avatarUrl.set(ROBOHASH_API + Random().nextInt(500) + ROBOHASH_SET_2_PARAM)
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