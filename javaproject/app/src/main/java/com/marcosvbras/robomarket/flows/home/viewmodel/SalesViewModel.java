package com.marcosvbras.robomarket.flows.home.viewmodel;

import com.marcosvbras.robomarket.business.model.RobotsModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import io.reactivex.disposables.Disposable;

public class SalesViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private RobotsModel robotsModel;
    private Disposable disposable;

    public SalesViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        this.robotsModel = new RobotsModel();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
