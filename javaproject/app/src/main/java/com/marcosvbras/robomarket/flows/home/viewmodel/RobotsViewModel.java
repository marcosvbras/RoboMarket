package com.marcosvbras.robomarket.flows.home.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.os.Bundle;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.model.RobotModel;
import com.marcosvbras.robomarket.flows.home.ui.adapter.RobotsAdapter;
import com.marcosvbras.robomarket.flows.robotdetail.ui.RobotDetailActivity;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class RobotsViewModel extends BaseViewModel implements OnRecyclerClick {

    private BaseActivityCallback activityCallback;
    private RobotModel robotModel;
    private Disposable disposable;
    private int skip = 0;
    private List<Robot> listRobots = new ArrayList<>();
    public final RobotsAdapter robotAdapter;
    public ObservableBoolean isListEmpty = new ObservableBoolean(false);

    public RobotsViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        this.robotAdapter = new RobotsAdapter(this);
        this.robotModel = new RobotModel();
        listRobots(null);
    }

    @SuppressLint("CheckResult")
    public void listRobots(String query) {
        cleanupSubscriptions();

        robotModel.listRobots(App.getInstance().getUser(), query, skip)
                .subscribe(next -> {
//                    listRobots.addAll(next.getListRobots());
                    listRobots = next.getListRobots();
                    robotAdapter.updateItems(listRobots);
                    isListEmpty.set(listRobots.size() == 0);
                }, error -> {
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
        super.onCleared();
        cleanupSubscriptions();
    }

    @Override
    public void cleanupSubscriptions() {
        isLoading.set(false);

        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void onClick(Object obj) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.Other.ROBOT_TAG, (Robot)obj);
        activityCallback.openActivity(RobotDetailActivity.class, bundle, false);
    }
}
