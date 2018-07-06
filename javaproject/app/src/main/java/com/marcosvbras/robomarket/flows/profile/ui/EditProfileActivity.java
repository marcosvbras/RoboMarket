package com.marcosvbras.robomarket.flows.profile.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.Activity;
import com.marcosvbras.robomarket.databinding.ActivityEditProfileBinding;
import com.marcosvbras.robomarket.flows.profile.viewmodel.EditProfileViewModel;
import com.marcosvbras.robomarket.flows.profile.viewmodel.EditProfileViewModelFactory;

public class EditProfileActivity extends Activity {

    private ActivityEditProfileBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, true);
    }

    private EditProfileViewModel createViewModel() {
        return ViewModelProviders.of(this, new EditProfileViewModelFactory(this))
                .get(EditProfileViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
