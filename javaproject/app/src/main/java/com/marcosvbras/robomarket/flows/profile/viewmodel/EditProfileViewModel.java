package com.marcosvbras.robomarket.flows.profile.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.business.model.UserModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import java.util.Random;

import io.reactivex.disposables.Disposable;

public class EditProfileViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    private User user;
    private UserModel userModel;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> address = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> newPassword = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> genre = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();
    public ErrorObservable emailFieldError = new ErrorObservable();
    public ErrorObservable newPasswordFieldError = new ErrorObservable();
    public ErrorObservable confirmPasswordFieldError = new ErrorObservable();
    public ErrorObservable usernameFieldError = new ErrorObservable();
    public ErrorObservable nameFieldError = new ErrorObservable();

    public EditProfileViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        setUserData();
        userModel = new UserModel();
    }

    private void setUserData() {
        user = (User) App.getInstance().getUser().clone();
        email.set(user.getEmail());
        username.set(user.getUsername());
        name.set(user.getName());
        phone.set(user.getPhone());
        address.set(user.getAddress());
        password.set(user.getPassword());
        genre.set(user.getGenre());
        avatarUrl.set(user.getAvatarUrl());
    }

    @SuppressLint("CheckResult")
    public void save() {
        if(isInfoFormValid()) {
            User newUserData = new User();
            newUserData.setAvatarUrl(avatarUrl.get());
            newUserData.setAddress(address.get());
            if(!user.getEmail().equals(email.get()))
                newUserData.setEmail(email.get());
            newUserData.setGenre(genre.get());
            newUserData.setName(name.get());
            newUserData.setPhone(phone.get());
            if(!user.getUsername().equals(username.get()))
                newUserData.setUsername(username.get());

            userModel.updateUser(newUserData, user.getObjectId())
                    .subscribe(next -> App.getInstance().setUser(next), error -> {
                        isLoading.set(false);
                        cleanupSubscriptions();
                        activityCallback.showDialogMessage(error.getMessage());
                    }, () -> {
                        isLoading.set(false);
                        cleanupSubscriptions();
                        activityCallback.finishCurrentActivity();
                    }, d -> {
                        isLoading.set(true);
                        disposable = d;
                    });
        }
    }

    public boolean isInfoFormValid() {
        if(TextUtils.isEmpty(name.get())) {
            nameFieldError.setErrorResource(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(email.get())) {
            emailFieldError.setErrorResource(R.string.required_field);
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
            emailFieldError.setErrorResource(R.string.invalid_email_format);
            return false;
        }

        if(TextUtils.isEmpty(username.get())) {
            usernameFieldError.setErrorResource(R.string.required_field);
            return false;
        }

        return true;
    }

    public void onGenreSelected(AdapterView<?> parent, View view, int position, long id) {
        genre.set((String)parent.getSelectedItem());
    }

    public void changeAvatarImg() {
        avatarUrl.set(Constants.Other.ROBOHASH_API + new Random().nextInt(500) + Constants.Other.ROBOHASH_SET_2_PARAM);
    }

    @Override
    protected void onCleared() {
        cleanupSubscriptions();
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
