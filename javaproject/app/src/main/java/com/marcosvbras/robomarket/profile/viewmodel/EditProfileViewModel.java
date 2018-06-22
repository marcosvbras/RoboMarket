package com.marcosvbras.robomarket.profile.viewmodel;

import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import java.util.Random;

import io.reactivex.disposables.Disposable;

public class EditProfileViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> birth = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> newPassword = new ObservableField<>();
    public ObservableField<String> oldPassword = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> genre = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();
    public ErrorObservable emailFieldError = new ErrorObservable();
    public ErrorObservable newPasswordFieldError = new ErrorObservable();
    public ErrorObservable oldPasswordFieldError = new ErrorObservable();
    public ErrorObservable confirmPasswordFieldError = new ErrorObservable();
    public ErrorObservable usernameFieldError = new ErrorObservable();
    public ErrorObservable nameFieldError = new ErrorObservable();

    public EditProfileViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        setUserData();
    }

    private void setUserData() {
        User user = (User) App.getInstance().getUser().clone();
        email.set(user.getEmail());
        username.set(user.getUsername());
        name.set(user.getName());
        phone.set(user.getPhone());
        birth.set(user.getBirth());
        password.set(user.getPassword());
        genre.set(user.getGenre());
        avatarUrl.set(user.getAvatarUrl());
    }

    public void save() {
        if(isInfoFormValid()) {

        }
    }

    public boolean isInfoFormValid() {
        return false;
    }

    public boolean isPasswordFormValid() {
        return false;
    }

    public void changePassword() {
        if(isPasswordFormValid()) {

        }
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
