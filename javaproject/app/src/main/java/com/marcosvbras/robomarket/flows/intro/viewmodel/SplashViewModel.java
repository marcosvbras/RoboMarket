package com.marcosvbras.robomarket.flows.intro.viewmodel;

import android.annotation.SuppressLint;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.model.UserModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity;

import io.reactivex.disposables.Disposable;

public class SplashViewModel extends BaseViewModel {

    private Disposable disposable;
    private UserModel userModel;
    private BaseActivityCallback activityCallback;

    SplashViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        userModel = new UserModel();
        authenticate();
    }

    @SuppressLint("CheckResult")
    private void authenticate() {
        cleanupSubscriptions();

        userModel.getAuthenticatedUser()
                .subscribe(next -> App.getInstance().setUser(next), error -> {
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

    @Override
    public void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
