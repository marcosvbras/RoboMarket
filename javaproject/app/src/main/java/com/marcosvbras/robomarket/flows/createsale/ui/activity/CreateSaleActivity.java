package com.marcosvbras.robomarket.flows.createsale.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityCreateSaleBinding;
import com.marcosvbras.robomarket.flows.createsale.viewmodel.CreateSaleViewModel;
import com.marcosvbras.robomarket.flows.createsale.viewmodel.CreateSaleViewModelFactory;
import com.marcosvbras.robomarket.utils.Constants;

public class CreateSaleActivity extends BaseActivity {

    private ActivityCreateSaleBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_sale);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, true);
    }

    private CreateSaleViewModel createViewModel() {
        return ViewModelProviders.of(this, new CreateSaleViewModelFactory(this)).get(CreateSaleViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.Other.SELECT_ROBOT_REQUEST_CODE && resultCode == RESULT_OK)
            activityBinding.getViewModel().addRobot(data.getExtras().getParcelable(Constants.Other.ROBOT_TAG));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}
