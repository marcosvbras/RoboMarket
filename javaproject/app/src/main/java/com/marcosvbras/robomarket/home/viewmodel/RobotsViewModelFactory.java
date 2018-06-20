package com.marcosvbras.robomarket.home.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.interfaces.BaseFragmentCallback;

import javax.inject.Inject;

public class RobotsViewModelFactory implements ViewModelProvider.Factory {

    private final BaseFragmentCallback fragmentCallback;

    @Inject
    public RobotsViewModelFactory(@NonNull BaseFragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RobotsViewModel(fragmentCallback);
    }
}
