package com.marcosvbras.robomarket.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityHomeBinding;
import com.marcosvbras.robomarket.viewmodels.HomeViewModel;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeViewModel homeViewModel = new HomeViewModel(this);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setViewModel(homeViewModel);
        activityHomeBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, false);
    }
}
