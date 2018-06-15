package com.marcosvbras.robomarket.viewmodels;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.RoboApplication;
import com.marcosvbras.robomarket.model.api.APIService;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.views.activities.HomeActivity;
import com.marcosvbras.robomarket.views.activities.RegisterActivity;
import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ErrorObservable usernameFieldError = new ErrorObservable();
    public ErrorObservable passwordFieldError = new ErrorObservable();

    public LoginViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @SuppressLint("CheckResult")
    public void signin() {
        if(isFormValid()) {
            if(disposable != null && !disposable.isDisposed())
                disposable.dispose();

            APIService.getService().login(username.get(), password.get())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d -> {
                        isLoading.set(true);
                        disposable = d;
                    })
                    .subscribe(next -> {
                        RoboApplication.getInstance().setUser(next);
                        RoboApplication.getInstance().writeUserCredentials(next.getObjectId(), next.getSessionToken());
                    }, error -> {
                        isLoading.set(false);
                        activityCallback.showErrorDialog(error.getMessage());
                    }, () -> {
                        isLoading.set(false);
                        disposable.dispose();
                        disposable = null;
                        activityCallback.openActivity(HomeActivity.class, true);
                    });
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
