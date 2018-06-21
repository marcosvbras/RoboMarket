package com.marcosvbras.robomarket.home.viewmodel;

import android.databinding.ObservableField;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.interfaces.BaseFragmentCallback;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

public class ProfileViewModel extends BaseViewModel {

    private BaseFragmentCallback fragmentCallback;
//    public ObservableField<User> user = new ObservableField<>(App.getInstance().getUser());

    public ProfileViewModel(BaseFragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
