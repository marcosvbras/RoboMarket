package com.marcosvbras.robomarket.viewmodels;

import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

public class ProfileViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public ProfileViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

}
