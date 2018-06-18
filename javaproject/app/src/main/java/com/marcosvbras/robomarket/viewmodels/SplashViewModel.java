package com.marcosvbras.robomarket.viewmodels;

import android.annotation.SuppressLint;

import com.marcosvbras.robomarket.app.RoboApplication;
import com.marcosvbras.robomarket.model.api.APIService;
import com.marcosvbras.robomarket.views.activities.HomeActivity;
import com.marcosvbras.robomarket.views.activities.LoginActivity;
import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;

    public SplashViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @SuppressLint("CheckResult")
    public void authenticate() {
        APIService.getService().getUserByToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> {
                    isLoading.set(true);
                    disposable = d;
                })
                .subscribe(next -> RoboApplication.getInstance().setUser(next), error -> {
                    isLoading.set(false);
                    disposable.dispose();
                    disposable = null;
                    activityCallback.openActivity(LoginActivity.class, true);
                }, () -> {
                    isLoading.set(false);
                    disposable.dispose();
                    disposable = null;
                    activityCallback.openActivity(HomeActivity.class, true);
                });
    }
}
