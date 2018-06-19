package com.marcosvbras.robomarket.home.viewmodel;

import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

public class SalesViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public SalesViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }
}
