package com.marcosvbras.robomarket.home.viewmodel;

import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

public class RobotViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public RobotViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

}
