package com.marcosvbras.robomarket.createrobot.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import javax.inject.Inject;

public class CreateRobotViewModelFactory implements ViewModelProvider.Factory {

    private final BaseActivityCallback activityCallback;

    @Inject
    public CreateRobotViewModelFactory(@NonNull BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CreateRobotViewModel(activityCallback);
    }
}
