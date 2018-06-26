package com.marcosvbras.robomarket.createrobot.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.model.RobotsModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import java.util.Random;

import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

public class CreateRobotViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    private RobotsModel robotsModel;
    private Robot savedRobot;
    public ObservableField<String> model = new ObservableField<>();
    public ObservableField<String> color = new ObservableField<>();
    public ObservableField<String> year = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> manufacturer = new ObservableField<>();
    public ObservableField<String> quantity = new ObservableField<>("0");
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ErrorObservable modelFieldError = new ErrorObservable();
    public ErrorObservable manufacturerFieldError = new ErrorObservable();
    public ErrorObservable priceFieldError = new ErrorObservable();

    public CreateRobotViewModel(BaseActivityCallback activityCallback, Robot savedRobot) {
        this.activityCallback = activityCallback;
        this.savedRobot = savedRobot;
        checkAction();
        robotsModel = new RobotsModel();
    }

    private void checkAction() {
        if(savedRobot != null) {
            model.set(savedRobot.getModel());
            color.set(savedRobot.getColor());
            year.set(String.valueOf(savedRobot.getYear()));
            price.set(String.valueOf(savedRobot.getPrice()));
            manufacturer.set(savedRobot.getManufacturer());
            quantity.set(String.valueOf(savedRobot.getQuantity()));
            imageUrl.set(savedRobot.getImageUrl());
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

    @SuppressLint("CheckResult")
    public void save() {
        if(isFormValid()) {
            cleanupSubscriptions();

            Robot robot = new Robot();
            robot.setColor(color.get());
            robot.setImageUrl(imageUrl.get());
            robot.setManufacturer(manufacturer.get());
            robot.setPrice(TextUtils.isEmpty(price.get()) ? 0 : Integer.parseInt(price.get()));
            robot.setQuantity(TextUtils.isEmpty(quantity.get()) ? 0 : Integer.parseInt(quantity.get()));
            robot.setModel(model.get());
            if(!TextUtils.isEmpty(year.get()))
                robot.setYear(Integer.parseInt(year.get()));

            if(savedRobot == null) {
                robot.setUserId(App.getInstance().getUser().getObjectId());
                robotsModel.createRobot(robot)
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
            } else {
                robotsModel.updateRobot(savedRobot.getObjectId(), robot)
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
    }

    public boolean isFormValid() {
        if(TextUtils.isEmpty(model.get())) {
            modelFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(manufacturer.get())) {
            manufacturerFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(price.get())) {
            priceFieldError.set(R.string.required_field);
            return false;
        }

        return true;
    }

    public void changeAvatarImg() {
        imageUrl.set(Constants.Other.ROBOHASH_API + new Random().nextInt(500) + Constants.Other.ROBOHASH_SET_1_PARAM);
    }

    public void increaseQuantity() {
        if(TextUtils.isEmpty(quantity.get())) {
            quantity.set("1");
            return;
        }

        int quantityInt = Integer.parseInt(quantity.get()) + 1;
        quantity.set(String.valueOf(quantityInt));
    }

    public void decreaseQuantity() {
        if(TextUtils.isEmpty(quantity.get())) {
            quantity.set("0");
            return;
        }

        int quantityInt = Integer.parseInt(quantity.get());

        if(quantityInt > 0)
            quantityInt--;

        quantity.set(String.valueOf(quantityInt));
    }
}
