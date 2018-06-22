package com.marcosvbras.robomarket.home.viewmodel;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.profile.ui.EditProfileActivity;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class ProfileViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public ProfileViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    public void edit() {
        activityCallback.openActivity(EditProfileActivity.class, false);
    }

    @Override
    protected void onCleared() {
        cleanupSubscriptions();
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
