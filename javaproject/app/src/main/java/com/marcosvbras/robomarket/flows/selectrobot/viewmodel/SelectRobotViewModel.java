package com.marcosvbras.robomarket.flows.selectrobot.viewmodel;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.model.RobotsModel;
import com.marcosvbras.robomarket.flows.home.ui.adapter.RobotsAdapter;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.interfaces.OnRecyclerClick;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

public class SelectRobotViewModel extends BaseViewModel implements OnRecyclerClick {

    private BaseActivityCallback activityCallback;
    private RobotsModel robotsModel;
    private Disposable disposable;
    private int skip = 0;
    private List<Robot> listRobots = new ArrayList<>();
    public final RobotsAdapter robotAdapter;

    public SelectRobotViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        this.robotAdapter = new RobotsAdapter(this);
        this.robotsModel = new RobotsModel();
        listRobots(null);
    }

    @SuppressLint("CheckResult")
    public void listRobots(String query) {
        cleanupSubscriptions();

        robotsModel.listRobots(App.getInstance().getUser(), query, skip)
                .subscribe(next -> {
                    listRobots = next.getListRobots();
                    robotAdapter.updateItems(listRobots);
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
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void onClick(Object object) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.Other.ROBOT_TAG, (Robot)object);
        activityCallback.setActivityResult(RESULT_OK, bundle);
        activityCallback.finishCurrentActivity();
    }
}
