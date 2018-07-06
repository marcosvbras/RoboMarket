package com.marcosvbras.robomarket.flows.home.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.app.BaseFragment;
import com.marcosvbras.robomarket.databinding.FragmentRobotsBinding;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.flows.createrobot.ui.CreateRobotActivity;
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback;
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.flows.home.viewmodel.RobotsViewModel;
import com.marcosvbras.robomarket.flows.home.viewmodel.RobotsViewModelFactory;
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity;
import com.marcosvbras.robomarket.interfaces.ActivityCallback;
import com.marcosvbras.robomarket.utils.Constants;

import org.jetbrains.annotations.NotNull;

import static android.app.Activity.RESULT_OK;

public class RobotsFragment extends BaseFragment {

    private FragmentRobotsBinding fragmentBinding;
    private SearchView searchView;
    private HomeActivityCallback activityCallback;

    public static RobotsFragment newInstance(@NonNull HomeActivityCallback activityCallback) {
        RobotsFragment robotsFragment = new RobotsFragment();
        robotsFragment.activityCallback = activityCallback;
        return robotsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_robots, container, false);
        fragmentBinding.setViewModel(createViewModel());
        setHasOptionsMenu(true);

        return fragmentBinding.getRoot();
    }

    private RobotsViewModel createViewModel() {
        return ViewModelProviders.of(this, new RobotsViewModelFactory(activityCallback))
                .get(RobotsViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem menuItemSearch = menu.findItem(R.id.menu_search);
        searchView = (SearchView)menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearchListener());
        searchView.setOnCloseListener(() -> {
            fragmentBinding.getViewModel().listRobots(null);
            return false;
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private SearchView.OnQueryTextListener onSearchListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fragmentBinding.getViewModel().listRobots(newText);
                return true;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_add:
                startActivityForResult(
                        new Intent(getActivity().getBaseContext(), CreateRobotActivity.class),
                        Constants.Other.NEW_ROBOT_REQUEST_CODE
                );
                break;
            case R.id.menu_logout:
                App.getInstance().deleteCredentials();
                activityCallback.openActivity(LoginActivity.class, null, true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.Other.NEW_ROBOT_REQUEST_CODE && resultCode == RESULT_OK)
            fragmentBinding.getViewModel().listRobots(null);
    }

}
