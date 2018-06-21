package com.marcosvbras.robomarket.home.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivityHomeBinding;
import com.marcosvbras.robomarket.home.HomeActivityCallbacks;
import com.marcosvbras.robomarket.home.viewmodel.HomeViewModel;
import com.marcosvbras.robomarket.home.viewmodel.HomeViewModelFactory;

public class HomeActivity extends BaseActivity implements HomeActivityCallbacks {

    private ActivityHomeBinding activityHomeBinding;
    private FragmentManager fragmentManager;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setViewModel(createViewModel());
        activityHomeBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, false);
    }

    private HomeViewModel createViewModel() {
        return ViewModelProviders.of(this, new HomeViewModelFactory(this)).get(HomeViewModel.class);
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        Fragment frag = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(frag == null)
            transaction.add(R.id.conteinerFrag, fragment, tag);

        if(activeFragment != null && activeFragment != fragment) {
            transaction.hide(activeFragment);
            transaction.show(fragment);
        }

        activeFragment = fragment;
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

}
