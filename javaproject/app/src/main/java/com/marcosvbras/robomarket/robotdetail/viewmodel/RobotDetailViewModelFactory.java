package com.marcosvbras.robomarket.robotdetail.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import javax.inject.Inject;

public class RobotDetailViewModelFactory implements ViewModelProvider.Factory {
    private final BaseActivityCallback activityCallback;

    @Inject
    public RobotDetailViewModelFactory(@NonNull BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RobotDetailViewModel(activityCallback);
    }
}
