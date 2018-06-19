package com.marcosvbras.robomarket.register.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityRegisterBinding;
import com.marcosvbras.robomarket.register.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegisterViewModel registerViewModel = new RegisterViewModel(this);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityRegisterBinding.setViewModel(registerViewModel);
        activityRegisterBinding.executePendingBindings();
    }
}
