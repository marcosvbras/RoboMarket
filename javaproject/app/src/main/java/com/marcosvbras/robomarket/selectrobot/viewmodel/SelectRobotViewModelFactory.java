package com.marcosvbras.robomarket.selectrobot.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.selectrobot.viewmodel.SelectRobotViewModel;

import javax.inject.Inject;

public class SelectRobotViewModelFactory implements ViewModelProvider.Factory {

    private final BaseActivityCallback baseActivityCallback;

    @Inject
    public SelectRobotViewModelFactory(BaseActivityCallback baseActivityCallback) {
        this.baseActivityCallback = baseActivityCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SelectRobotViewModel(baseActivityCallback);
    }
}
