package com.marcosvbras.robomarket.flows.createsale.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.ItemRobotSale;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.domain.ItemRobotQuantity;
import com.marcosvbras.robomarket.business.domain.RobotSale;
import com.marcosvbras.robomarket.business.domain.Sale;
import com.marcosvbras.robomarket.business.model.RobotModel;
import com.marcosvbras.robomarket.business.model.SaleModel;
import com.marcosvbras.robomarket.flows.createsale.ui.adapter.RobotSaleAdapter;
import com.marcosvbras.robomarket.flows.dialog.DialogFormActions;
import com.marcosvbras.robomarket.flows.dialog.SaleDialogFragment;
import com.marcosvbras.robomarket.flows.selectrobot.ui.SelectRobotActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateSaleViewModel extends BaseViewModel implements OnRecyclerClick, DialogFormActions {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    private List<ItemRobotQuantity> listRobotQuantity;
    private SaleModel saleModel;
    private RobotModel robotModel;
    public final RobotSaleAdapter robotSaleAdapter;
    public ObservableField<String> totalPrice = new ObservableField<>();

    public CreateSaleViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        this.robotSaleAdapter = new RobotSaleAdapter(this);
        this.listRobotQuantity = new ArrayList<>();
        saleModel = new SaleModel();
        robotModel = new RobotModel();
    }

    public void select() {
        activityCallback.openActivityForResult(
                SelectRobotActivity.class, null, Constants.Other.SELECT_ROBOT_REQUEST_CODE
        );
    }

    public void addRobot(Robot robot) {
        if(robot != null) {
            boolean isAlreadySelected = false;

            for (ItemRobotQuantity itemRobotQuantity : listRobotQuantity) {
                if(itemRobotQuantity.getRobot().getObjectId().equals(robot.getObjectId())) {
                    isAlreadySelected = true;
                    break;
                }
            }

            if(!isAlreadySelected) {
                listRobotQuantity.add(new ItemRobotQuantity(robot, 1));
                robotSaleAdapter.updateItems(listRobotQuantity);
            }
        }

        updateFinalValue();
    }

    @SuppressLint("CheckResult")
    public void save() {
        if(listRobotQuantity.size() > 0) {
            Sale sale = new Sale();
            sale.setUserId(App.getInstance().getUser().getObjectId());
            List<ItemRobotSale> items = new ArrayList<>();

            for(ItemRobotQuantity itemRobotQuantity : listRobotQuantity) {
                items.add(
                        new ItemRobotSale(
                                itemRobotQuantity.getItemQuantity(),
                                itemRobotQuantity.getRobot().getPrice(),
                                itemRobotQuantity.getRobot().getObjectId()
                        )
                );
            }

            sale.setRobots(new RobotSale(items));
            saleModel.createSale(sale).subscribe(
                    next -> {

                    }, error -> {
                        isLoading.set(false);
                        activityCallback.showDialogMessage(error.getMessage());
                        cleanupSubscriptions();
                    }, () -> {
                        isLoading.set(false);
                        cleanupSubscriptions();
                        decreaseStock();
                    }, d -> {
                        isLoading.set(true);
                        disposable = d;
                    });

        } else {
            activityCallback.showDialogMessage(R.string.select_robots_to_continue);
        }
    }

    @SuppressLint("CheckResult")
    private void decreaseStock() {
        Observable.fromArray(listRobotQuantity.toArray())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> {
                    Robot robot = new Robot();
                    robot.setQuantity(
                            ((ItemRobotQuantity)next).getRobot().getQuantity() -
                                    ((ItemRobotQuantity)next).getItemQuantity());

                    robotModel.updateRobot(((ItemRobotQuantity)next).getRobot().getObjectId(), robot)
                            .subscribe(n -> {}, e -> {}, () -> {});
                }, error -> {

                }, () -> activityCallback.finishCurrentActivity(), d -> {

                });
    }

    @Override
    public void onClick(Object obj) {
        SaleDialogFragment saleDialogFragment = SaleDialogFragment.newInstance((ItemRobotQuantity) obj, this);
        activityCallback.showCustomAlertDialog(saleDialogFragment);
    }

    @Override
    public void onSave(@NotNull ItemRobotQuantity itemRobotQuantity) {
        for (ItemRobotQuantity rs : listRobotQuantity) {
            if(rs.getRobot().getObjectId().equals(itemRobotQuantity.getRobot().getObjectId())) {
                rs.setItemQuantity(itemRobotQuantity.getItemQuantity());
                break;
            }
        }

        updateFinalValue();
    }

    @Override
    public void onRemove(@NotNull ItemRobotQuantity itemRobotQuantity) {
        for (ItemRobotQuantity rs : listRobotQuantity) {
            if(rs.getRobot().getObjectId().equals(itemRobotQuantity.getRobot().getObjectId())) {
                listRobotQuantity.remove(rs);
                break;
            }
        }

        robotSaleAdapter.updateItems(listRobotQuantity);
        updateFinalValue();
    }

    private void updateFinalValue() {
        int total = 0;

        for(ItemRobotQuantity itemRobotQuantity : listRobotQuantity)
            total += itemRobotQuantity.getRobot().getPrice() * itemRobotQuantity.getItemQuantity();


        totalPrice.set("$ " + total);
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
