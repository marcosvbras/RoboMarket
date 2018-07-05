package com.marcosvbras.robomarket.flows.register.viewmodel

import android.databinding.ObservableField
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import com.marcosvbras.robomarket.R
import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.domain.User
import com.marcosvbras.robomarket.business.model.UserModel
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.utils.Constants
import com.marcosvbras.robomarket.utils.ErrorObservable
import com.marcosvbras.robomarket.utils.MaskWatcher
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable
import java.util.*

class RegisterViewModel(private val callback: BaseActivityCallback) : BaseViewModel() {

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
    val phoneNumberWatcher: TextWatcher = MaskWatcher.build9DigitPhone()

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

            val newUser = User()
            newUser.address = address.get()
            newUser.avatarUrl = avatarUrl.get()
            newUser.email = email.get()
            newUser.genre = genre.get()
            newUser.address = address.get()
            newUser.name = name.get()
            newUser.password = password.get()
            newUser.phone = phone.get()
            newUser.username = username.get()

            userModel.signUp(newUser)!!
                    .subscribe({ next -> App.getInstance().user = next }, { error ->
                        cleanupSubscriptions()
                        callback.showDialogMessage(error.message!!)
                    }, {
                        cleanupSubscriptions()
                        callback.openActivity(HomeActivity::class.java, true)
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
        avatarUrl.set(Constants.Other.ROBOHASH_API + Random().nextInt(500) + Constants.Other.ROBOHASH_SET_2_PARAM)
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