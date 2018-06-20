package com.marcosvbras.robomarket.home.viewmodel;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.home.HomeActivityCallbacks;
import com.marcosvbras.robomarket.home.ui.fragment.ProfileFragment;
import com.marcosvbras.robomarket.home.ui.fragment.RobotsFragment;
import com.marcosvbras.robomarket.home.ui.fragment.SalesFragment;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    private HomeActivityCallbacks activityCallback;
    private SalesFragment salesFragment;
    private RobotsFragment robotsFragment;
    private ProfileFragment profileFragment;

    public HomeViewModel(HomeActivityCallbacks activityCallback) {
        this.activityCallback = activityCallback;
        this.salesFragment = new SalesFragment();
        this.robotsFragment = new RobotsFragment();
        this.profileFragment = new ProfileFragment();
        this.activityCallback.replaceFragment(salesFragment);
    }

    public boolean onNavigationClick(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.item_sales:
                this.activityCallback.replaceFragment(salesFragment);
                break;
            case R.id.item_robots:
                this.activityCallback.replaceFragment(robotsFragment);
                break;
            case R.id.item_profile:
                this.activityCallback.replaceFragment(profileFragment);
                break;
        }

        return true;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}
