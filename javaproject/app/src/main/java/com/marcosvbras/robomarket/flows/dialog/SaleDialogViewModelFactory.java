package com.marcosvbras.robomarket.flows.dialog;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity;

import javax.inject.Inject;

public class SaleDialogViewModelFactory implements ViewModelProvider.Factory {

    private final DialogFormActions dialogFormActions;
    private final ItemRobotQuantity itemRobotQuantity;

    @Inject
    public SaleDialogViewModelFactory(DialogFormActions dialogFormActions, ItemRobotQuantity itemRobotQuantity) {
        this.dialogFormActions = dialogFormActions;
        this.itemRobotQuantity = itemRobotQuantity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SaleDialogViewModel(itemRobotQuantity, dialogFormActions);
    }
}
