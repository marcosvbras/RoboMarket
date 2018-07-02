package com.marcosvbras.robomarket.flows.createsale.viewmodel;

import android.databinding.ObservableField;

import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.domain.RobotSale;
import com.marcosvbras.robomarket.flows.createsale.ui.adapter.RobotSaleAdapter;
import com.marcosvbras.robomarket.flows.dialog.DialogActions;
import com.marcosvbras.robomarket.flows.selectrobot.ui.SelectRobotActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CreateSaleViewModel extends BaseViewModel implements OnRecyclerClick, DialogActions {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    private List<RobotSale> listSale;
    private Robot robot;
    public final RobotSaleAdapter robotSaleAdapter;
    public ObservableField<String> quantity = new ObservableField<>();
    public ObservableField<String> totalPrice = new ObservableField<>();

    public CreateSaleViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        this.robotSaleAdapter = new RobotSaleAdapter(this);
        this.listSale = new ArrayList<>();
    }

    public void select() {
        activityCallback.openActivityForResult(
                SelectRobotActivity.class, null, Constants.Other.SELECT_ROBOT_REQUEST_CODE
        );
    }

    public void addRobot(Robot robot) {
        if(robot != null) {
            boolean isAlreadySelected = false;

            for (RobotSale robotSale : listSale) {
                if(robotSale.getRobot().getObjectId().equals(robot.getObjectId())) {
                    isAlreadySelected = true;
                    break;
                }
            }

            if(!isAlreadySelected) {
                listSale.add(new RobotSale(robot, 0));
//                robotSaleAdapter.updateItems(listSale);
            }
        }
    }

    public void save() {

    }

    @Override
    public void onClick(Object obj) {
//        SaleDialogFragment saleDialogFragment = SaleDialogFragment.newInstance(new RobotSale(), this);
//        activityCallback.showCustomAlertDialog(saleDialogFragment);
    }

    @Override
    public void onSave(@NotNull RobotSale robotSale) {

    }

    @Override
    public void onRemove(@NotNull RobotSale robotSale) {

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
