package com.marcosvbras.robomarket.flows.register.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityRegisterBinding;
import com.marcosvbras.robomarket.flows.register.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegisterViewModel registerViewModel = new RegisterViewModel(this);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityBinding.setViewModel(registerViewModel);
        activityBinding.executePendingBindings();
    }
}
