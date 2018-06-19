package com.marcosvbras.robomarket.intro.viewmodel;

import android.annotation.SuppressLint;

import com.marcosvbras.robomarket.app.RoboApplication;
import com.marcosvbras.robomarket.business.model.UserModel;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.login.ui.LoginActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import io.reactivex.disposables.Disposable;

public class SplashViewModel extends BaseViewModel {

    private Disposable disposable;
    private UserModel userModel;

    SplashViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        userModel = new UserModel();
        authenticate();
    }

    @SuppressLint("CheckResult")
    private void authenticate() {
        cleanupSubscriptions();

        userModel.getAuthenticatedUser()
                .subscribe(next -> RoboApplication.getInstance().setUser(next), error -> {
                    isLoading.set(false);
                    cleanupSubscriptions();
                    activityCallback.openActivity(LoginActivity.class, true);
                }, () -> {
                    isLoading.set(false);
                    cleanupSubscriptions();
                    activityCallback.openActivity(HomeActivity.class, true);
                }, d -> {
                    isLoading.set(true);
                    disposable = d;
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        cleanupSubscriptions();
    }

    private void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
