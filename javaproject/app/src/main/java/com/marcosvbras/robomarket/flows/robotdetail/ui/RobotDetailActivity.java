package com.marcosvbras.robomarket.flows.robotdetail.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.databinding.ActivityRobotDetailBinding;
import com.marcosvbras.robomarket.flows.robotdetail.viewmodel.RobotDetailViewModel;
import com.marcosvbras.robomarket.flows.robotdetail.viewmodel.RobotDetailViewModelFactory;
import com.marcosvbras.robomarket.utils.Constants;

public class RobotDetailActivity extends BaseActivity {

    private ActivityRobotDetailBinding activityBinding;
    private Robot robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robot = getIntent().getExtras().getParcelable(Constants.Other.ROBOT_TAG);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_robot_detail);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, true);
    }

    private RobotDetailViewModel createViewModel() {
        return ViewModelProviders.of(this, new RobotDetailViewModelFactory(this))
                .get(RobotDetailViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityBinding.getViewModel().showData(robot);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.Other.EDIT_ROBOT_REQUEST_CODE && resultCode == RESULT_OK)
            robot = data.getExtras().getParcelable(Constants.Other.ROBOT_TAG);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
