package com.marcosvbras.robomarket;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class SaleDialogViewModel extends BaseViewModel {

    private BaseActivityCallback baseActivityCallback;

//    public SaleDialogViewModel(BaseActivityCallback baseActivityCallback) {
//        this.baseActivityCallback = baseActivityCallback;
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
