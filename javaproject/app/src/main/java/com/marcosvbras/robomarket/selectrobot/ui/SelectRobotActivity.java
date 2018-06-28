package com.marcosvbras.robomarket.selectrobot.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.BaseActivity;
import com.marcosvbras.robomarket.databinding.ActivitySelectRobotBinding;
import com.marcosvbras.robomarket.selectrobot.viewmodel.SelectRobotViewModel;
import com.marcosvbras.robomarket.selectrobot.viewmodel.SelectRobotViewModelFactory;

public class SelectRobotActivity extends BaseActivity {

    private ActivitySelectRobotBinding activityBinding;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_robot);
        activityBinding.setViewModel(createViewModel());
        activityBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, true);
    }

    private SelectRobotViewModel createViewModel() {
        return ViewModelProviders.of(this, new SelectRobotViewModelFactory(this))
                .get(SelectRobotViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        menu.findItem(R.id.menu_add).setVisible(false);
        menu.findItem(R.id.menu_logout).setVisible(false);
        MenuItem menuItemSearch = menu.findItem(R.id.menu_search);
        searchView = (SearchView)menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearchListener());
        searchView.setOnCloseListener(() -> {
            activityBinding.getViewModel().listRobots(null);
            return false;
        });

        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener onSearchListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                activityBinding.getViewModel().listRobots(newText);
                return true;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
