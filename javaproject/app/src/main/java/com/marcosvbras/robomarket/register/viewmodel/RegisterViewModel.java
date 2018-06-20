package com.marcosvbras.robomarket.register.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.model.UserModel;
import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import io.reactivex.disposables.Disposable;

public class RegisterViewModel extends BaseViewModel {

    private Disposable disposable;
    private int currentRobotId = 1;
    private UserModel userModel;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> genre = new ObservableField<>();
    public ObservableField<String> birth = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();
    public ErrorObservable emailFieldError = new ErrorObservable();
    public ErrorObservable passwordFieldError = new ErrorObservable();
    public ErrorObservable confirmPasswordFieldError = new ErrorObservable();
    public ErrorObservable usernameFieldError = new ErrorObservable();
    public ErrorObservable nameFieldError = new ErrorObservable();

    public RegisterViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        userModel = new UserModel();
        avatarUrl.set(Constants.Other.ROBOHASH_API + currentRobotId + Constants.Other.ROBOHASH_SET_2_PARAM);
    }

    @SuppressLint("CheckResult")
    public void signUp() {
        if(isFormValid()) {
            cleanupSubscriptions();
            User newUser = new User();
            newUser.setBirth(birth.get());
            newUser.setAvatarUrl(avatarUrl.get());
            newUser.setEmail(email.get());
            newUser.setGenre(genre.get());
            newUser.setName(name.get());
            newUser.setPassword(password.get());
            newUser.setPhone(phone.get());
            newUser.setUsername(username.get());

            userModel.signUp(newUser)
                    .subscribe(next -> App.getInstance().setUser(next), error -> {
                        isLoading.set(false);
                        cleanupSubscriptions();
                        activityCallback.showErrorDialog(error.getMessage());
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
            nameFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(email.get())) {
            emailFieldError.set(R.string.required_field);
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
            emailFieldError.set(R.string.invalid_email_format);
            return false;
        }

        if(TextUtils.isEmpty(username.get())) {
            usernameFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(password.get())) {
            passwordFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(confirmPassword.get())) {
            confirmPasswordFieldError.set(R.string.required_field);
            return false;
        }

        if(!password.get().equals(confirmPassword.get())) {
            confirmPasswordFieldError.set(R.string.different_passwords);
            return false;
        }

        return true;
    }

    public void onGenreSelected(AdapterView<?> parent, View view, int position, long id) {
        genre.set((String)parent.getSelectedItem());
    }

    public void changeAvatarImg() {
        currentRobotId++;
        avatarUrl.set(Constants.Other.ROBOHASH_API + currentRobotId + Constants.Other.ROBOHASH_SET_2_PARAM);
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
