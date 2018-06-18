package com.marcosvbras.robomarket.viewmodels;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

public class HomeViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;

    public HomeViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.item_sales:
                break;
            case R.id.item_robots:
                break;
            case R.id.item_profile:
                break;
        }

        return true;
    }
}
