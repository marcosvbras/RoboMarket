package com.marcosvbras.robomarket.home.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityHomeBinding;
import com.marcosvbras.robomarket.home.HomeActivityCallbacks;
import com.marcosvbras.robomarket.home.viewmodel.HomeViewModel;
import com.marcosvbras.robomarket.home.viewmodel.HomeViewModelFactory;

public class HomeActivity extends BaseActivity implements HomeActivityCallbacks {

    private ActivityHomeBinding activityHomeBinding;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setViewModel(createViewModel());
        activityHomeBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, false);
        fragmentManager = getSupportFragmentManager();
    }

    private HomeViewModel createViewModel() {
        return ViewModelProviders.of(this, new HomeViewModelFactory(this)).get(HomeViewModel.class);
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag, Fragment activeFragment) {
        Fragment frag = fragmentManager.findFragmentByTag(tag);

        if(frag == null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.conteinerFrag, fragment, tag)
                    .commit();
        }

        if(fragment != activeFragment) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .hide(activeFragment)
                    .show(fragment)
                    .commit();
        }
    }
}
