package com.marcosvbras.robomarket.createsale.viewmodel;

import android.databinding.ObservableField;

import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.createsale.ui.adapter.RobotSaleAdapter;
import com.marcosvbras.robomarket.home.OnRecyclerClick;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.selectrobot.ui.SelectRobotActivity;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CreateSaleViewModel extends BaseViewModel implements OnRecyclerClick {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    private List<Robot> listRobots;
    public final RobotSaleAdapter robotSaleAdapter;
    public ObservableField<String> quantity = new ObservableField<>();
    public ObservableField<String> totalPrice = new ObservableField<>();

    public CreateSaleViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        this.robotSaleAdapter = new RobotSaleAdapter(this);
        this.listRobots = new ArrayList<>();
    }

    public void select() {
        activityCallback.openActivityForResult(
                SelectRobotActivity.class, null, Constants.Other.SELECT_ROBOT_REQUEST_CODE
        );
    }

    public void addRobot(Robot robot) {
        if(robot != null) {
//            boolean isAlreadySelected = false;
//
//            for (Robot r : listRobots) {
//                if(r.getObjectId().equals(robot.getObjectId())) {
//                    isAlreadySelected = true;
//                    break;
//                }
//            }
//
//            if(!isAlreadySelected) {
//                listRobots.add(robot);
//                robotSaleAdapter.updateItems(listRobots);
//            }
        }
    }

    public void save() {

    }

    @Override
    public void onClick(Object object) {

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
