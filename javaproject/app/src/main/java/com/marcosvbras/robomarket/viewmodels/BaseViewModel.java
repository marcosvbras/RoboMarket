package com.marcosvbras.robomarket.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class BaseViewModel {

    private ObservableField<String> toolbarTitle = new ObservableField<>();
    private ObservableBoolean isLoading = new ObservableBoolean(false);

    public ObservableField<String> getToolbarTitle() {
        return toolbarTitle;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }
}
