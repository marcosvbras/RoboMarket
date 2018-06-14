package com.marcosvbras.robomarket.viewmodels;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.views.activities.RegisterActivity;
import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

public class LoginViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private ObservableField<String> username = new ObservableField<>();
    private ObservableField<String> password = new ObservableField<>();
    private ErrorObservable usernameFieldError = new ErrorObservable();
    private ErrorObservable passwordFieldError = new ErrorObservable();

    public LoginViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    public ObservableField<String> getUsername() {
        return username;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public ErrorObservable getUsernameFieldError() {
        return usernameFieldError;
    }

    public ErrorObservable getPasswordFieldError() {
        return passwordFieldError;
    }

    public void signin() {
        if(isFormValid()) {
//            activityCallback.openActivity();
        }
    }

    public void register() {
        activityCallback.openActivity(RegisterActivity.class, false);
    }

    private boolean isFormValid() {
        if(TextUtils.isEmpty(username.get())) {
            usernameFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(password.get())) {
            passwordFieldError.set(R.string.required_field);
            return false;
        }

        return true;
    }



}
