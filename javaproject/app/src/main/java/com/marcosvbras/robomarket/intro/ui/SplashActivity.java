package com.marcosvbras.robomarket.intro.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivitySplashBinding;
import com.marcosvbras.robomarket.intro.viewmodel.SplashViewModel;
import com.marcosvbras.robomarket.intro.viewmodel.SplashViewModelFactory;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding activitySplashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        activitySplashBinding.setViewModel(createViewModel());
    }

    private SplashViewModel createViewModel() {
        return ViewModelProviders.of(this, new SplashViewModelFactory(this)).get(SplashViewModel.class);
    }
}
