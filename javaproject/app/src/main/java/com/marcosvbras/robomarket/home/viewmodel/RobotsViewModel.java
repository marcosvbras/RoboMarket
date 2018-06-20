package com.marcosvbras.robomarket.home.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.model.RobotsModel;
import com.marcosvbras.robomarket.home.ui.adapter.RobotsAdapter;
import com.marcosvbras.robomarket.interfaces.BaseFragmentCallback;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class RobotsViewModel extends BaseViewModel implements View.OnClickListener {

    private BaseFragmentCallback fragmentCallback;
    private RobotsModel robotsModel;
    private Disposable disposable;
    public ObservableField<List<Robot>> listRobots = new ObservableField<>(new ArrayList<>());
    public ObservableField<RobotsAdapter> robotAdapter = new ObservableField<>(new RobotsAdapter(this));

    public RobotsViewModel(BaseFragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
        this.robotsModel = new RobotsModel();
        listRobots();
    }

    @SuppressLint("CheckResult")
    public void listRobots() {
        robotsModel.listRobots(App.getInstance().getUser())
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
    public void onClick(View v) {

    }
}
