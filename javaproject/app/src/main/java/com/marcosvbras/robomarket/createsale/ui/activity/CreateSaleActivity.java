package com.marcosvbras.robomarket.createsale.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.createsale.viewmodel.CreateSaleViewModel;
import com.marcosvbras.robomarket.createsale.viewmodel.CreateSaleViewModelFactory;
import com.marcosvbras.robomarket.databinding.ActivityCreateSaleBinding;

public class CreateSaleActivity extends BaseActivity {

    private ActivityCreateSaleBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_sale);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
    }

    private CreateSaleViewModel createViewModel() {
        return ViewModelProviders.of(this, new CreateSaleViewModelFactory(this))
                .get(CreateSaleViewModel.class);
    }
}
