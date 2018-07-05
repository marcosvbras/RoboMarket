package com.marcosvbras.robomarket.flows.register.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityRegisterBinding;
import com.marcosvbras.robomarket.flows.register.viewmodel.RegisterViewModel;
import com.marcosvbras.robomarket.flows.register.viewmodel.RegisterViewModelFactory;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
    }

    private RegisterViewModel createViewModel() {
        return ViewModelProviders.of(this, new RegisterViewModelFactory(this))
                .get(RegisterViewModel.class);
    }
}
