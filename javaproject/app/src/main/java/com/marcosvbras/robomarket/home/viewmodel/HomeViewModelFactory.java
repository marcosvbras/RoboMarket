package com.marcosvbras.robomarket.home.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.home.HomeActivityCallbacks;

import javax.inject.Inject;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final HomeActivityCallbacks activityCallback;

    @Inject
    public HomeViewModelFactory(@NonNull HomeActivityCallbacks activityCallback) {
        this.activityCallback = activityCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(activityCallback);
    }

}
