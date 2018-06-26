package com.marcosvbras.robomarket.createrobot.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.createrobot.viewmodel.CreateRobotViewModel;
import com.marcosvbras.robomarket.createrobot.viewmodel.CreateRobotViewModelFactory;
import com.marcosvbras.robomarket.databinding.ActivityCreateRobotBinding;
import com.marcosvbras.robomarket.utils.Constants;

public class CreateRobotActivity extends BaseActivity {

    private ActivityCreateRobotBinding activityBinding;
    private Robot robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robot = getIntent().getParcelableExtra(Constants.Other.ROBOT_TAG);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_robot);
        activityBinding.setViewModel(createRobotViewModel());
        activityBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, true);
    }

    private CreateRobotViewModel createRobotViewModel() {
        return ViewModelProviders.of(this, new CreateRobotViewModelFactory(this, robot))
                .get(CreateRobotViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
