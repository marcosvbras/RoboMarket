package com.marcosvbras.robomarket.createsale.viewmodel;

import android.databinding.ObservableField;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class CreateSaleViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    public ObservableField<String> quantity = new ObservableField<>();
    public ObservableField<String> totalPrice = new ObservableField<>();

    public CreateSaleViewModel(BaseActivityCallback activityCallback) {
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
