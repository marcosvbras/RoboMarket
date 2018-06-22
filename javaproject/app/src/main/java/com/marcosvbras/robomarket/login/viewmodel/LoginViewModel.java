package com.marcosvbras.robomarket.login.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.model.UserModel;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.register.ui.RegisterActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import io.reactivex.disposables.Disposable;

public class LoginViewModel extends BaseViewModel {

    private Disposable disposable;
    private UserModel userModel;
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ErrorObservable usernameFieldError = new ErrorObservable();
    public ErrorObservable passwordFieldError = new ErrorObservable();

    public LoginViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        userModel = new UserModel();
    }

    @SuppressLint("CheckResult")
    public void signIn() {
        if(isFormValid()) {
            cleanupSubscriptions();

            userModel.login(username.get(), password.get())
                    .subscribe(next -> {
                        App.getInstance().setUser(next);
                        App.getInstance().writeUserCredentials(next.getObjectId(), next.getSessionToken());
                    }, error -> {
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

    public void register() {
//        activityCallback.openActivityForResult(RegisterActivity.class, null, Constants.Other.FINISH_LOGIN_ACTIVITY);
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

    @Override
    public void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        cleanupSubscriptions();
    }
}
