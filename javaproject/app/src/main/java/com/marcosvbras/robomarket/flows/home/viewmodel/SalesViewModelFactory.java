package com.marcosvbras.robomarket.flows.home.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import javax.inject.Inject;

public class SalesViewModelFactory implements ViewModelProvider.Factory {
    private final BaseActivityCallback activityCallback;

    @Inject
    public SalesViewModelFactory(@NonNull BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SalesViewModel(activityCallback);
    }
}
