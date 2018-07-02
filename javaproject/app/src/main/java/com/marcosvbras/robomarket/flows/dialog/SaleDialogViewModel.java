package com.marcosvbras.robomarket.flows.dialog;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.RobotSale;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class SaleDialogViewModel extends BaseViewModel {

    public final ObservableField<String> itemQuantity = new ObservableField<>("0");
    public final ObservableField<String> totalValue = new ObservableField<>("Total: $ 0");
    public final ObservableField<String> model = new ObservableField<>();
    public final ErrorObservable error = new ErrorObservable();
    private RobotSale robotSale;
    private DialogActions dialogActions;

    public SaleDialogViewModel(RobotSale robotSale, DialogActions dialogActions) {
        this.robotSale = robotSale;
        this.dialogActions = dialogActions;
        prepareData();
    }

    private void prepareData() {
        if(robotSale != null) {
            model.set(robotSale.getRobot().getModel());
            itemQuantity.addOnPropertyChangedCallback(onQuantityChanged());

            if(robotSale.getItemQuantity() != 0)
                itemQuantity.set("Total: $ " + robotSale.getItemQuantity());
        }
    }

    private Observable.OnPropertyChangedCallback onQuantityChanged() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                updateTotalValue();
            }
        };
    }

    public void increaseQuantity() {
        if(TextUtils.isEmpty(itemQuantity.get()))
            itemQuantity.set("0");

        int quantityInt = Integer.parseInt(itemQuantity.get());
        itemQuantity.set(String.valueOf(quantityInt + 1));
        updateTotalValue();
    }

    public void decreaseQuantity() {
        if(TextUtils.isEmpty(itemQuantity.get())) {
            itemQuantity.set("0");
            return;
        }

        int quantityInt = Integer.parseInt(itemQuantity.get());

        if(quantityInt > 0)
            quantityInt--;

        itemQuantity.set(String.valueOf(quantityInt));
        updateTotalValue();
    }

    public void removeItem() {
        dialogActions.onRemove(robotSale);
    }

    public void saveItem() {
        if(checkStock()) {
            robotSale.setItemQuantity(
                    TextUtils.isEmpty(itemQuantity.get()) ? 0 : Integer.valueOf(itemQuantity.get())
            );
            dialogActions.onSave(robotSale);
        }
    }

    private void updateTotalValue() {
        if(TextUtils.isEmpty(itemQuantity.get())) {
            totalValue.set("Total: $ 0");
            return;
        }

        int value = Integer.parseInt(itemQuantity.get()) * robotSale.getRobot().getPrice();
        totalValue.set("Total: $ " + String.valueOf(value));
        checkStock();
    }

    private boolean checkStock() {
        int quantityInt = TextUtils.isEmpty(itemQuantity.get()) ? 0 : Integer.parseInt(itemQuantity.get());

        if(quantityInt > robotSale.getRobot().getQuantity()) {
            error.setErrorResource(R.string.error_stock_not_enough);
            return false;
        }

        return true;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        cleanupSubscriptions();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
