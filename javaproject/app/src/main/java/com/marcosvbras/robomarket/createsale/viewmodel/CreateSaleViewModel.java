package com.marcosvbras.robomarket.createsale.viewmodel;

import android.databinding.ObservableField;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.selectrobot.ui.SelectRobotActivity;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import io.reactivex.disposables.Disposable;

public class CreateSaleViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    public ObservableField<String> quantity = new ObservableField<>();
    public ObservableField<String> totalPrice = new ObservableField<>();

    public CreateSaleViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    public void add() {
        activityCallback.openActivityForResult(
                SelectRobotActivity.class, null, Constants.Other.SELECT_ROBOT_REQUEST_CODE
        );
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
