package com.marcosvbras.robomarket.flows.register.viewmodel;

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
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity;

import java.util.Random;

import io.reactivex.disposables.Disposable;

public class RegisterViewModel extends BaseViewModel {

    private Disposable disposable;
    private UserModel userModel;
    private BaseActivityCallback activityCallback;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> genre = new ObservableField<>();
    public ObservableField<String> address = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();
    public ErrorObservable emailFieldError = new ErrorObservable();
    public ErrorObservable passwordFieldError = new ErrorObservable();
    public ErrorObservable confirmPasswordFieldError = new ErrorObservable();
    public ErrorObservable usernameFieldError = new ErrorObservable();
    public ErrorObservable nameFieldError = new ErrorObservable();

    public RegisterViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        userModel = new UserModel();
        changeAvatarImg();
    }

    @SuppressLint("CheckResult")
    public void signUp() {
        if(isFormValid()) {
            cleanupSubscriptions();
            User newUser = new User();
            newUser.setAddress(address.get());
            newUser.setAvatarUrl(avatarUrl.get());
            newUser.setEmail(email.get());
            newUser.setGenre(genre.get());
            newUser.setAddress(address.get());
            newUser.setName(name.get());
            newUser.setPassword(password.get());
            newUser.setPhone(phone.get());
            newUser.setUsername(username.get());

            userModel.signUp(newUser)
                    .subscribe(next -> App.getInstance().setUser(next), error -> {
                        isLoading.set(false);
                        cleanupSubscriptions();
                        activityCallback.showDialogMessage(error.getMessage());
                    }, () -> {
                        isLoading.set(false);
                        cleanupSubscriptions();
                        activityCallback.openActivity(HomeActivity.class, true);
                    }, d -> {
                        isLoading.set(true);
                        disposable = d;
                    });
        }
    }

    public void signIn() {
        activityCallback.finishCurrentActivity();
    }

    private boolean isFormValid() {
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

        if(TextUtils.isEmpty(password.get())) {
            passwordFieldError.setErrorResource(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(confirmPassword.get())) {
            confirmPasswordFieldError.setErrorResource(R.string.required_field);
            return false;
        }

        if(!password.get().equals(confirmPassword.get())) {
            confirmPasswordFieldError.setErrorResource(R.string.different_passwords);
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
        super.onCleared();
        cleanupSubscriptions();
    }

    @Override
    public void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
