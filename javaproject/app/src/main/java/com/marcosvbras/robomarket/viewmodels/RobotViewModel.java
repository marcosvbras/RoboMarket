package com.marcosvbras.robomarket.viewmodels;

import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

public class RobotViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public RobotViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

}
