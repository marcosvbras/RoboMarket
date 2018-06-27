package com.marcosvbras.robomarket.robotdetail.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.model.RobotsModel;
import com.marcosvbras.robomarket.createrobot.ui.CreateRobotActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

public class RobotDetailViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Robot robot;
    private RobotsModel robotsModel;
    private Disposable disposable;
    public ObservableField<String> model = new ObservableField<>();
    public ObservableField<String> manufacturer = new ObservableField<>();
    public ObservableField<String> color = new ObservableField<>();
    public ObservableField<String> year = new ObservableField<>();
    public ObservableField<String> quantity = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> imageUrl= new ObservableField<>();

    public RobotDetailViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        robotsModel = new RobotsModel();
    }

    public void showData(Robot robot) {
        this.robot = robot;

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
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.Other.ROBOT_TAG, robot);

        activityCallback.openActivityForResult(
                CreateRobotActivity.class, bundle, Constants.Other.EDIT_ROBOT_REQUEST_CODE
        );
    }

    @SuppressLint("CheckResult")
    public void delete() {
        cleanupSubscriptions();

        if(robot != null) {
            robotsModel.deleteRobot(robot.getObjectId())
                    .subscribe(next -> {

                    }, error -> {
                        isLoading.set(false);
                        activityCallback.showDialogMessage(error.getMessage());
                    }, () -> {
                        isLoading.set(false);
                        activityCallback.setActivityResult(RESULT_OK);
                        activityCallback.finishCurrentActivity();
                    }, d -> {
                        isLoading.set(true);
                        disposable = d;
                    });
        }
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
