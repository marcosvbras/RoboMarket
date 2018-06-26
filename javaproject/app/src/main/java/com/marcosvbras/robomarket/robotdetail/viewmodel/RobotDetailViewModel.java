package com.marcosvbras.robomarket.robotdetail.viewmodel;

import android.databinding.ObservableField;

import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.createrobot.ui.CreateRobotActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class RobotDetailViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    public ObservableField<String> model = new ObservableField<>();
    public ObservableField<String> manufacturer = new ObservableField<>();
    public ObservableField<String> color = new ObservableField<>();
    public ObservableField<String> year = new ObservableField<>();
    public ObservableField<String> quantity = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> imageUrl= new ObservableField<>();

    public RobotDetailViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    public void showData(Robot robot) {
        if(robot != null) {
            model.set(robot.getModel());
            manufacturer.set(robot.getManufacturer());
            color.set(robot.getColor());
            year.set(String.valueOf(robot.getYear()));
            quantity.set(String.valueOf(robot.getQuantity()));
            price.set(String.valueOf(robot.getPrice()));
            imageUrl.set(String.valueOf(robot.getImageUrl()));
        }
    }

    public void edit() {
        activityCallback.openActivityForResult(
                CreateRobotActivity.class, null, Constants.Other.EDIT_ROBOT_REQUEST_CODE
        );
    }

    public void delete() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
