package com.marcosvbras.robomarket.flows.dialog;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class SaleDialogViewModel extends BaseViewModel {

    public final ObservableField<String> itemQuantity = new ObservableField<>("0");
    public final ObservableField<String> totalValue = new ObservableField<>("Total: $ 0");
    public final ObservableField<String> model = new ObservableField<>();
    public final ErrorObservable error = new ErrorObservable();
    private ItemRobotQuantity itemRobotQuantity;
    private DialogFormActions dialogFormActions;
    private DialogActions dialogActions;

    public SaleDialogViewModel(ItemRobotQuantity itemRobotQuantity, DialogFormActions dialogFormActions) {
        this.itemRobotQuantity = itemRobotQuantity;
        this.dialogFormActions = dialogFormActions;
        prepareData();
    }

    public void setActions(DialogActions dialogActions) {
        this.dialogActions = dialogActions;
    }

    private void prepareData() {
        if(itemRobotQuantity != null) {
            model.set(itemRobotQuantity.getRobot().getModel());
            itemQuantity.addOnPropertyChangedCallback(onQuantityChanged());

            if(itemRobotQuantity.getItemQuantity() != 0) {
                itemQuantity.set(String.valueOf(itemRobotQuantity.getItemQuantity()));
            }
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
            itemQuantity.set("1");

        int quantityInt = Integer.parseInt(itemQuantity.get());
        itemQuantity.set(String.valueOf(quantityInt + 1));
        updateTotalValue();
    }

    public void decreaseQuantity() {
        if(TextUtils.isEmpty(itemQuantity.get()) || itemQuantity.get().equals("0")) {
            itemQuantity.set("1");
            return;
        }

        int quantityInt = Integer.parseInt(itemQuantity.get());

        if(quantityInt > 0)
            quantityInt--;

        itemQuantity.set(String.valueOf(quantityInt));
        updateTotalValue();
    }

    public void removeItem() {
        dialogFormActions.onRemove(itemRobotQuantity);
        finish();
    }

    public void saveItem() {
        if(checkStock()) {
            itemRobotQuantity.setItemQuantity(
                    TextUtils.isEmpty(itemQuantity.get()) ? 0 : Integer.valueOf(itemQuantity.get())
            );
            dialogFormActions.onSave(itemRobotQuantity);
            finish();
        }
    }

    private void finish() {
        if(dialogActions != null)
            dialogActions.onFinished();
    }

    private void updateTotalValue() {
        if(TextUtils.isEmpty(itemQuantity.get())) {
            totalValue.set("Total: $ 1");
            return;
        }

        int value = Integer.parseInt(itemQuantity.get()) * itemRobotQuantity.getRobot().getPrice();
        totalValue.set("Total: $ " + String.valueOf(value));
        checkStock();
    }

    private boolean checkStock() {
        int quantityInt = TextUtils.isEmpty(itemQuantity.get()) ? 0 : Integer.parseInt(itemQuantity.get());

        if(quantityInt > itemRobotQuantity.getRobot().getQuantity()) {
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
