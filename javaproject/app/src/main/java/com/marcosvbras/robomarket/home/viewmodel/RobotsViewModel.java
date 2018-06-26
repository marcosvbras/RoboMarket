package com.marcosvbras.robomarket.home.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.model.RobotsModel;
import com.marcosvbras.robomarket.createrobot.ui.CreateRobotActivity;
import com.marcosvbras.robomarket.home.OnRecyclerClick;
import com.marcosvbras.robomarket.home.ui.adapter.RobotsAdapter;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.interfaces.BaseFragmentCallback;
import com.marcosvbras.robomarket.login.ui.LoginActivity;
import com.marcosvbras.robomarket.robotdetail.ui.RobotDetailActivity;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class RobotsViewModel extends BaseViewModel implements OnRecyclerClick {

    private BaseActivityCallback activityCallback;
    private RobotsModel robotsModel;
    private Disposable disposable;
    public ObservableField<List<Robot>> listRobots = new ObservableField<>(new ArrayList<>());
    public ObservableField<RobotsAdapter> robotAdapter = new ObservableField<>(new RobotsAdapter(this));
    public ObservableField<Integer> skip = new ObservableField<>(0);

    public RobotsViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        this.robotsModel = new RobotsModel();
        listRobots(null);
    }

    @SuppressLint("CheckResult")
    public void listRobots(String query) {
        cleanupSubscriptions();

        robotsModel.listRobots(App.getInstance().getUser(), query, skip.get())
                .subscribe(next -> listRobots.set(next.getListRobots()), error -> {
                    isLoading.set(false);
                    cleanupSubscriptions();
                }, () -> {
                    isLoading.set(false);
                    cleanupSubscriptions();
                }, d -> {
                    isLoading.set(true);
                    disposable = d;
                });

    }

    @Override
    protected void onCleared() {
        Log.d(Constants.Other.TAG, "onCleared: ");
        super.onCleared();
        cleanupSubscriptions();
    }

    @Override
    public void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void onClick(Object object) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.Other.ROBOT_TAG, (Robot)object);
        activityCallback.openActivity(RobotDetailActivity.class, bundle, false);
    }
}
