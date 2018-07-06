package com.marcosvbras.robomarket.flows.intro.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.Activity;
import com.marcosvbras.robomarket.databinding.ActivitySplashBinding;
import com.marcosvbras.robomarket.flows.intro.viewmodel.SplashViewModel;
import com.marcosvbras.robomarket.flows.intro.viewmodel.SplashViewModelFactory;

public class SplashActivity extends Activity {

    private ActivitySplashBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
    }

    private SplashViewModel createViewModel() {
        return ViewModelProviders.of(this, new SplashViewModelFactory(this)).get(SplashViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityBinding.getViewModel().authenticate();
    }
}
