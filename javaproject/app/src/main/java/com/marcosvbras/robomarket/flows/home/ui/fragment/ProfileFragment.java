package com.marcosvbras.robomarket.flows.home.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marcosvbras.robomarket.app.BaseFragment;
import com.marcosvbras.robomarket.databinding.FragmentProfileBinding;
import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallback;
import com.marcosvbras.robomarket.flows.home.ui.activity.HomeActivity;
import com.marcosvbras.robomarket.flows.home.viewmodel.ProfileViewModel;
import com.marcosvbras.robomarket.flows.home.viewmodel.ProfileViewModelFactory;
import com.marcosvbras.robomarket.interfaces.ActivityCallback;
import com.marcosvbras.robomarket.flows.login.ui.LoginActivity;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends BaseFragment {

    private FragmentProfileBinding fragmentBinding;
    private HomeActivityCallback activityCallback;

    public static ProfileFragment newInstance(@NonNull HomeActivityCallback activityCallback) {
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.activityCallback = activityCallback;
        return profileFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        fragmentBinding.setViewModel(createViewModel());
        showInfo();
        return fragmentBinding.getRoot();
    }

    private ProfileViewModel createViewModel() {
        return ViewModelProviders.of(this, new ProfileViewModelFactory(activityCallback))
                .get(ProfileViewModel.class);
    }

    public void showInfo() {
        fragmentBinding.setUser(App.getInstance().getUser());
    }

    @Override
    public void onResume() {
        super.onResume();
        showInfo();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_home, menu);
        menu.findItem(R.id.menu_add).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_logout) {
            App.getInstance().deleteCredentials();
            activityCallback.openActivity(LoginActivity.class, null,true);
        }

        return super.onOptionsItemSelected(item);
    }
}
