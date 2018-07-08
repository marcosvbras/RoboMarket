package com.marcosvbras.robomarket.flows.profile.viewmodel

import android.annotation.SuppressLint
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
import com.marcosvbras.robomarket.interfaces.ActivityCallback
import com.marcosvbras.robomarket.utils.ErrorObservable
import com.marcosvbras.robomarket.utils.MaskWatcher
import com.marcosvbras.robomarket.app.BaseViewModel
import io.reactivex.disposables.Disposable
import java.util.*

class EditProfileViewModel(private val callback: ActivityCallback) : BaseViewModel() {

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
        val (objectId, name1, email1, username1, password1, emailVerified, sessionToken, avatarUrl1,
                phone1, genre1, address1) = App.instance.user!!
        user = App.instance.user?.copy(objectId = objectId, name = name1, email = email1,
                username = username1, password = password1, emailVerified = emailVerified,
                sessionToken = sessionToken, avatarUrl = avatarUrl1, phone = phone1, genre = genre1,
                address = address1)
        email.set(user?.email)
        username.set(user?.username)
        name.set(user?.name)
        phone.set(user?.phone)
        address.set(user?.address)
        password.set(user?.password)
        genre.set(user?.genre)
        avatarUrl.set(user?.avatarUrl)
    }

    @SuppressLint("CheckResult")
    fun save() {
        if (isInfoFormValid) {
            val newUserData = User()
            newUserData.avatarUrl = avatarUrl.get()
            newUserData.address = address.get()

            if (user?.email.equals(email.get()))
                newUserData.email = email.get()

            newUserData.genre = genre.get()
            newUserData.name = name.get()
            newUserData.phone = phone.get()

            if (user?.username.equals(username.get()))
                newUserData.username = username.get()

            userModel.updateUser(newUserData, user?.objectId!!)
                    ?.subscribe({ next -> App.instance.user = next }, { error ->
                        isLoading.set(false)
                        callback.showDialogMessage(error.message!!)
                        cleanupSubscriptions()
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