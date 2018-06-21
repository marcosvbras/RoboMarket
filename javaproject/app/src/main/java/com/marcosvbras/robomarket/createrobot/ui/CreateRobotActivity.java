package com.marcosvbras.robomarket.createrobot.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.createrobot.viewmodel.CreateRobotViewModel;
import com.marcosvbras.robomarket.createrobot.viewmodel.CreateRobotViewModelFactory;
import com.marcosvbras.robomarket.databinding.ActivityCreateRobotBinding;

public class CreateRobotActivity extends BaseActivity {

    private ActivityCreateRobotBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_robot);
        activityBinding.setViewModel(createRobotViewModel());
        activityBinding.executePendingBindings();
    }

    private CreateRobotViewModel createRobotViewModel() {
        return ViewModelProviders.of(this, new CreateRobotViewModelFactory(this))
                .get(CreateRobotViewModel.class);
    }
}
