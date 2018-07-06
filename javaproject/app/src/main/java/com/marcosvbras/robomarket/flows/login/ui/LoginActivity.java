package com.marcosvbras.robomarket.flows.login.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.Activity;
import com.marcosvbras.robomarket.databinding.ActivityLoginBinding;
import com.marcosvbras.robomarket.flows.login.viewmodel.LoginViewModel;
import com.marcosvbras.robomarket.flows.login.viewmodel.LoginViewModelFactory;

public class LoginActivity extends Activity {

    private ActivityLoginBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
    }

    private LoginViewModel createViewModel() {
        return ViewModelProviders.of(this, new LoginViewModelFactory(this))
                .get(LoginViewModel.class);
    }
}
