package com.marcosvbras.robomarket.createrobot.viewmodel;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import io.reactivex.disposables.Disposable;

public class CreateRobotViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;

    public CreateRobotViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        cleanupSubscriptions();
    }

    @Override
    public void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
