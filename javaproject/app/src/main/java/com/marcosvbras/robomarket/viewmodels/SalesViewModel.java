package com.marcosvbras.robomarket.viewmodels;

import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

public class SalesViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public SalesViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }
}
