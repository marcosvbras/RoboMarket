package com.marcosvbras.robomarket.login.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityLoginBinding;
import com.marcosvbras.robomarket.login.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginViewModel loginViewModel = new LoginViewModel(this);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.executePendingBindings();
    }
}
