package com.marcosvbras.robomarket;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class SaleDialogViewModelFactory implements ViewModelProvider.Factory {

    private final DialogActions dialogActions;
    private final RobotSale robotSale;

    @Inject
    public SaleDialogViewModelFactory(DialogActions dialogActions, RobotSale robotSale) {
        this.dialogActions = dialogActions;
        this.robotSale = robotSale;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SaleDialogViewModel(robotSale, dialogActions);
    }
}
