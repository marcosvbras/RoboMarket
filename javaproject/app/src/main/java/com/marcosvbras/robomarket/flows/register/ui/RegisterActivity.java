package com.marcosvbras.robomarket.flows.register.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.Activity;
import com.marcosvbras.robomarket.databinding.ActivityRegisterBinding;
import com.marcosvbras.robomarket.flows.register.viewmodel.RegisterViewModel;
import com.marcosvbras.robomarket.flows.register.viewmodel.RegisterViewModelFactory;

public class RegisterActivity extends Activity {

    private ActivityRegisterBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, true);
    }

    private RegisterViewModel createViewModel() {
        return ViewModelProviders.of(this, new RegisterViewModelFactory(this))
                .get(RegisterViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
