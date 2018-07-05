package com.marcosvbras.robomarket.flows.profile.viewmodel

import android.annotation.SuppressLint
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
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback
import com.marcosvbras.robomarket.utils.Constants
import com.marcosvbras.robomarket.utils.ErrorObservable
import com.marcosvbras.robomarket.utils.MaskWatcher
import com.marcosvbras.robomarket.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable
import java.util.*

class EditProfileViewModel(private val callback: BaseActivityCallback) : BaseViewModel() {

    private var disposable: Disposable? = null
    private var user: User? = null
    private val userModel: UserModel
    val phoneNumberWatcher = MaskWatcher.nineDigitPhone
    var email = ObservableField<String>()
    var username = ObservableField<String>()
    var name = ObservableField<String>()
    var phone = ObservableField<String>()
    var address = ObservableField<String>()
    var password = ObservableField<String>()
    var genre = ObservableField<String>()
    var avatarUrl = ObservableField<String>()
    var emailFieldError = ErrorObservable()

    var usernameFieldError = ErrorObservable()
    var nameFieldError = ErrorObservable()

    private val isInfoFormValid: Boolean
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

            return true
        }

    init {
        setUserData()
        userModel = UserModel()
    }

    private fun setUserData() {
        val (objectId, name1, email1, username1, password1, emailVerified, sessionToken, avatarUrl1, phone1, genre1, address1) = App.getInstance().user
        user = User()
        user!!.address = address1
        user!!.username = username1
        user!!.phone = phone1
        user!!.name = name1
        user!!.genre = genre1
        user!!.email = email1
        user!!.avatarUrl = avatarUrl1
        user!!.emailVerified = emailVerified
        user!!.objectId = objectId
        user!!.password = password1
        user!!.sessionToken = sessionToken

        email.set(user!!.email)
        username.set(user!!.username)
        name.set(user!!.name)
        phone.set(user!!.phone)
        address.set(user!!.address)
        password.set(user!!.password)
        genre.set(user!!.genre)
        avatarUrl.set(user!!.avatarUrl)
    }

    @SuppressLint("CheckResult")
    fun save() {
        if (isInfoFormValid) {
            val newUserData = User()
            newUserData.avatarUrl = avatarUrl.get()
            newUserData.address = address.get()
            if (user!!.email != email.get())
                newUserData.email = email.get()
            newUserData.genre = genre.get()
            newUserData.name = name.get()
            newUserData.phone = phone.get()
            if (user!!.username != username.get())
                newUserData.username = username.get()

            userModel.updateUser(newUserData, user!!.objectId!!)!!
                    .subscribe({ next -> App.getInstance().user = next }, { error ->
                        isLoading.set(false)
                        cleanupSubscriptions()
                        callback.showDialogMessage(error.message!!)
                    }, {

                        cleanupSubscriptions()
                        callback.finishCurrentActivity()
                    }, { d ->
                        isLoading.set(true)
                        disposable = d
                    })
        }
    }

    fun onGenreSelected(parent: AdapterView<*>, view: View, position: Int, settedStr: String) {
        if(parent.getItemAtPosition(position).toString() != settedStr)
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