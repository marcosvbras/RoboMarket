package com.marcosvbras.robomarket.flows.login.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityLoginBinding;
import com.marcosvbras.robomarket.flows.login.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginViewModel loginViewModel = new LoginViewModel(this);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityBinding.setViewModel(loginViewModel);
        activityBinding.executePendingBindings();
    }
}
