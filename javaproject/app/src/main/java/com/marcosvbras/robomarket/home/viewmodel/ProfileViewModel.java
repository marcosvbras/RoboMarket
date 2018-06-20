package com.marcosvbras.robomarket.home.viewmodel;

import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

public class ProfileViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public ProfileViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
