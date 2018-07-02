package com.marcosvbras.robomarket.flows.createrobot.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import javax.inject.Inject;

public class CreateRobotViewModelFactory implements ViewModelProvider.Factory {

    private final BaseActivityCallback activityCallback;
    private final Robot robot;

    @Inject
    public CreateRobotViewModelFactory(@NonNull BaseActivityCallback activityCallback, Robot robot) {
        this.activityCallback = activityCallback;
        this.robot = robot;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CreateRobotViewModel(activityCallback, robot);
    }
}
