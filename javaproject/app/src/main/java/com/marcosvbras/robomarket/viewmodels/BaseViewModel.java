package com.marcosvbras.robomarket.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

public class BaseViewModel extends ViewModel {

    public ObservableField<String> toolbarTitle = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public BaseActivityCallback activityCallback;

}
